package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import file_handler.Doc;
import file_handler.FileReader;

public class FileReaderTest {

    private final String BASE_PATH = "C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs\\";
    private final String TEST_FILE_NAME = "57110";
    private final String TEST_FILE_PATH1 = BASE_PATH + TEST_FILE_NAME;
    
    private final List<String> TEST_FILE_WORDS = Arrays.asList("i","have","a","42","yr","old","male","friend");
    private FileReader fileReader;

    @Before
    public void init() {
        fileReader = new FileReader();
    }

    @Test
    public void fileWordsReadingTest() throws FileNotFoundException {
        var file = new File(TEST_FILE_PATH1);
        var words = fileReader.getFilesWords(file);

        Assert.assertEquals(TEST_FILE_WORDS, words);
    }

    @Test
    public void fileConvertToDocTest() throws FileNotFoundException {
        var docs = fileReader.getFilesDocs(new File[] {new File(TEST_FILE_PATH1)});

        Assert.assertEquals(new ArrayList<>() {
            {add(new Doc(TEST_FILE_NAME, TEST_FILE_WORDS));}
        }, docs);
    }

    @After
    public void dispose() {
        fileReader = null;
    }
    
}
