import java.util.List;
import java.util.Map;
import java.util.Set;


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