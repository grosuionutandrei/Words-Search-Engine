package dal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileData implements DataAccess {


    private String location = "D:\\computer_science\\sde\\words\\dictionary.txt";
    private String historyLocation = "D:\\computer_science\\sde\\words\\history.txt";

    private static FileData instance;


    private FileData() {
    }

    private FileData(String location) {
        this();
        location = location;
    }

    private FileData(String location, String history) {
        this(location);
        this.historyLocation = history;
    }

    public static FileData getInstance() {
        if (instance == null) {
            instance = new FileData();
        }
        return instance;
    }


    public List<String> getWords() {
        Path filePath = Paths.get(location);

        List<String> words;
        try {
            words = Files.readAllLines(filePath);
        } catch (IOException io) {
            System.out.println(io.toString());
            words = new ArrayList<>();
            return words;
        }
        return words;
    }

    @Override
    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        Path path = Paths.get(historyLocation);
        try {
            history = Files.readAllLines(path);
        } catch (IOException io) {
            System.out.println(io.toString());
        }
    return history;
    }

    public boolean writeToFileAppend(String content,boolean append) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.historyLocation, append))) {
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public void clearList(){
        try (FileWriter writer = new FileWriter(this.historyLocation)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
