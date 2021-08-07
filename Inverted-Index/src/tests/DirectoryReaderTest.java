package tests;

import java.io.File;
import org.junit.Test;

import exceptions.NotDirectoryException;

import org.junit.Assert;
import org.junit.Before;

import file_handler.DirectoryReader;

public class DirectoryReaderTest {

    private final int FILES_COUNT = 3;
    private final String[] TEST_FILE_NAMES = new String[] {"57110", "59483", "59519"};
    private final String TEST_DOCS_PATH = "C:\\Repos\\Summer1400-SE-Team24\\Inverted-Index\\src\\tests\\docs";
    private File[] files;

    @Before
    public void readingFiles() throws NotDirectoryException {
        var directoryReader = new DirectoryReader();
        files = directoryReader.getFiles(TEST_DOCS_PATH);
    }

    @Test
    public void readedFilesCountTest() {

        Assert.assertEquals(FILES_COUNT, files.length);
    }

    @Test
    public void readedFilesNamesTest() {
        for (int i = 0; i < TEST_FILE_NAMES.length; i++) {
            Assert.assertEquals(TEST_FILE_NAMES[i], files[i].getName());
        }
    }
}
