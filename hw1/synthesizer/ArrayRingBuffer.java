package synthesizer; // Make sure to make this class a part of the synthesizer package


import java.util.Iterator;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    /** Create a self-defined Iterator. */
    public class KeyIterator implements Iterator {

        private int position;

        public KeyIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < fillCount;
        }

        public T next() {
            T returnValue = rb[first + position];
            position += 1;
            return returnValue;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last += 1;
            if (last == capacity()) {
                last = 0;
            }
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            T rmv = rb[first];
            first += 1;
            if (first == capacity()) {
                first = 0;
            }
            fillCount -= 1;
            return rmv;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (fillCount == 0) {
            throw new RuntimeException("Nothing left.");
        } else {
            return rb[first];
        }
    }

    // When you get to part 5, implement the needed code to support iteration.

}
