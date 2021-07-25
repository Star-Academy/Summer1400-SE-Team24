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
  
        Scanner sc = null;
        for(File file : filesList) {

            sc = new Scanner(file);

            String input;
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                input = sc.nextLine().replaceAll("[^a-zA-Z0-9]"," ");
                sb.append(input+" ");
            }

            docs.add(new Doc(file.getName(), sb));
            sc.close();
        }

        return docs;
     }
}