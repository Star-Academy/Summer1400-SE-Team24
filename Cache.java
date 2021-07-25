import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Cache {

    private File file;

    public Cache(String filePath) {
        this.file = new File(filePath);
    }

    public boolean cacheExists() {
        return file.exists();
    }

    public void saveMap(Map<String, List<Doc>> map) {
        this.clearCache();
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

    public boolean clearCache() {
        return file.delete();
    }

    public Map<String, List<Doc>> readCache() {
        return new HashMap<>();
    }
}