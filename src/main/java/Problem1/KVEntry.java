package Problem1;

public class KVEntry {
    String key;
    String value;
    KVEntry next; // chaining

    public KVEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
