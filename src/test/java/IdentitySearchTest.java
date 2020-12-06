import Problem2.Identity;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class IdentitySearchTest extends IdentityPerfTest {

    @Override
    public void runTestOfSize(int size) {
        fillEmUp(size);
        Identity targetIdentity = idsArray.get(idsArray.size() - 1);
        String target = targetIdentity.username;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\t", size));

        searchInArray(targetIdentity, target, sb);
        searchInLinkedList(targetIdentity, target, sb);
        searchInBST(targetIdentity, target, sb);
        searchInHashtable(targetIdentity, target, sb);

        System.out.println(sb);
    }

    private void searchInHashtable(Identity targetIdentity, String target, StringBuilder sb) {
        long endTime;
        long durationInNano;
        Identity resultId;
        long startTime;

        startTime = System.nanoTime();

        // search in Dict
        resultId = idsDict.get(target);

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;
        assertEquals(targetIdentity, resultId);
        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void searchInBST(Identity targetIdentity, String target, StringBuilder sb) {
        long durationInNano;
        long startTime;
        long endTime;

        startTime = System.nanoTime();

        // search in BST
        Identity resultId = idsTree.get(target);

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;
        assertEquals(targetIdentity, resultId);
        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void searchInLinkedList(Identity targetIdentity, String target, StringBuilder sb) {
        long startTime;
        long endTime;
        long durationInNano;

        // search in List
        Iterator<Identity> iNode = idsLinkedList.iterator();
        Identity id = idsLinkedList.get(0); // initialized just so Intellij warning would shush
        startTime = System.nanoTime();

        // search in List
        while (iNode.hasNext()) {
            id = iNode.next();
            if (id.username.equals(target)) {
                break;
            }
        }

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;
        assertEquals(targetIdentity, id);
        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void searchInArray(Identity targetIdentity, String target, StringBuilder sb) {
        long startTime = System.nanoTime();

        // search in Array
        int result = PerfUtils.binarySearch(idsArray, target);

        long endTime = System.nanoTime();
        long durationInNano = endTime - startTime;
        assertEquals(targetIdentity, idsArray.get(result));
        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

}
