import java.util.Arrays;

/**
 * ArrayIntQueue implementation using a circular array.
 */
public class ArrayIntQueue implements IntQueue {

    private int[] elementData;
    private int head;
    private int size;
    private static final int INITIAL_SIZE = 10;

    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        Arrays.fill(elementData, 0);
        size = 0;
        head = 0;
    }

    /** {@inheritDoc} */
    @Override
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        int value = elementData[head]; // Integer-ruu auto-box hiine
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public boolean enqueue(Integer value) {
        if (value == null)
            return false; // Null check nemsen
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} */
    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData[head];
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return size;
    }

    /**
     * Circular array-iin elementuudiig shine massiv-ruu zov daraallaar huulah
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            // CMU Recitation 2 дээрх стандарт өргөтгөх хэмжээ: oldCapacity * 2 + 1
            int newCapacity = oldCapacity * 2 + 1;
            int[] newData = new int[newCapacity];

            // Elementuudiig shine massiv-iin 0 index-ees ehlen daraallaar huulah
            for (int i = 0; i < size; i++) {
                // (head + i) % oldCapacity ni odoogiin element-iin index-iig olno
                newData[i] = elementData[(head + i) % oldCapacity];
            }

            elementData = newData;
            head = 0; // Shine massiv-t head dandaa 0-ees ehelne
        }
    }

    public static void main(String[] args) {
        ArrayIntQueue q = new ArrayIntQueue();
        q.enqueue(10);
        q.enqueue(20);
        System.out.println(q.dequeue()); // 10
        System.out.println(q.peek()); // 20
    }
}