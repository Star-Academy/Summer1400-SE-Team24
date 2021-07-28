package user_interface;

import java.util.Set;

import file_handler.Doc;

public interface IUserIO {
    String get();

    void printResult(Set<Doc> result);
}
