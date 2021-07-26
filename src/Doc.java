public class Doc {

    private String docID;
    private StringBuilder docText;

    public Doc(String docName, StringBuilder docText) {
        this.docID = docName;
        this.docText = docText;
    }

    public Doc(String docName) {
        this.docID = docName;
    }

    public String[] getWords() {
        return docText.toString().toLowerCase().split("\\s+");
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
