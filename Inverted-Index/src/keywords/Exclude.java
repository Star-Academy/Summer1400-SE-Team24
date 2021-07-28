package keywords;
import java.util.HashSet;
import java.util.Set;

import file_handler.Doc;


public class Exclude extends Keyword {

    public Exclude(String word){
        super(word);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs, Set<Doc> newDocs) {
        if(docs == null) {
            return new HashSet<>();
        }
        if(newDocs != null)
            docs.removeAll(newDocs);
        return docs;
    }
}