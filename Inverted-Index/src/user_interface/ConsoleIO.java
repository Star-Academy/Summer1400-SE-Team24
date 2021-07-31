package user_interface;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import file_handler.Doc;

public class ConsoleIO implements IUserIO {

    @Override
    public String get() {
        System.out.println("Enter query: ");

        try (Scanner scanner = new Scanner(System.in)) {
            if(scanner.hasNextLine())
                return scanner.nextLine();
            throw new IOException();
            
        } catch (IOException error) {
            error.printStackTrace();
            return null;
        }
    }

    @Override
    public void printResult(Set<Doc> result) {
        for (var doc : result) {
            System.out.println(doc.toString());
        }
    }
    
}
