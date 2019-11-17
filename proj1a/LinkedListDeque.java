public class LinkedListDeque<Item> implements Deque<Item> {
    private class TNode {
        private TNode prev;
        private Item item;
        private TNode next;

        private TNode(TNode x, Item i, TNode n) {
            prev = x;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    //Create an empty Deque.
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //Create a Deque with one item.
    public LinkedListDeque(Item x) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    //Add an item type Item to front of the deque.
    @Override
    public void addFirst(Item i) {
        sentinel.next = new TNode(sentinel, i, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    //Add an item type Item to end of the deque.
    @Override
    public void addLast(Item i) {
        sentinel.prev = new TNode(sentinel.prev, i, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1;
    }

    //Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    //Print the items in the deque from first to last, separated by a space.
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i = i + 1) {
            System.out.print(this.get(i) + " ");
        }
    }

    //Removes and returns the item at the front of the deque. If no such item, return null.
    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            Item first_rem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size = size - 1;
            return first_rem;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        else {
            Item last_rem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size = size - 1;
            return last_rem;
        }
    }

    //Gets the item at the given index.
    @Override
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        else {
            TNode index_deq = sentinel.next;
            while (index > 0) {
                index_deq = index_deq.next;
                index = index - 1;
            }
            return index_deq.item;
        }
    }

    //getRecursive helper function that get the index form a given TNode N.
    private Item getRecursive(TNode n, int index) {
        if (index == 0) {
            return n.item;
        }
        else {
            return getRecursive(n.next, index - 1);
        }
    }

    //return the item at the given index.
    public Item getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        else {
            return getRecursive(sentinel.next, index);
        }
    }
}






















