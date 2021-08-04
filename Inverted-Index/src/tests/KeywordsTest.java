package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    private final Doc DOC_TEST1 = new Doc("1", Arrays.asList("some", "words", "are", "here"));
    private final Doc DOC_TEST2 = new Doc("2", Arrays.asList("some", "another", "words"));
    private final Doc DOC_TEST3 = new Doc("1", Arrays.asList("some", "words", "are", "here"));
    private final Doc DOC_TEST4 = new Doc("4", Arrays.asList("here"));
    private final String DUMMY_WORD = "dummyword";

    @Before
    public void beforeTest(){
        expected_result = new HashSet<>();

        docs = new HashSet<>();
        docs.add(DOC_TEST1);
        docs.add(DOC_TEST2);

        newDocs = new HashSet<>();
        newDocs.add(DOC_TEST3);
        newDocs.add(DOC_TEST4);
    }
    @Test
    public void ordinaryTest(){
        keyword = new Ordinary(DUMMY_WORD);
        expected_result.add(DOC_TEST1);
    }

    @Test
    public void excludeTest(){
        keyword = new Exclude(DUMMY_WORD);
        expected_result.add(DOC_TEST2);
    }

    @Test
    public void includeTest(){
        keyword = new Union(DUMMY_WORD);
        expected_result.add(DOC_TEST1);
        expected_result.add(DOC_TEST2);
        expected_result.add(DOC_TEST4);
    }

    @After
    public void afterTest(){
        var result = keyword.operate(docs, newDocs);
        Assert.assertEquals(expected_result, result);
    }
}
