package search;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import file_handler.Doc;

public class Mapper {

    public Map<String, Set<Doc>> getDocMap(Doc doc) {

        var map = new HashMap<String, Set<Doc>>();
        var docList = new HashSet<Doc>();
        docList.add(doc);

        for (var word : doc.getWords()) {
            map.put(word, docList);
        }
        return map;
    }
    public Map<String, Set<Doc>> mergeMaps(Map<String, Set<Doc>> map, Map<String, Set<Doc>> doc) {

        Map<String, Set<Doc>> newMap;

        newMap = appendSameKeys(map, doc);
        newMap = addDiffrentKeys(newMap, doc);

        return newMap;
    }
    public Map<String, Set<Doc>> appendSameKeys(Map<String, Set<Doc>> baseMap, Map<String, Set<Doc>> additionalMap) {
        
        for (var pair : baseMap.entrySet()) {
            baseMap = appendPairValues(baseMap, additionalMap, pair);
        }
        return baseMap;
    }
    public Map<String, Set<Doc>> appendPairValues(Map<String, Set<Doc>> baseMap, Map<String, Set<Doc>> additionalMap, Entry<String, Set<Doc>> pair) {
        
        var key = pair.getKey();
        if(additionalMap.containsKey(key)) {
            var docValues = additionalMap.get(key);
            var mapValues = baseMap.get(key);
            mapValues.addAll(docValues);
            baseMap.put(key, mapValues);
        }

        return baseMap;
    }
    public Map<String, Set<Doc>> addDiffrentKeys(Map<String, Set<Doc>> baseMap, Map<String, Set<Doc>> additionalMap) {

        for (var pair : additionalMap.entrySet()) {
            baseMap = addPair(baseMap, pair);
        }

        return baseMap;
    }
    public Map<String, Set<Doc>> addPair(Map<String, Set<Doc>> baseMap, Entry<String, Set<Doc>> pair) {
        
        var key = pair.getKey();
        if(!baseMap.containsKey(key)) {
            var set = new HashSet<Doc>();
            set.addAll(pair.getValue());
            baseMap.put(key, set);
        }

        return baseMap;
    }
}
