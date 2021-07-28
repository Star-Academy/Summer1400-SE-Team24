package search;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file_handler.Doc;

public class InvertedIndex {
    private Map<String, List<Doc>> map;

    public InvertedIndex(List<Doc> docs, Mapper mapper) {
        map = new HashMap<>();

        for (var doc : docs)
            map = mapper.mergeMaps(map, mapper.convertDocToMap(doc));
    }
  
    public List<Doc> get(String word) {
        return map.get(word);
    }
}  
