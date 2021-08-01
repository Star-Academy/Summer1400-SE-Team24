package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import keywords.Ordinary;
import keywords.Union;
import keywords.Exclude;
import keywords.Keyword;
import file_handler.Doc;

public class KeywordsTest {
    private Keyword keyword;
    private Set<Doc> docs, newDocs, expected_result;

    @Before
    public void BeforeTest(){
        docs = new HashSet<Doc>();
        expected_result = new HashSet<Doc>();
        docs.add(new Doc("1", Arrays.asList("some", "words", "are", "here")));
        docs.add(new Doc("2", Arrays.asList("some", "another", "words")));
        newDocs = new HashSet<Doc>();
        newDocs.add(new Doc("1", Arrays.asList("some", "words", "are", "here")));
        newDocs.add(new Doc("4", Arrays.asList("here")));
    }
    @Test
    public void ordinaryTest(){
        keyword = new Ordinary("word");
        expected_result.add(new Doc("1", Arrays.asList("some", "words", "are", "here")));
    }

    @Test
    public void excludeTest(){
        keyword = new Exclude("word");
        expected_result.add(new Doc("2", Arrays.asList("some", "another", "words")));  
    }

    @Test
    public void includeTest(){
        keyword = new Union("word");
        expected_result.add(new Doc("1", Arrays.asList("some", "words", "are", "here")));
        expected_result.add(new Doc("2", Arrays.asList("some", "another", "words")));
        expected_result.add(new Doc("4", Arrays.asList("here")));
    }

    @After
    public void afterTest(){
        var result = keyword.operate(docs, newDocs);
        Assert.assertEquals(expected_result, result);
    }
}
