import java.util.ArrayList;
import java.util.List;

public class TypeQuery {
    private final String INCLUDE = "+";
    private final String EXCLUDE = "-";
    public List<String> ordinary = new ArrayList<>();
    public List<String> exclude = new ArrayList<>();
    public List<String> include = new ArrayList<>();

    public TypeQuery(String query){
        splitQuery(query);
    }

    private void splitQuery(String query){
        var keywords = query.toLowerCase().split(" ");
        for (String keyword : keywords) {
            if(!keyword.startsWith(INCLUDE) && !keyword.startsWith(EXCLUDE)) 
                ordinary.add(keyword);
            else if(keyword.startsWith(INCLUDE)) 
                include.add(keyword.substring(1));
            else 
                exclude.add(keyword.substring(1));
        }
    }
}
