package file_handler;

import java.util.List;

public class Doc {

    private String docID;
    private List<String> words;

    public Doc(String docName, List<String> words) {
        this.docID = docName;
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }
    public String getName() {
        return docID;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass().equals(this.getClass())) {
            final Doc doc = (Doc)obj;
            return this.docID.equals(doc.docID);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.docID.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", this.docID);
    }
}
