package file_handler;

import java.io.File;
import exceptions.NotADirectoryException;

public interface IDirectoryReader {
    File[] getFiles(String path) throws NotADirectoryException;
}
