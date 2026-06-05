import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    @Before
    public void setUp() {
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue("Shine daraalal hooson baih yostoi", mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(42);
        assertFalse("Element nemsnii daraa hooson bish", mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull("Hooson daraallin peek() -> null", mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        assertEquals("peek() ni anhni elementig butsaana",
                Integer.valueOf(10), mQueue.peek());
        assertEquals("peek() elementig ustgahgui", 2, mQueue.size());
    }

    @Test
    public void testEnqueue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertEquals(Integer.valueOf(3), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull("Hooson daraallin dequeue() -> null", mQueue.dequeue());
    }

    @Test
    public void testEnqueueReturnValue() {
        assertTrue("enqueue() ni true butsaana", mQueue.enqueue(5));
    }

    @Test
    public void testSize() {
        assertEquals(0, mQueue.size());
        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());
        mQueue.enqueue(2);
        assertEquals(2, mQueue.size());
        mQueue.dequeue();
        assertEquals(1, mQueue.size());
    }

    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        mQueue.clear();
        assertTrue("clear() daraa hooson", mQueue.isEmpty());
        assertEquals("clear() daraa size 0", 0, mQueue.size());
        assertNull("clear() daraa peek() -> null", mQueue.peek());
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");
            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                mQueue.enqueue(input);
            }
            for (Integer result : correctResult) {
                assertEquals(result, mQueue.dequeue());
            }
        }
    }

    @Test
    public void testEnsureCapacityExpansion() {
        for (int i = 0; i < 15; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(15, mQueue.size());
        for (int i = 0; i < 15; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
    }

    @Test
    public void testEnsureCapacityWithWraparound() {
        for (int i = 0; i < 5; i++)
            mQueue.enqueue(i);
        for (int i = 0; i < 5; i++)
            mQueue.dequeue();

        for (int i = 10; i < 20; i++)
            mQueue.enqueue(i);

        mQueue.enqueue(99);

        assertEquals(11, mQueue.size());
        for (int i = 10; i < 20; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
        assertEquals(Integer.valueOf(99), mQueue.dequeue());
    }

    @Test
    public void testCircularBehavior() {
        for (int i = 0; i < 8; i++)
            mQueue.enqueue(i);
        for (int i = 0; i < 6; i++)
            mQueue.dequeue();
        mQueue.enqueue(100);
        mQueue.enqueue(200);
        assertEquals(Integer.valueOf(6), mQueue.dequeue());
        assertEquals(Integer.valueOf(7), mQueue.dequeue());
        assertEquals(Integer.valueOf(100), mQueue.dequeue());
        assertEquals(Integer.valueOf(200), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testMultipleClearOperations() {
        for (int i = 0; i < 5; i++)
            mQueue.enqueue(i);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        mQueue.enqueue(42);
        assertEquals(Integer.valueOf(42), mQueue.peek());
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testInterleavedEnqueueDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.dequeue();
        mQueue.enqueue(3);
        mQueue.dequeue();
        mQueue.enqueue(4);
        assertEquals(2, mQueue.size());
        assertEquals(Integer.valueOf(3), mQueue.dequeue());
        assertEquals(Integer.valueOf(4), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }
}