package search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file_handler.Doc;

public class Mapper {

    public Map<String, List<Doc>> mergeMaps(Map<String, List<Doc>> map, Map<String, List<Doc>> doc) {
        System.out.println(map.size());

        doc.forEach(
            (word, list) -> map.merge(word, list, (l1,l2) -> {
            l1.addAll(l2);
            return l1;
        }));

        System.out.println(map.size());
        return map;
    }
    public Map<String, List<Doc>> convertDocToMap(Doc doc) {

        Map<String, List<Doc>> map = new HashMap<>();
        var docList = new ArrayList<Doc>();
        docList.add(doc);

        for (String word : doc.getWords()) {
            map.put(word, docList);
        }

        return map;
    }
}
