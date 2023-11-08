package gui;

import be.History;
import be.SortWords;
import bll.ILogicProcessor;
import bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;

public class Model {

    private ObservableList<String> words;
    private ObservableList<String> awords;
    private ObservableList<String> nwords;
    private ObservableList<String> history;
    private ILogicProcessor logic;
    private String searchedWord;
    private History historyManipulator;
    private final String isResult= " and found results";
    private final String noResult = " and found no results";
    private final String prefix = "Search for ";


    public Model() {
        this.logic= new Logic();
        this.historyManipulator = logic.getHistory();
        this.words = FXCollections.observableArrayList();
        this.awords = FXCollections.observableArrayList();
        this.nwords = FXCollections.observableArrayList();
        this.history = FXCollections.observableArrayList();
        setHistory();
        this.searchedWord="";
        populateWords();
        populateAWords();
        populateNwords();
    }



    public ObservableList<String> getAllWords(){
        return this.words;
    }
    public ObservableList<String> getNwords() {
        return nwords;
    }

    public ObservableList<String> getAwords() {
        return awords;
    }



    private void populateWords(){
        this.words.addAll(logic.getAllWords());
    }

    private void populateAWords(){
        this.awords.setAll(logic.sortWords(SortWords.A.getValue(),this.words));
    }
    private void populateNwords(){
        this.nwords.setAll(logic.sortWords(SortWords.N.getValue(), this.words));
    }


    public String getSearchedWord() {
        return searchedWord;
    }

    public boolean getSerchedWord(String word){
        if(word.isEmpty()){
             return false;
        }
        String result = logic.searchWord(word,this.words);
        if(result!=null){
            this.searchedWord=result;
            addToHistory(word,true);
            setHistory();
            return true;
        }
       addToHistory(word,false);
       setHistory();
        System.out.println(this.history.size());
        return false;
    }

    public ObservableList<String> getHistory() {
        return history;
    }

    public void setHistory(){
     this.history.setAll(this.historyManipulator.getHistory());
    }

    private void addToHistory(String word, boolean present){
        if(present){
            historyManipulator.addToHistory(prefix + "'" + word + "'" + isResult  );
            return;
        }
        historyManipulator.addToHistory(prefix + "'" + word + "'" + noResult  );
    }

    public void clearHistory(){
        this.historyManipulator.clearHistory();
        setHistory();
    }






}
