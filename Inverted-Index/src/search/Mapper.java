package search;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import file_handler.Doc;

public class Mapper {

    public Map<String, Set<Doc>> convertDocToMap(Doc doc) {

        var map = new HashMap<String, Set<Doc>>();
        var docList = new HashSet<Doc>();
        docList.add(doc);

        for (var word : doc.getWords()) {
            map.put(word, docList);
        }
        return map;
    }
    public Map<String, Set<Doc>> mergeMaps(Map<String, Set<Doc>> map, Map<String, Set<Doc>> doc) {

        appendSameKeys(map, doc);
        addDiffrentKeys(map, doc);

        return map;
    }
    public void appendSameKeys(Map<String, Set<Doc>> baseMap, Map<String, Set<Doc>> additionalMap) {
        for (var pair : baseMap.entrySet()) {
            var key = pair.getKey();
            if(additionalMap.containsKey(key)) {
                var docValues = additionalMap.get(key);
                var mapValues = baseMap.get(key);
                mapValues.addAll(docValues);
                baseMap.put(key, mapValues);
            }
        }
    }
    public void addDiffrentKeys(Map<String, Set<Doc>> baseMap, Map<String, Set<Doc>> additionalMap) {
        for (var pair : additionalMap.entrySet()) {
            var key = pair.getKey();
            if(baseMap.containsKey(key)) {
                continue;
            } else {
                var set = new HashSet<Doc>();
                set.addAll(pair.getValue());
                baseMap.put(key, set);
                
            }
        }
    }
}
