package file_handler;

import java.io.File;
import exceptions.NotDirectoryException;

public interface IDirectoryReader {
    File[] getFiles(String path) throws NotDirectoryException;
}
