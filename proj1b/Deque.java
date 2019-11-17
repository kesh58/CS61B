public interface Deque<Item> {
    public void addFirst(Item i);


    //Add an item type T to end of the deque.
    public void addLast(Item i);

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty();

    //Returns the number of items in the deque.
    public int size();

    //Print the items in the deque from first to last, separated by a space.
    public void printDeque();

    //Removes and returns the item at the front of the deque. If no such item, return null.
    public Item removeFirst();

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public Item removeLast();

    //Gets the item at the given index.
    public Item get(int index);

}
