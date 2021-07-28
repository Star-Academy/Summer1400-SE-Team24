package keywords;
import java.util.List;
import java.util.Map;
import java.util.Set;

import file_handler.Doc;

public class Ordinary extends Keyword {


    public Ordinary(String word){
        super(word);
    }

    @Override
    public Set<Doc> operate(Set<Doc> docs, List<Doc> newDocs) {

        if(newDocs != null)
            docs.retainAll(newDocs);
        return docs;
    }
}