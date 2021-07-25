public class Doc {

    private String docID;
    private StringBuilder docText;

    public Doc(String docName, StringBuilder docText) {
        this.docID = docName;
        this.docText = docText;
    }

    public String[] getWords() {
        return docText.toString().split(" ");
    }
    @Override
    public String toString() {
        return String.format("%s", this.docID);
    }
}
