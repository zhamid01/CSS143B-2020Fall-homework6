import Problem1.ArrayDictionary;
import Problem1.Dictionary;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class DictTest {

    private Map<String, String> data;

    @Test
    public void playAround() {
        Dictionary dict = new ArrayDictionary(10);

        dict.put("Jerry", "467");
        dict.put("Alex", "132");
        dict.put("Sam", "234");
        dict.put("Ron", "999");
        dict.put("Tim", "012");
        dict.put("Katie", "549");
        dict.put("Joe", "333");

        System.out.println(dict);
        System.out.println("Does Alex exist in the DB? " + dict.contains("Alex"));
        System.out.println("Does Andrew exist in the DB? " + dict.contains("Andrew"));
    }

    @Before
    public void setUp() {
        data = new HashMap<>() {{
            put("alex", "123");
            put("sam", "456");
            put("tony", "789");
            put("ron", "451");
            put("katie", "1122");
            put("al", "00112233");
            put("b", "18283");
            put("tom", "9999");
        }};
    }

    @Test
    public void testRemove() {
        Dictionary dict = getDictionary();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            dict.put(entry.getKey(), entry.getValue());
        }

        Set<String> removedKeys = new HashSet<>();

        for (String key : data.keySet()) {
            dict.remove(key);

            // verify the correct item was removed
            assertFalse(dict.contains(key));

            // mark key as removed
            removedKeys.add(key);

            // verify other items still exists
            for (Map.Entry<String, String> e2 : data.entrySet()) {
                if (!removedKeys.contains(e2.getKey())) {
                    assertTrue(dict.contains(e2.getKey()));
                    assertEquals(dict.get(e2.getKey()), e2.getValue());
                }
            }
        }
    }

    @Test
    public void testContainsAndGet() {
        Dictionary dict = getDictionary();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            dict.put(entry.getKey(), entry.getValue());
        }

        for (String key : data.keySet()) {
            assertTrue(dict.contains(key));
            assertEquals(data.get(key), dict.get(key));
        }

        assertFalse(dict.contains("notexist"));
        assertFalse(dict.contains("n"));
        assertFalse(dict.contains("ab"));
        assertFalse(dict.contains("ron1"));
        assertFalse(dict.contains("xtom5"));
    }

    @Test
    public void testPut() {
        Dictionary dict = getDictionary();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            dict.put(entry.getKey(), entry.getValue());
        }

        System.out.println(dict);

        String expect = "dictionary[0] = null\n" +
                "dictionary[1] = {(b, 18283)}\n" +
                "dictionary[2] = {(al, 00112233)}\n" +
                "dictionary[3] = {(ron, 451)(tom, 9999)(sam, 456)}\n" +
                "dictionary[4] = {(tony, 789)(alex, 123)}\n" +
                "dictionary[5] = {(katie, 1122)}\n";

        assertEquals(expect, dict.toString());
    }

    private Dictionary getDictionary() {
        int size = 0;
        for (String key : data.keySet()) {
            if (size < key.length()) {
                size = key.length();
            }
        }
        return new ArrayDictionary(size + 1);
    }
}