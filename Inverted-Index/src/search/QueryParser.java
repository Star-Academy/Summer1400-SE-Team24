package search;

import java.util.ArrayList;
import java.util.List;

import keywords.Keyword;
import keywords.Ordinary;
import keywords.Exclude;
import keywords.Union;
public class QueryParser {
    private final String INCLUDE = "+";
    private final String EXCLUDE = "-";

    public List<Keyword> parseQuery(String query){

        var words = query.toLowerCase().split(" ");

        List<Keyword> keywords = new ArrayList<>();

        for (var keyword : words) {
            if(!keyword.startsWith(INCLUDE) && !keyword.startsWith(EXCLUDE)) 
                keywords.add(new Ordinary(keyword));
        }
        for (var keyword : words) {
            if(keyword.startsWith(INCLUDE)) 
                keywords.add(new Union(keyword.substring(1)));
        }
        for (var keyword : words) {
            if(keyword.startsWith(EXCLUDE)) 
                keywords.add(new Exclude(keyword.substring(1)));
        }
        return keywords;
    }
}
