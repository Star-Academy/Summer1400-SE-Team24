package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import file_handler.Doc;
import search.Mapper;

public class MapperTest {
    private final String WORD1 = "word1";
    private final String WORD2 = "word2";
    private final String WORD3 = "word3";

    private final Doc DOC_TEST1;
    private final Doc DOC_TEST2;

    private Set<Doc> set1;
    private Set<Doc> set2;
    private Set<Doc> set12;

    public MapperTest() {
        DOC_TEST1 = new Doc("1", Arrays.asList(WORD1, WORD2, WORD3));
        DOC_TEST2 = new Doc("2", new ArrayList<>());
    }

    @Before
    public void initSets() {

        set1 = new HashSet<>();
        set2 = new HashSet<>();
        set12 = new HashSet<>();

        set1.add(DOC_TEST1);

        set2.add(DOC_TEST2);

        set12.add(DOC_TEST1);
        set12.add(DOC_TEST2);
    }

    @Test
    public void mergeMapsTest() {

        var map1 = new HashMap<String, Set<Doc>>();
        var map2 = new HashMap<String, Set<Doc>>();
        var map3 = new HashMap<String, Set<Doc>>();

        map1.put(WORD1, set1);
        map1.put(WORD2, set2);

        map2.put(WORD1, set2);
        map2.put(WORD3, set1);

        map3.put(WORD1, set12);
        map3.put(WORD2, set2);
        map3.put(WORD3, set1);

        var mapper = new Mapper();
        var result = mapper.mergeMaps(map1, map2);
        
        Assert.assertEquals(map3, result);
    }

    @Test 
    public void getDocMapTest() {
        var mapper = new Mapper();
        var map = mapper.getDocMap(DOC_TEST1);

        Assert.assertEquals(map.get(WORD1), set1);
    }

    @After
    public void dispose() {
        set1 = null;
        set2 = null;
        set12 = null;
    }
}
