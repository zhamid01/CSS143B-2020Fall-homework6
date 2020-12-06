package Problem1;

public interface Dictionary {
    boolean isEmpty();

    void put(String key, String value);

    void remove(String key);

    String get(String key);

    boolean contains(String key);
}
