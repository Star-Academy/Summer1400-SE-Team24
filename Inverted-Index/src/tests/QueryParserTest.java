package tests;

import org.junit.Test;

import keywords.Keyword;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import search.QueryParser;

public class QueryParserTest {
    
    private final String TEST_QUERY = "-exclude ordinary +union";
    private List<Keyword> keywords;

    @Before
    public void initKeywords() {
        var parser = new QueryParser();
        String query = TEST_QUERY;
        keywords = parser.parseQuery(query);
    }

    @Test
    public void parseQueryTest1() {

        Assert.assertEquals(keywords.get(0).getWord(), "ordinary");
    }

    @Test
    public void parseQueryTest2() {

        Assert.assertEquals(keywords.get(1).getWord(), "union");
    }

    @Test
    public void parseQueryTest3() {

        Assert.assertEquals(keywords.get(2).getWord(), "exclude");
    }

    @After
    public void dispose() {
        keywords = null;
    }
}