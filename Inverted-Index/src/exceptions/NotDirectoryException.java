package exceptions;

public class NotDirectoryException extends Exception {
    public NotDirectoryException(String path) {
        super("Specified path ("+ path +") is not a directory");
    }
}
