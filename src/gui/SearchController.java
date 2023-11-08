package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    Model model;
    @FXML
    private ListView allWords;
    @FXML
    private ListView wordsWithA;
    @FXML
    private ListView wordsWithN;
    @FXML
    private TextField wordInput;
    @FXML
    private Label searchResult;
    @FXML
    private Label totalWords;
    @FXML
    private ListView history;
    public void clearHistory(ActionEvent event) {
        model.clearHistory();
    }

    public void searchForWord(ActionEvent event) {
       String input= this.wordInput.getText();
        boolean ispresent = model.getSerchedWord(input);
        if(ispresent){
            searchResult.setText(model.getSearchedWord());
        }else{
            this.searchResult.setText(input+ " not present");
        }

        ObservableList<String> items = allWords.getItems();
        for (String item: items){
            if (item.equals(model.getSearchedWord())){
                allWords.getSelectionModel().select(item);
                allWords.getFocusModel().focus(allWords.getSelectionModel().getSelectedIndex());
                allWords.scrollTo(allWords.getSelectionModel().getSelectedIndex()-10);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model =new Model();
        this.allWords.setItems(model.getAllWords());
        this.wordsWithA.setItems(model.getAwords());
        this.wordsWithN.setItems(model.getNwords());
        this.totalWords.setText(String.valueOf(model.getAllWords().size()-1));
        this.history.setItems(model.getHistory());


    }




}
