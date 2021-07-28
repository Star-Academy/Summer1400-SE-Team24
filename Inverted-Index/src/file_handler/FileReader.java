package file_handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<Doc> convertFilesToDocs(File[] files) throws FileNotFoundException {
        var docs = new ArrayList<Doc>();
        for (File file : files) {
            docs.add(new Doc(file.getName(), readFileWords(file)));
        }
        return docs;
    }
    public List<String> readFileWords(File file) throws FileNotFoundException  {

        Scanner scanner = new Scanner(file);
        List<String> words = new ArrayList<>();

        while (scanner.hasNextLine()) {
            words.addAll(Arrays.asList(scanner.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9]"," ").split("\\s+")));
        }
        scanner.close();

        return words;
    }
}