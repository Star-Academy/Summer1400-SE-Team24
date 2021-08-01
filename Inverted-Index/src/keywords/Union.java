package keywords;
import java.util.Set;

import file_handler.Doc;

public class Union implements Keyword {

    private String word;

    public Union(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs, Set<Doc> newDocs) {
        if(docs == null) {
            return newDocs;
        }
        if(newDocs != null)
            docs.addAll(newDocs);
        return docs;
    }
}
