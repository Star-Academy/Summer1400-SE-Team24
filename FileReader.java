import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader implements IFileReader{

    @Override
    public List<Doc> readFiles(String filePath) throws IOException {

        File directoryPath = new File(filePath);
        File filesList[] = directoryPath.listFiles();
        List<Doc> docs = new ArrayList<>();
  
        for(File file : filesList) {
            docs.add(fileToDoc(file));
        }

        return docs;
    }
    
    private Doc fileToDoc(File file) throws IOException {

        Scanner sc = new Scanner(file);

        String input;
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            input = sc.nextLine().replaceAll("[^a-zA-Z0-9]"," ");
            sb.append(input+" ");
        }
        sc.close();

        return new Doc(file.getName(), sb);
    }
}