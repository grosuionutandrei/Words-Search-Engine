package bll;

import be.History;
import javafx.collections.ObservableList;

import java.util.List;

public interface ILogicProcessor {
    List<String> getAllWords();

    List<String> sortWords(String value, ObservableList<String> words);

    String searchWord(String word, ObservableList<String> words);

    History getHistory();
}
