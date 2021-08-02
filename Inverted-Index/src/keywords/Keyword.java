package keywords;

import java.util.Set;

import file_handler.Doc;

public interface Keyword {

    public String getWord();
    public Set<Doc> operate(Set<Doc> docs, Set<Doc> newDocs);
}