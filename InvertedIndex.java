import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class InvertedIndex {  
    private String INCLUDE = "+";
    private String EXCLUDE = "-";
    private String path;

    public InvertedIndex(String path) {
        this.path = path;
    }

    public List<Doc> search(String query) {  

        var keywords = query.split(" ");

        var ordinery = new ArrayList<String>();
        var exclude = new ArrayList<String>();
        var include = new ArrayList<String>();

        for (String keyword : keywords) {
            if(!keyword.startsWith(INCLUDE) && !keyword.startsWith(EXCLUDE)) {
                ordinery.add(keyword);
            }
            else if(keyword.startsWith(INCLUDE)) {
                include.add(keyword);
            }
            else {
                exclude.add(keyword);
            }
        }

        var map = getMap();
        var docs = new ArrayList<Doc>();

        for (var word : ordinery) {
            docs.addAll(map.get(word));
        }

        return docs;
    }  

    public Map<String, List<Doc>> getMap() {

        IFileReader fileReader = new FileReader();
        Mapper mapper = new Mapper();
        Map<String, List<Doc>> map = null;

        try {
            List<Doc> files = fileReader.readFiles(path);
            map = mapper.map(files);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}  