package keywords;
import java.util.Set;

import file_handler.Doc;

public class Ordinary extends Keyword {


    public Ordinary(String word){
        super(word);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs, Set<Doc> newDocs) {

        if(docs == null) {
            return newDocs;
        }
        if(newDocs != null)
            docs.retainAll(newDocs);
        return docs;
    }
}