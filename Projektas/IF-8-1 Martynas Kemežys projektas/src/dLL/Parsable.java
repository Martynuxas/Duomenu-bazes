package dLL;

public interface Parsable<T> extends Comparable<T> {
    void parse(String dataString);
}
