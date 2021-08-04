package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import exceptions.NotADirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;

public class FileHandlerTest {

    private List<Doc> testDocs;
    private File[] testFiles;

    @Before
    public void getTestDocs() throws NotADirectoryException, FileNotFoundException {
        IDirectoryReader reader = new DirectoryReader();
        testFiles = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs");
        Assert.assertEquals(8, testFiles.length);

        FileReader fileReader = new FileReader();
        this.testDocs = fileReader.getFilesDocs(testFiles);
    }

    @Test
    public void docEqualsTest() {
        String sameName = "sameName";
        var content = new ArrayList<String>();
        Doc doc1 = new Doc(sameName, content);
        Doc doc2 = new Doc(sameName, content);

        Assert.assertEquals(doc1, doc2);
    }

    @Test
    public void getFilesDocsTest() {

        Assert.assertEquals(testFiles.length, testDocs.size());
        Assert.assertEquals(testDocs.get(0).getName(), testFiles[0].getName());
        Assert.assertEquals(8, testDocs.get(0).getWords().size());
    }
}
