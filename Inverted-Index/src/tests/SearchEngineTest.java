package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;

import search.InvertedIndex;
import search.QueryParser;
import search.SearchEngine;
import file_handler.Doc;
import keywords.Exclude;
import keywords.Ordinary;
import keywords.Union;

@RunWith(MockitoJUnitRunner.class)
public class SearchEngineTest {

    private final Doc DOC1;
    private final Doc DOC2;
    private final Doc DOC3;

    @Mock
    QueryParser parser;
    @Mock
    InvertedIndex index;
    @Mock
    Ordinary ordinary;
    @Mock
    Union union;
    @Mock
    Exclude exclude;

    public SearchEngineTest() {
        DOC1 = new Doc("57110", new ArrayList<>());
        DOC2 = new Doc("59483", new ArrayList<>());
        DOC3 = new Doc("59519", new ArrayList<>());
    }

    private Set<Doc> buildHashSet(boolean doc1Exists, boolean doc2Exists, boolean doc3Exists) {
        var set = new HashSet<Doc>();
        if(doc1Exists) set.add(DOC1);
        if(doc2Exists) set.add(DOC2);
        if(doc3Exists) set.add(DOC3);

        return set;
    }
    
    private Set<Doc> mockDocs(Map<Keyword,boolean[]> keywordDocs) {

        var keywordsMock = keywordDocs.keySet();
        when(parser.parseQuery(anyString())).thenReturn(keywordsMock);

        for(var pair : keywordDocs) {
            var includeArray = pair.getValue();
            when(pair.getKey().operate(any(), any())).thenReturn(
                buildHashSet(includeArray[0],includeArray[1],includeArray[2])
            );
        }

        var docs = engine.search(parser.parseQuery(""));
    }

    @Before
    public void mockResources() {

        var engine = new SearchEngine(index);

        doNothing().when(index.get(any()));
        
        doNothing().when(ordinary.getWord());
        doNothing().when(union.getWord());
        doNothing().when(exclude.getWord());
    }

    @Test
    public void unionWithExcludeTest() {
        var keywordDocs = new HashMap<Keyword,boolean[]>() {
            {
                put(ordinary, {false,true,false});
                put(union, {true,true,false});
                put(exclude, {true,false,true});
            }
        };
        
        var docs = mockDocs(keywordDocs);
        var expectedDocs = buildHashSet(false,true,false);
        Assert.assertEquals(expectedDocs, docs);
    }

    @Test
    public void includeAllTest() {
        var keywordDocs = new HashMap<Keyword,boolean[]>() {
            {
                put(ordinary, {false,false,false});
                put(union, {true,true,true});
            }
        };
        
        var docs = mockDocs(keywordDocs);
        var expectedDocs = buildHashSet(true,true,true);
        Assert.assertEquals(expectedDocs, docs);
    }

    @Test
    public void excludeAllTest() {
        
        var keywordDocs = new HashMap<Keyword,boolean[]>() {
            {
                put(ordinary, {true,false,true});
                put(exclude, {true,true,true});
            }
        };
        
        var docs = mockDocs(keywordDocs);
        var expectedDocs = buildHashSet(false,false,false);
        Assert.assertEquals(expectedDocs, docs);
    }

    @Test
    public void simpleSearchTets() {
        var keywordDocs = new HashMap<Keyword,boolean[]>() {
            {
                put(ordinary, {true,true,false});
            }
        };
        
        var docs = mockDocs(keywordDocs);
        var expectedDocs = buildHashSet(true,true,false);
        Assert.assertEquals(expectedDocs, docs);
    }
}
