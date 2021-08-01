package tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;
import exceptions.NotADirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;
import org.junit.Assert;

public class Tester {
    @Test
    public void fileReadingTest() throws NotADirectoryException {
        IDirectoryReader reader = new DirectoryReader();
        var files = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\docs");
        Assert.assertEquals(files.length, 1000);
        
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
    public void getFilesDocsTest() throws NotADirectoryException, FileNotFoundException {
        IDirectoryReader reader = new DirectoryReader();
        var files = reader.getFiles("C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\test\\docs");
        FileReader fileReader = new FileReader();
        var docs = fileReader.getFilesDocs(files);

        Assert.assertEquals(files.length, docs.size());
        Assert.assertEquals(docs.get(0).getName(), files[0].getName());
        Assert.assertEquals(docs.get(0).getWords().size(), 8);
    }
}
