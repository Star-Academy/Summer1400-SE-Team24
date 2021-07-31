package search;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import file_handler.Doc;

public class Mapper {

    public Map<String, Set<Doc>> mergeMaps(Map<String, Set<Doc>> map, Map<String, Set<Doc>> doc) {

        for (var pair : map.entrySet()) {
            var key = pair.getKey();
            if(doc.containsKey(key)) {
                var docValues = doc.get(key);
                var mapValues = map.get(key);
                mapValues.addAll(docValues);
                map.put(key, mapValues);
            }
        }

        for (var pair : doc.entrySet()) {
            var key = pair.getKey();
            if(map.containsKey(key)) {
                continue;
            } else {
                var set = new HashSet<Doc>();
                set.addAll(pair.getValue());
                map.put(key, set);
                
            }
        }
        return map;
    }
    public Map<String, Set<Doc>> convertDocToMap(Doc doc) {

        Map<String, Set<Doc>> map = new HashMap<>();
        var docList = new HashSet<Doc>();
        docList.add(doc);

        for (var word : doc.getWords()) {
            map.put(word, docList);
        }
        return map;
    }
}
