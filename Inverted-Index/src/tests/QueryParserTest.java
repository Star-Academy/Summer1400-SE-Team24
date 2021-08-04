package tests;

import org.junit.Test;
import org.junit.Assert;

import search.QueryParser;

public class QueryParserTest {
    

    @Test
    public void parseQueryTest() {
        var parser = new QueryParser();
        String query = "-exclude ordinary +union";
        var keywords = parser.parseQuery(query);

        Assert.assertEquals(keywords.get(0).getWord(), "ordinary");
        Assert.assertEquals(keywords.get(1).getWord(), "union");
        Assert.assertEquals(keywords.get(2).getWord(), "exclude");
    }
}
