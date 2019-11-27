package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;

    protected int capacity;

    /** return the capacity of the bounded queue. */
    public int capacity() {
        return capacity;
    }

    /** return the fillCount of the bounded queue. */
    public int fillCount() {
        return fillCount;
    }

}
