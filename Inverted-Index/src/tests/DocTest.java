package tests;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import file_handler.Doc;
import junit.framework.Assert;

public class DocTest {

    private final String SMAPLE_NAME = "docName";
    private final List<String> SMAPLE_WORDS = Arrays.asList("word1", "word2", "word3");

    private Doc doc1;

    @Before
    public void initiTestDoc() {
        doc1 = new Doc(SMAPLE_NAME, SMAPLE_WORDS);
    }

    @Test
    public void getNameTest() {
        var name = doc1.getName();
        Assert.assertEquals(SMAPLE_NAME, name);
    }

    @Test
    public void getWordsTest() {
        var words = doc1.getWords();
        Assert.assertEquals(SMAPLE_WORDS, words);
    }

    @Test
    public void equalsTest() {
        Assert.assertEquals(new Doc(SMAPLE_NAME, SMAPLE_WORDS), doc1);
    }

    @Test
    public void hashSetTets() {
        Assert.assertEquals(SMAPLE_NAME.hashCode(), doc1.hashCode());
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals(SMAPLE_NAME, doc1.toString());
    }
    @After
    public void dispose() {
        doc1 = null;
    }
}
