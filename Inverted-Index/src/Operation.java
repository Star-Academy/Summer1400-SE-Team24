import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class Operation {
    
    protected Map<String, List<Doc>> map;
    protected List<String> keyWords;
    protected Set<Doc> docs;

    Operation(List<String> keyWords, Map<String, List<Doc>> map){
        this.keyWords = keyWords;
        this.map = map;
    }

    abstract public void operate(Set<Doc> docs);
    public static List<Operation> getOps(TypeQuery typeQuery, Map<String, List<Doc>> map){
        List<Operation> ops = new ArrayList<>(){
            {
                add(new Intersection(typeQuery.ordinary, map));
                add(new Union(typeQuery.include, map));
                add(new Exclude(typeQuery.exclude, map));
            }
        };
        return ops;
    }

}





