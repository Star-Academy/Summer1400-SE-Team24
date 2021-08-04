package tests;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;

import file_handler.Doc;
import search.InvertedIndex;
import search.Mapper;

@RunWith(MockitoJUnitRunner.class)
public class InvertedIndexTest {

    private final String KEY = "key";
    private InvertedIndex index;
    private Map<String, Set<Doc>> fakeMap;
    private Set<Doc> docSet;

    @Mock
    Mapper mapper;

    @Before
    public void init() {

        index = new InvertedIndex(new ArrayList<>() {
            {add(new Doc("1", new ArrayList<>()));}
        }, mapper);
        docSet = new HashSet<>();

        docSet.add(new Doc("1", new ArrayList<>()));
        docSet.add(new Doc("2", new ArrayList<>()));
        docSet.add(new Doc("3", new ArrayList<>()));

        fakeMap = new HashMap<>();
        fakeMap.put(KEY, docSet);
    }

    @Test
    public void invertedIndexGetTest() {

        when(mapper.mergeMaps(any(), any())).thenReturn(fakeMap);

        var result = index.get(KEY);
        Assert.assertEquals(docSet, result);
    }
}
