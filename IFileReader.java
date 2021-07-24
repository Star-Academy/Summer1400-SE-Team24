import java.io.IOException;
import java.util.List;

interface IFileReader {
    public List<Doc> readFiles(String path) throws IOException;
}