package user_interface;

import java.util.Scanner;
import java.util.Set;

import file_handler.Doc;

public class ConsoleIO implements IUserIO {

    @Override
    public String get() {
        System.out.println("Enter query: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();

        return text;
    }

    @Override
    public void printResult(Set<Doc> result) {
        for (Doc doc : result) {
            System.out.println(doc.toString());
        }
    }
    
}
