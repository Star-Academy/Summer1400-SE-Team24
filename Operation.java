import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class Operation {
    
    protected Map<String, List<Doc>> map;
    protected List<String> keyWords;
    protected Set<Doc> docs;

    Operation(List<String> keyWords, Map<String, List<Doc>> map){
        this.keyWords = keyWords;
        this.map = map;
    }

    abstract public void operate(Set<Doc> docs);
    public static List<Operation> getOps(TypeQuery typeQuery, Map<String, List<Doc>> map){
        List<Operation> ops = new ArrayList<>(){
            {
                add(new Intersection(typeQuery.ordinary, map));
                add(new Union(typeQuery.include, map));
                add(new Exclude(typeQuery.exclude, map));
            }
        };
        return ops;
    }

}


class Intersection extends Operation {

    Intersection(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public void operate(Set<Doc> docs) {
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
                return;
        }

    }
}

class Union extends Operation {

    Union(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public void operate(Set<Doc> docs) {
        for (String word : keyWords) {
            var list = map.get(word);
            if(list != null) {
                for (Doc doc : list) {
                    docs.add(doc);
                }
            }
        }
    }
}

class Exclude extends Operation {

    Exclude(List<String> words, Map<String, List<Doc>> map){
        super(words, map);
    }

    @Override
    public void operate(Set<Doc> docs) {
        for (String word : keyWords) {
            var list = map.get(word);
            if(list != null) {
                for (Doc doc : list) {
                    docs.remove(doc);
                }
            }
        }
    }
}