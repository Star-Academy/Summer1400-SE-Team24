import java.util.List;
import java.util.Map;
import java.util.Set;

class Intersection extends Operation {

    Intersection(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs) {

        for (var word : keyWords) {
            var list = map.get(word);
            if(list != null) {
                docs.retainAll(list);
            }
            if (docs.isEmpty()) 
                return docs;
        }
        return docs;
    }
}