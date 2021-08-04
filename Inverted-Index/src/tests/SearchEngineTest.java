package tests;
import search.InvertedIndex;
import search.Mapper;
import search.QueryParser;
import search.SearchEngine;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import file_handler.Doc;

public class SearchEngineTest {

    private List<Doc> testDocs;

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
