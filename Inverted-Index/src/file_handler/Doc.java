package file_handler;

import java.util.List;

public class Doc {

    private String docName;
    private List<String> words;

    public Doc(String docName, List<String> words) {
        this.docName = docName;
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }
    public String getName() {
        return docName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass().equals(this.getClass())) {
            final Doc doc = (Doc)obj;
            return this.docName.equals(doc.docName);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.docName.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", this.docName);
    }
}
