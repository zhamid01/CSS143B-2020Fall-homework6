import Problem2.Identity;

import static org.junit.Assert.assertFalse;

public class IdentityRemovalTest extends IdentityPerfTest {

    @Override
    public void runTestOfSize(int size) {
        fillEmUp(size);
        Identity targetIdentity = idsArray.get(0);
        String targetByName = targetIdentity.username;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\t", size));

        removeInArray(targetIdentity, sb);
        removeInLinkedList(targetIdentity, sb);
        removeInBST(targetByName, sb);
        removeInHashtable(targetByName, sb);

        System.out.println(sb);
    }

    private void removeInHashtable(String targetByName, StringBuilder sb) {
        long endTime;
        long durationInNano;
        long startTime;

        startTime = System.nanoTime();

        // remove in Dict
        idsDict.remove(targetByName);

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;

        // verify removal
        assertFalse(idsDict.containsKey(targetByName));

        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void removeInBST(String targetByName, StringBuilder sb) {
        long durationInNano;
        long startTime;
        long endTime;

        startTime = System.nanoTime();

        idsTree.remove(targetByName);

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;

        // verify removal
        assertFalse(idsTree.containsKey(targetByName));

        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void removeInLinkedList(Identity targetIdentity, StringBuilder sb) {
        long startTime;
        long endTime;
        long durationInNano;

        startTime = System.nanoTime();

        idsLinkedList.remove(0);

        endTime = System.nanoTime();
        durationInNano = endTime - startTime;

        // verify removal
        assertFalse(idsLinkedList.contains(targetIdentity));

        PerfUtils.addTimeToPrint(sb, durationInNano);
    }

    private void removeInArray(Identity targetIdentity, StringBuilder sb) {
        long startTime = System.nanoTime();

        idsArray.remove(0);

        long endTime = System.nanoTime();
        long durationInNano = endTime - startTime;

        // verify removal
        assertFalse(idsArray.contains(targetIdentity));

        PerfUtils.addTimeToPrint(sb, durationInNano);
    }
}
