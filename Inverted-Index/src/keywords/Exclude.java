package keywords;
import java.util.HashSet;
import java.util.Set;

import file_handler.Doc;


public class Exclude implements Keyword {

    private String word;

    public Exclude(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
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