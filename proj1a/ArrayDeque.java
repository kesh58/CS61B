public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int f = 4;
    private int l = 5;
    private double usage;

    //Array Deque constructor;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[f] = x;
        f = f - 1;
        if (f < 0) {
            f = items.length - 1;
        }
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[l] = x;
        l = l + 1;
        if (l == items.length) {
            l = 0;
        }
        size += 1;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            int x = f + 1 + i;
            if (x >= items.length) {
                x = x - items.length;
            }
            System.out.print(items[x] + " ");

        }
    }


    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            if (f == items.length - 1) {
                f = -1;
            }
            T x = items[f + 1];
            items[f + 1] = null;
            f = f + 1;
            size -= 1;
            usage = (double) size / (double) items.length;
            if (usage < 0.25 && items.length > 16) {
                resize(items.length / 2);
            }
            return x;
        }
    }

    /**Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            if (l == 0) {
                l = items.length;
            }
            T x = items[l - 1];
            items[l - 1] = null;
            l = l - 1;
            size -= 1;
            usage = (double) size / (double) items.length;
            if (usage < 0.25 && items.length >= 16) {
                resize(items.length / 2);
            }
            return x;
        }
    }

    //Gets the item at the given index.
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int x = index + f + 1;
        if (x >= items.length) {
            x = x - items.length;
        }
        return items[x];
    }

    /** resize up or resize down the original ArrayList.*/
    private void resize(int capacity) {

        T[] a = (T[]) new Object[capacity];
        int ind = 0;

        if (f - l == items.length - 1 && size == items.length) {
            System.arraycopy(items, 0, a, 0, size);
            f = a.length - 1;
            l = size;
        } else if (f >= l || (f == l - 1 && size != 0)) {
            for (int i = 0; i < l; i += 1) {
                a[i] = items[i];
                ind = i;
            }
            l = ind + 1;
            int k = 1;
            for (int j = items.length; j > f + 1; j = j - 1) {
                a[capacity - k] = items[j - 1];
                k = k + 1;
            }
            f = capacity - k;
        } else if (size ==  items.length) {
            System.arraycopy(items, 0, a, 0, size);
            f = capacity - 1;
            l = size;
        } else {
            System.arraycopy(items, f + 1, a, 0, size);
            f = a.length - 1;
            l = size;
        }
        items = a;
    }
}
















