package tests;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import keywords.Ordinary;
import file_handler.Doc;

public class KeywordsTest {
    @Test
    public void ordinaryTest(){
        var ordinary = new Ordinary("word");
        var docs = new HashSet<Doc>();
        docs.add(new Doc("1", Arrays.asList("some", "words", "are", "here")));
        var newDocs = new HashSet<Doc>();
        newDocs.add(new Doc("1", Arrays.asList("some", "words", "in", "here")));
        var expected_result = new HashSet<String>();
        expected_result.add("some");
        expected_result.add("words");
        expected_result.add("here");

        var result = ordinary.operate(docs, newDocs);
        // System.out.println(result.toArray());
        // Assert.assertEquals(expected_result, result);
    }
}
