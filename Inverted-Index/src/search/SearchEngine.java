package search;

import java.util.List;
import java.util.Set;

import file_handler.Doc;
import keywords.Keyword;

public class SearchEngine {
    private InvertedIndex index;

    public SearchEngine(InvertedIndex index) {
        this.index = index;
    }
    public Set<Doc> search(List<Keyword> keywords) {
        Set<Doc> docs = null;

        for (Keyword keyword : keywords) {
            
            var newDocs = index.get(keyword.getWord());
            docs = keyword.operate(docs, newDocs);
        }
        return docs;
    }
}
