package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import keywords.Ordinary;
import keywords.Union;
import keywords.Exclude;
import keywords.Keyword;
import file_handler.Doc;

public class KeywordsTest {

    private Keyword keyword;
    private Set<Doc> docs, newDocs, expectedResult;

    private final List<String> SAMPLE_WORDS = Arrays.asList("some", "words");
    private final Doc DOC_TEST1 = new Doc("1", SAMPLE_WORDS);
    private final Doc DOC_TEST2 = new Doc("2", SAMPLE_WORDS);
    private final Doc DOC_TEST3 = new Doc("3", SAMPLE_WORDS);
    private final String DUMMY_WORD = "dummyword";

    @Before
    public void beforeTest(){
        expectedResult = new HashSet<>();

        docs = new HashSet<>();
        docs.add(DOC_TEST1);
        docs.add(DOC_TEST2);

        newDocs = new HashSet<>();
        newDocs.add(DOC_TEST3);
        newDocs.add(DOC_TEST1);
    }

    @Test
    public void ordinaryTest(){
        keyword = new Ordinary(DUMMY_WORD);
        expectedResult.add(DOC_TEST1);
        var result = keyword.operate(docs, newDocs);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void excludeTest(){
        keyword = new Exclude(DUMMY_WORD);
        expectedResult.add(DOC_TEST2);
        var result = keyword.operate(docs, newDocs);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void includeTest(){
        keyword = new Union(DUMMY_WORD);
        expectedResult.add(DOC_TEST1);
        expectedResult.add(DOC_TEST2);
        expectedResult.add(DOC_TEST3);
        var result = keyword.operate(docs, newDocs);
        Assert.assertEquals(expectedResult, result);
    }

    @After
    public void afterTest(){
        expectedResult = null;
        keyword = null;
        newDocs = null;
        docs = null;
    }
}
