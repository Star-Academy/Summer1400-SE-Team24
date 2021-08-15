package keywords;
import java.util.Set;
import file_handler.Doc;


public abstract class Keyword {

    private String word;

    public Keyword(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public abstract Set<Doc> operate(Set<Doc> docs, Set<Doc> newDocs);
}