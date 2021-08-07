package tests;
import search.InvertedIndex;
import search.QueryParser;
import search.SearchEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import file_handler.Doc;
import keywords.Exclude;
import keywords.Ordinary;
import keywords.Union;

@RunWith(MockitoJUnitRunner.class)
public class SearchEngineTest {

    private final Doc DOC1;
    private final Doc DOC2;
    private final Doc DOC3;

    private final String ORDINARY_WORD = "i";
    private final String UNION_WORD = "first";
    private final String EXCLUDE_WORD = "friend";

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

    @Before
    public void mockResources() {
        var mockKeywords = Arrays.asList(ordinary, union, exclude);
        when(parser.parseQuery(anyString())).thenReturn(mockKeywords);

        when(ordinary.getWord()).thenReturn(ORDINARY_WORD);
        when(union.getWord()).thenReturn(UNION_WORD);
        when(exclude.getWord()).thenReturn(EXCLUDE_WORD);

        when(ordinary.operate(any(), any())).thenReturn(new HashSet<Doc>() {
            {
                add(DOC1);
                add(DOC2);
            }
        });

        when(union.operate(any(), any())).thenReturn(new HashSet<Doc>() {
            {
                add(DOC1);
                add(DOC2);
                add(DOC3);
            }
        });

        when(exclude.operate(any(), any())).thenReturn(new HashSet<Doc>() {
            {
                add(DOC2);
                add(DOC3);
            }
        });

        when(index.get(ORDINARY_WORD)).thenReturn(new HashSet<Doc>() {
            {
                add(DOC1);
                add(DOC2);
            }
        });
        when(index.get(UNION_WORD)).thenReturn(new HashSet<Doc>() {
            {add(DOC3);}
        });
        when(index.get(EXCLUDE_WORD)).thenReturn(new HashSet<Doc>() {
            {add(DOC1);}
        });
    }
    @Test
    public void searchEngineTest() {
        var engine = new SearchEngine(index);

        var docs = engine.search(parser.parseQuery(""));

        var expectedDocs = new HashSet<Doc>() {
            {
                add(new Doc("59483", new ArrayList<>()));
                add(new Doc("59519", new ArrayList<>()));
            }
        };

        Assert.assertEquals(expectedDocs, docs);
    }
}
