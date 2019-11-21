public class NLList {

    /** IntNode class as the base of the naive list.*/
    private class IntNode{
        public int item;
        public IntNode next;
        public IntNode (int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x){
        first = new IntNode(x, first);
    }

    public void reverse() {
        if (first == null || first.next == null){
            return;
        }
        IntNode Ptr = first.next;
        first.next = null;
        while (Ptr.next != null) {
            IntNode N = Ptr.next;
            Ptr.next = first;
            first = Ptr;
            Ptr = N;
        }
        Ptr.next = first;
        first = Ptr;
    }


}
