package search;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file_handler.Doc;

public class InvertedIndex {
    private Map<String, List<Doc>> map;

    public InvertedIndex(List<Doc> docs, Mapper mapper) {
        Map<String, List<Doc>> tempMap = new HashMap<>();
        map = new HashMap<>();

        for (var doc : docs) {
            System.out.println(doc);
            System.out.println(doc.getWords());
            tempMap = mapper.mergeMaps(tempMap, mapper.convertDocToMap(doc));
            System.out.println(tempMap);
            int a = 5/0;
        }
        map.putAll(tempMap);
        File file = new File("cache.txt");
        try(FileWriter writer = new FileWriter(file)) {
            file.createNewFile();
            for (var pair : map.entrySet()) {
                writer.append(pair.getKey());
                for (var doc : pair.getValue()) {
                    
                    writer.append(" " + doc);
                }
                writer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public List<Doc> get(String word) {
        return map.get(word);
    }
}  
