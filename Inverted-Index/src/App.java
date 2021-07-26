import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(System.in);

        System.out.println("Enter query:");
        String query = cin.nextLine();
        cin.close();


        System.out.println("Viewing results...");

        InvertedIndex engine = new InvertedIndex("docs");
        var matches = engine.search(query);

        if(matches.isEmpty()) {
            System.out.println("No matches found!");
        } else {
            System.out.println("Search results: ");
            for (Doc doc : matches) {
                System.out.println(doc);
            }
        }
    }
}
