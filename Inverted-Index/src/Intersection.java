import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Intersection extends Operation {

    Intersection(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs) {
        if(!keyWords.isEmpty()) {
            var firstEl = map.get(keyWords.get(0));
            if(firstEl != null) {
                docs.addAll(firstEl);
            }
        }

        for (var word : keyWords) {
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
            if (docs.isEmpty()) 
                return docs;
        }
        return docs;
    }
}