package bll;

import be.History;
import dal.DataAccess;
import dal.FileData;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class Logic implements ILogicProcessor {
    private DataAccess dal;


    public Logic() {
        this.dal = FileData.getInstance();
    }

    @Override
    public List<String> getAllWords() {
        return dal.getWords();
    }

    @Override
    public List<String> sortWords(String value, ObservableList<String> words) {
       return  words.stream().filter(elem->elem.startsWith(value)).toList();
    }

    @Override
    public String searchWord(String word, ObservableList<String> words) {
        return words.stream().filter(elem->elem.contains(word)).findFirst().orElse(null);
    }

    @Override
    public History getHistory() {
        History historyManipulator = History.getInstance();
        try{
            historyManipulator.setHistory(dal.getHistory());
        }catch(IOException io){
            System.out.println(io.toString());
        }
        return historyManipulator;
    }


}
