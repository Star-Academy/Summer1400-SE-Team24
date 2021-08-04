package tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

import exceptions.NotADirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;
import search.InvertedIndex;
import search.Mapper;
import search.QueryParser;
import search.SearchEngine;

public class SearchTest {

    private final String WORD1 = "word1";
    private final String WORD2 = "word2";
    private final String WORD3 = "word3";

    private final Doc DOC_TEST1 = new Doc("1", Arrays.asList(WORD1, WORD2, WORD3));
    private final Doc DOC_TEST2 = new Doc("2", new ArrayList<>());

    private List<Doc> testDocs;

    @Before
    public void getTestDocs() throws NotADirectoryException, FileNotFoundException {
        IDirectoryReader reader = new DirectoryReader();
        var testFiles = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs");
        FileReader fileReader = new FileReader();
        this.testDocs = fileReader.getFilesDocs(testFiles);
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
        var map = mapper.getDocMap(DOC_TEST1);
        Assert.assertEquals(map.get(WORD1), new HashSet<Doc>() {{add(DOC_TEST1);}});
    }

    @Test
    public void mergeMapsTest() {

        var map1 = new HashMap<String, Set<Doc>>();
        var map2 = new HashMap<String, Set<Doc>>();
        var map3 = new HashMap<String, Set<Doc>>();

        map1.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST1);}});
        map1.put(WORD2, new HashSet<Doc>() {{add(DOC_TEST2);}});

        map2.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST2);}});
        map2.put(WORD3, new HashSet<Doc>() {{add(DOC_TEST1);}});

        map3.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST1);add(DOC_TEST2);}});
        map3.put(WORD2, new HashSet<Doc>() {{add(DOC_TEST2);}});
        map3.put(WORD3, new HashSet<Doc>() {{add(DOC_TEST1);}});

        var mapper = new Mapper();
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
    public void searchEngineTest() {
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
