package tests;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;


import file_handler.Doc;
import search.InvertedIndex;
import search.Mapper;

public class InvertedIndexTest {
    private List<Doc> testDocs;

    @Test
    public void invertedIndexGetTest() {
        var index = new InvertedIndex(testDocs, new Mapper());
        var result = index.get("have");
        Assert.assertEquals(result.size(), 5);
    }
}
