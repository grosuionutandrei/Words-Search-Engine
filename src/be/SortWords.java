package be;

public enum SortWords {
A("a"),N("n");
private String value;
    SortWords(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
