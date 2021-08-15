package keywords;
import java.util.Set;

import file_handler.Doc;

public class Union extends Keyword {

    public Union(String word){
        super(word);
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
