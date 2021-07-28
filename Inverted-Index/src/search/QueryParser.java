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

        for (String keyword : words) {
            if(!keyword.startsWith(INCLUDE) && !keyword.startsWith(EXCLUDE)) 
                keywords.add(new Ordinary(keyword));
            else if(keyword.startsWith(INCLUDE)) 
                keywords.add(new Union(keyword.substring(1)));
            else 
                keywords.add(new Exclude(keyword.substring(1)));
        }
        return keywords;
    }
}
