package file_handler;

import java.io.File;

import exceptions.NotADirectoryException;

public class DirectoryReader implements IDirectoryReader {

    @Override
    public File[] getFiles(String dirPath) throws NotADirectoryException {
        var path = new File(dirPath);
        if(path.isDirectory()) {
            return path.listFiles();
        }
        else throw new NotADirectoryException(dirPath);
    }
    
}
