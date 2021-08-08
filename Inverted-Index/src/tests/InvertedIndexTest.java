package tests;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;

import file_handler.Doc;
import search.InvertedIndex;
import search.Mapper;

@RunWith(MockitoJUnitRunner.class)
public class InvertedIndexTest {

    private InvertedIndex index;
    private Map<String, Set<Doc>> mockMap;
    private Set<Doc> docSet;

    @Mock
    Mapper mapper;

    private void initMockings(String mockKey, List<String> docNames, ) {

        docSet = new HashSet<>();
    
        for(var name : docNames) {
            docSet.add(new Doc(name, new ArrayList<>()));
        }
        
        mockMap = new HashMap<>();
        mockMap.put(mockKey, docSet);
        when(mapper.mergeMaps(any(), any())).thenReturn(mockMap);
    }
    @Before
    public void init() {

        index = new InvertedIndex(new ArrayList<>() {
            {add(new Doc("1", new ArrayList<>()));}
        }, mapper);
    }

    @Test
    public void invertedIndexGetTest1() {

        final String KEY = "key";
        initMockings(KEY, Arrays.asList("1","2","3"));

        var result = index.get(KEY);
        Assert.assertEquals(docSet, result);
    }

    @Test
    public void invertedIndexGetTest2() {

        final String KEY = "anotherkey";
        initMockings(KEY, new ArrayList<>());

        var result = index.get(KEY);
        Assert.assertEquals(null, result);
    }
}
