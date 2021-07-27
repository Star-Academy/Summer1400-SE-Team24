public class App {
    public static void main(String[] args)  {
        IUserIO io = new ConsoleIO();

        String query = io.getInput("Enter qury: ");

        io.output("Viewing results...");

        InvertedIndex engine = new InvertedIndex("docs");
        var matches = engine.search(query);

        if(matches.isEmpty()) {
            io.output("No matches found!");
        } else {
            io.output("Search results: ");
            for (Doc doc : matches) {
                io.output(doc.toString());
            }
        }
    }
}
