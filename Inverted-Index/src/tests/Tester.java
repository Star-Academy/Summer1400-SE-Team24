package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import exceptions.NotADirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;
import search.InvertedIndex;
import search.Mapper;
import search.QueryParser;
import search.SearchEngine;

import org.junit.Assert;
import org.junit.Before;

public class Tester {
    private List<Doc> testDocs;
    private File[] testFiles;
    @Before
    public void getTestDocs() throws NotADirectoryException, FileNotFoundException {
        IDirectoryReader reader = new DirectoryReader();
        testFiles = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs");
        FileReader fileReader = new FileReader();
        this.testDocs = fileReader.getFilesDocs(testFiles);
    }

    @Test
    public void fileReadingTest() throws NotADirectoryException {
        IDirectoryReader reader = new DirectoryReader();
        var files = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\docs");
        Assert.assertEquals(files.length, 1000);
    }

    @Test
    public void docEqualsTest() {
        String sameName = "sameName";
        var content = new ArrayList<String>();
        Doc doc1 = new Doc(sameName, content);
        Doc doc2 = new Doc(sameName, content);

        Assert.assertEquals(doc1, doc2);
    }

    @Test
    public void getFilesDocsTest() {

        Assert.assertEquals(testFiles.length, testDocs.size());
        Assert.assertEquals(testDocs.get(0).getName(), testFiles[0].getName());
        Assert.assertEquals(testDocs.get(0).getWords().size(), 8);
    }

    @Test
    public void invertedIndexGetTest() {
        var index = new InvertedIndex(testDocs, new Mapper());
        var result = index.get("have");
        Assert.assertEquals(result.size(), 5);
    }
    @Test 
    public void getDocMapTest() {
        var mapper = new Mapper();
        var doc = new Doc("1", Arrays.asList("word1", "word2", "word3"));
        var map = mapper.getDocMap(doc);
        Assert.assertEquals(map.get("word1"), new HashSet<Doc>() {{add(doc);}});
    }
    @Test
    public void mergeMapsTest() {

        var mapper = new Mapper();
        var map1 = new HashMap<String, Set<Doc>>();
        var map2 = new HashMap<String, Set<Doc>>();
        var map3 = new HashMap<String, Set<Doc>>();

        map1.put("word1", new HashSet<Doc>() {{add(new Doc("1", new ArrayList<String>()));}});
        map1.put("word2", new HashSet<Doc>() {{add(new Doc("2", new ArrayList<String>()));}});

        map2.put("word1", new HashSet<Doc>() {{add(new Doc("2", new ArrayList<String>()));}});
        map2.put("word3", new HashSet<Doc>() {{add(new Doc("1", new ArrayList<String>()));}});

        map3.put("word1", new HashSet<Doc>() {{add(new Doc("1", new ArrayList<String>()));add(new Doc("2", new ArrayList<String>()));}});
        map3.put("word2", new HashSet<Doc>() {{add(new Doc("2", new ArrayList<String>()));}});
        map3.put("word3", new HashSet<Doc>() {{add(new Doc("1", new ArrayList<String>()));}});

        var result = mapper.mergeMaps(map1, map2);

        Assert.assertEquals(map3, result);
    }

    @Test
    public void parseQueryTest() {
        var parser = new QueryParser();
        String query = "-exclude ordinary +union";
        var keywords = parser.parseQuery(query);

        Assert.assertEquals(keywords.get(0).getWord(), "ordinary");
        Assert.assertEquals(keywords.get(1).getWord(), "union");
        Assert.assertEquals(keywords.get(2).getWord(), "exclude");
    }

    @Test
    public void SearchEngineTest() {
        var index = new InvertedIndex(testDocs, new Mapper());
        var engine = new SearchEngine(index);

        String query = "i have -friend";
        var parser = new QueryParser();
        var docs = engine.search(parser.parseQuery(query));
        var expectedDocs = new HashSet<Doc>() {
            {
                add(new Doc("59496", new ArrayList<>()));
                add(new Doc("59495", new ArrayList<>()));
                add(new Doc("59494", new ArrayList<>()));
            }
        };

        Assert.assertEquals(expectedDocs, docs);
    }
}
