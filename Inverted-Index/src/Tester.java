import java.util.ArrayList;

import org.junit.Test;

import exceptions.NotADirectoryException;
import file_handler.DirectoryReader;
import file_handler.Doc;
import file_handler.FileReader;
import file_handler.IDirectoryReader;
import junit.framework.Assert;

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
}
