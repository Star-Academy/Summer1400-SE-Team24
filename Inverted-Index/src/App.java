import java.io.File;
import java.util.List;

import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;
import search.InvertedIndex;
import search.Mapper;
import search.QueryParser;
import search.SearchEngine;
import user_interface.ConsoleIO;
import user_interface.IUserIO;

public class App {
    private static final String DOCS_PATH = "docs";
    public static void main(String[] args)  {
        IDirectoryReader directoryReader = new DirectoryReader();
        FileReader fileReader = new FileReader();
        try {
            
            File[] documentFiles = directoryReader.getFiles(DOCS_PATH);
            List<Doc> docs = fileReader.convertFilesToDocs(documentFiles);
            
            Mapper mapper = new Mapper();
            InvertedIndex invertedIndex = new InvertedIndex(docs, mapper);
            SearchEngine engine = new SearchEngine(invertedIndex);

            IUserIO userio = new ConsoleIO();
            String query = userio.get();
            QueryParser parser = new QueryParser();
            var result = engine.search(parser.parseQuery(query));
                
            userio.printResult(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
