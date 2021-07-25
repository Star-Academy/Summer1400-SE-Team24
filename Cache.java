import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
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

        Map<String, List<Doc>> map = new HashMap<>();

        try(Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                String key = line[0];
                List<Doc> docs = new ArrayList<>();
                for (int i = 1; i < line.length; i++) {
                    docs.add(new Doc(line[i]));
                }
                map.put(key, docs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }
}