package file_handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<Doc> getFilesDocs(File[] files) throws FileNotFoundException {
        var docs = new ArrayList<Doc>();
        for (File file : files) {
            docs.add(new Doc(file.getName(), getFilesWords(file)));
        }
        return docs;
    }
    public List<String> getFilesWords(File file) throws FileNotFoundException  {

        try (Scanner scanner = new Scanner(file)) {
            List<String> words = new ArrayList<>();
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                String[] lineWords = line.replaceAll("[^a-zA-Z0-9]"," ").split("\\s+");
                words.addAll(Arrays.asList(lineWords));
            }
            return words;
        }
    }
}