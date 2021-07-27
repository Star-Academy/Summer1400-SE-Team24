import java.util.List;
import java.util.Map;
import java.util.Set;

class Union extends Operation {

    Union(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs) {
        for (String word : keyWords) {
            docs.addAll(map.get(word));
        }
        return docs;
    }
}
