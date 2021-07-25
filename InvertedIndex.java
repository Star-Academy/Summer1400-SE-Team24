import java.util.ArrayList;
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
        getMap();
    }

    public Set<Doc> search(String query) {  

        TypeQuery typeQuery = new TypeQuery(query);
        System.out.println(typeQuery.ordinary);

        var docs = intersection(typeQuery.ordinary);
        union(docs, typeQuery.include);
        exclution(docs, typeQuery.exclude);

        return docs;
    }  

    private void getMap() {

        Cache cache = new Cache(cachePath);
        if(cache.cacheExists()) {
            map = cache.readCache();
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
            
            this.map = docMap;
            cache.saveMap(map);
        }
    }

    private Set<Doc> intersection(List<String> ordinary) {

        Set<Doc> docs = new HashSet<>();

        if(!ordinary.isEmpty()) {
            var firstEl = map.get(ordinary.get(0));
            if(firstEl != null) {
                docs.addAll(firstEl);
            }
        }

        for (var word : ordinary) {
            var set = docs;
            docs = new HashSet<>();
            var list = map.get(word);
            if(list != null) {
                for (Doc doc : list) {
                    if(set.contains(doc)) {
                        docs.add(doc);
                    }
                }
            }
            if (docs.isEmpty()) return docs;
        }

        return docs;
    }

    private void union(Set<Doc> docs, List<String> include) {
        for (String word : include) {
            var list = map.get(word);
            if(list != null) {
                for (Doc doc : list) {
                    if(!docs.contains(doc)) {
                        docs.add(doc);
                    }
                }
            }
        }
    }

    private void exclution(Set<Doc> docs, List<String> exclude) {
        for (String word : exclude) {
            var list = map.get(word);
            if(list != null) {
                for (Doc doc : list) {
                    if(docs.contains(doc)) {
                        docs.remove(doc);
                    }
                }
            }
        }
    }
}  
