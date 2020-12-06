import Problem2.Identity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PerfUtils {
    // https://www.baeldung.com/java-random-string#java8-alphabetic
    public static String randStr(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int binarySearch(ArrayList<Identity> data, String target) {
        int start = 0;
        int end = data.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            String candidate = data.get(mid).username;
            if (candidate.equals(target)) {
                return mid;
            }
            if (candidate.compareToIgnoreCase(target) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void testStringBinarySearch() {
        List<ArrayList<Identity>> inputs = getBinarySearchTestCases();

        String[] targets = {
                "a", "b", "c", "d", "x",
                "aa", "ab", "cd", "de", "xy",
                "aab", "abc", "cde", "cdf", "xyz", "123"
        };
        int[] expects = {0, 1, 2, 3, -1, 0, 1, 2, 3, -1, 0, 1, 2, 3, 4, -1};

        assertEquals(targets.length, targets.length);

        for (int i = 0; i < inputs.size(); i++) {
            int actual = binarySearch(inputs.get(i), targets[i]);
            assertEquals(String.format("case %d: array{%s}, target{%s}", i, inputs.get(i), targets[i]),
                    expects[i], actual);
        }
    }

    private List<ArrayList<Identity>> getBinarySearchTestCases() {
        List<ArrayList<Identity>> inputs = new ArrayList<>();
        Identity a = new Identity("a", randStr(10));
        Identity b = new Identity("b", randStr(10));
        Identity c = new Identity("c", randStr(10));
        Identity d = new Identity("d", randStr(10));
        ArrayList<Identity> input = new ArrayList<>(Arrays.asList(a, b, c, d));
        fillUpInputs(inputs, input);

        Identity aa = new Identity("aa", randStr(10));
        Identity ab = new Identity("ab", randStr(10));
        Identity cd = new Identity("cd", randStr(10));
        Identity de = new Identity("de", randStr(10));
        input = new ArrayList<>(Arrays.asList(aa, ab, cd, de));
        fillUpInputs(inputs, input);

        Identity aab = new Identity("aab", randStr(10));
        Identity abc = new Identity("abc", randStr(10));
        Identity cde = new Identity("cde", randStr(10));
        Identity cdf = new Identity("cdf", randStr(10));
        Identity xyz = new Identity("xyz", randStr(10));
        input = new ArrayList<>(Arrays.asList(aab, abc, cde, cdf, xyz));
        fillUpInputs(inputs, input);
        return inputs;
    }

    private void fillUpInputs(List<ArrayList<Identity>> inputs, ArrayList<Identity> input) {
        for (int i = 0; i < input.size() + 1; i++) {
            inputs.add(input);
        }
    }

    public static void addTimeToPrint(StringBuilder sb, long durationInNano) {
        sb.append(String.format("%f\t", durationInNano / 1e6));
    }

    public static void addNToPrint(StringBuilder sb, long n) {
        sb.append(String.format("%d\t", n));
    }
}
