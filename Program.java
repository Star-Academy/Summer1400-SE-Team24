import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);

        System.out.println("Enter query:");
        String query = cin.nextLine();
        cin.close();

        InvertedIndex engine = new InvertedIndex(args[0]);
        var matches = engine.search(query);

        for (Doc doc : matches) {
            System.out.println(doc);
        }
    }
}
