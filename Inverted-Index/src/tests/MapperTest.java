package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.Assert;

import file_handler.Doc;
import search.Mapper;

public class MapperTest {
    private final String WORD1 = "word1";
    private final String WORD2 = "word2";
    private final String WORD3 = "word3";

    private final Doc DOC_TEST1 = new Doc("1", Arrays.asList(WORD1, WORD2, WORD3));
    private final Doc DOC_TEST2 = new Doc("2", new ArrayList<>());

    @Test
    public void mergeMapsTest() {

        var map1 = new HashMap<String, Set<Doc>>();
        var map2 = new HashMap<String, Set<Doc>>();
        var map3 = new HashMap<String, Set<Doc>>();

        map1.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST1);}});
        map1.put(WORD2, new HashSet<Doc>() {{add(DOC_TEST2);}});

        map2.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST2);}});
        map2.put(WORD3, new HashSet<Doc>() {{add(DOC_TEST1);}});

        map3.put(WORD1, new HashSet<Doc>() {{add(DOC_TEST1);add(DOC_TEST2);}});
        map3.put(WORD2, new HashSet<Doc>() {{add(DOC_TEST2);}});
        map3.put(WORD3, new HashSet<Doc>() {{add(DOC_TEST1);}});

        var mapper = new Mapper();
        var result = mapper.mergeMaps(map1, map2);
        
        Assert.assertEquals(map3, result);
    }

    @Test 
    public void getDocMapTest() {
        var mapper = new Mapper();
        var map = mapper.getDocMap(DOC_TEST1);
        Assert.assertEquals(map.get(WORD1), new HashSet<Doc>() {{add(DOC_TEST1);}});
    }
}
