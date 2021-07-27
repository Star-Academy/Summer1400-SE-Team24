import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    public static Map<String, List<Doc>> map(List<Doc> docs) {

        Map<String, List<Doc>> map = new HashMap<>();

        for (var doc : docs)
            mergeMaps(map, mapDoc(doc));

        return map;
    }
    private static void mergeMaps(Map<String, List<Doc>> map, Map<String, Doc> doc) {

        for (var pair : map.entrySet()) {
            var key = pair.getKey();
            if(doc.containsKey(key)) {
                var docValue = doc.get(key);
                var mapValues = map.get(key);
                if(!mapValues.contains(docValue)) {
                    mapValues.add(docValue);
                    map.put(key, mapValues);
                }
            }
        }

        for (var pair : doc.entrySet()) {
            var key = pair.getKey();
            if(map.containsKey(key)) {
                continue;
            } else {
                var list = new ArrayList<Doc>();
                list.add(pair.getValue());
                map.put(key, list);
                
            }
        }
    }
    private static Map<String, Doc> mapDoc(Doc doc) {

        Map<String, Doc> map = new HashMap<>();

        for (String word : doc.getWords()) {
            map.put(word, doc);
        }

        return map;
    }
}
