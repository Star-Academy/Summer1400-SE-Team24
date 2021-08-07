package file_handler;

import java.io.File;

import exceptions.NotDirectoryException;

public class DirectoryReader implements IDirectoryReader {

    @Override
    public File[] getFiles(String dirPath) throws NotDirectoryException {
        var path = new File(dirPath);
        if(path.isDirectory()) {
            return path.listFiles();
        }
        throw new NotDirectoryException(dirPath);
    }
    
}
