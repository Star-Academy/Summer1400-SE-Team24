import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;

public class Tester {
    @Test
    public void testFileReaderSize() {
        IFileReader fileReader = new FileReader();
        try {
            var files = fileReader.readFiles("docs");
            Assert.assertEquals(files.size(), 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
