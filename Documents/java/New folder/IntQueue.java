/**
 * A minimal interface for an integer queue.
 */
public interface IntQueue {
    void clear();

    Integer dequeue();

    boolean enqueue(Integer value);

    boolean isEmpty();

    Integer peek();

    int size();
}
