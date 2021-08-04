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

        keywords.addAll(getOrdinaries(words));
        keywords.addAll(getUnions(words));
        keywords.addAll(getExcludes(words));
        
        return keywords;
    }
    public List<Keyword> getOrdinaries(String[] words) {

        var ordinaries = new ArrayList<Keyword>();
        for (var keyword : words) {
            if(!keyword.startsWith(INCLUDE) && !keyword.startsWith(EXCLUDE)) 
                ordinaries.add(new Ordinary(keyword));
        }
        return ordinaries;
    }
    public List<Keyword> getUnions(String[] words) {

        var additionals = new ArrayList<Keyword>();
        for (var keyword : words) {
            if(keyword.startsWith(INCLUDE)) 
                additionals.add(new Union(keyword.substring(1)));
        }
        return additionals;
    }
    public List<Keyword> getExcludes(String[] words) {

        var excludes = new ArrayList<Keyword>();
        for (var keyword : words) {
            if(keyword.startsWith(EXCLUDE)) 
                excludes.add(new Exclude(keyword.substring(1)));
        }
        return excludes;
    }
}
