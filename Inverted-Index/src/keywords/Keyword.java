package keywords;

import java.util.List;
import java.util.Set;

import file_handler.Doc;

public abstract class Keyword {
    
    protected String word;

    protected Keyword(String word){
        this.word = word;
    }
    public String getWord() {
        return word;
    }
    public abstract Set<Doc> operate(Set<Doc> docs, List<Doc> newDocs);

}





