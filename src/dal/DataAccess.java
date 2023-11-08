package dal;

import be.History;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DataAccess {
    List<String> getWords();
    List<String> getHistory() throws IOException;
}
