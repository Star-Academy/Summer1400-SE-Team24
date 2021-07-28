package exceptions;

public class NotADirectoryException extends Exception {
    public NotADirectoryException(String path) {
        super("Specified path ("+ path +") is not a directory");
    }
}
