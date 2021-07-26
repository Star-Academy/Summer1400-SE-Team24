import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class InvertedIndex {
    private final String cachePath = "./cache.txt";
    private String path;
    private Map<String, List<Doc>> map;

    public InvertedIndex(String docPath) {
        this.path = docPath;
        this.map = getMap();
    }

    public Set<Doc> search(String query) {  

        TypeQuery typeQuery = new TypeQuery(query);
        Set<Doc> docs = new HashSet<>();
        List<Operation> ops = Operation.getOps(typeQuery, map);
        for(var op : ops){
            op.operate(docs);
        }

        return docs;
    }  


    private Map<String, List<Doc>> getMap() {

        Cache cache = new Cache(cachePath);
        if(cache.cacheExists()) {
            return cache.readCache();
        }
        else {

            IFileReader fileReader = new FileReader();
            Mapper mapper = new Mapper();
            Map<String, List<Doc>> docMap = null;
            
            try {
                List<Doc> files = fileReader.readFiles(path);
                docMap = mapper.map(files);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            cache.saveMap(docMap);

            return docMap;
        }
    }
    

    

    
}  
