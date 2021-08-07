package tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;

import exceptions.NotDirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;

public class SearchTest {

    private final String WORD1 = "word1";
    private final String WORD2 = "word2";
    private final String WORD3 = "word3";

    private final Doc DOC_TEST1 = new Doc("1", Arrays.asList(WORD1, WORD2, WORD3));
    private final Doc DOC_TEST2 = new Doc("2", new ArrayList<>());

    private List<Doc> testDocs;

    @Before
    public void getTestDocs() throws NotDirectoryException, FileNotFoundException {
        IDirectoryReader reader = new DirectoryReader();
        var testFiles = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs");
        FileReader fileReader = new FileReader();
        this.testDocs = fileReader.getFilesDocs(testFiles);
    }
}
