package be;

import dal.FileData;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<String> history;
    private static History instance;
    private int counter = 0;

    public static History getInstance() {
        if (instance == null) {
            instance = new History();
        }
        return instance;
    }


    private History() {
        this.history = FileData.getInstance().getHistory();
    }


    public List<String> getHistory() {
        return history;
    }

    public void addToHistory(String value) {
        boolean added = this.getHistory().add(value);
        if (added) {
            this.counter++;
        }
        if (counter % 10 == 0) {
            for (int i = counter - 10; i < counter; i++) {
                FileData.getInstance().writeToFileAppend(history.get(i),true);
            }
        }
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
    public void clearHistory(){
        this.history=new ArrayList<>();
     FileData.getInstance().clearList();
    }

}
