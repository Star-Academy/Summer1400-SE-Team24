import java.util.Scanner;

public interface IUserIO {
    public String getInput(String message);
    public void output(String text);
}

class ConsoleIO implements IUserIO {

    @Override
    public String getInput(String message) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        sc.close();
        return text;
    }

    @Override
    public void output(String text) {
        System.out.println(text);
    }
}
