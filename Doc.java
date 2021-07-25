import java.util.UUID;

public class Doc {

    private String docID;
    private String docName;
    private StringBuilder docText;

    public Doc(String docName, StringBuilder docText) {
        this.docID = UUID.randomUUID().toString();
        this.docName = docName;
        this.docText = docText;
    }

    public String[] getWords() {
        return docText.toString().split("\\s+");
    }
    @Override
    public String toString() {
        return String.format("{ID: %s, Name: %s}", this.docID.substring(0,5), this.docName);
    }
}
