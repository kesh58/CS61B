public class ArrayLauncher {
    public static void main(String[] args) {
        ArrayDeque<Integer> D1 = new ArrayDeque<>();

        D1.addFirst(0);
        D1.removeLast();
        D1.addLast(2);
        D1.addFirst(3);
        D1.get(1);
        D1.get(1);
        D1.addFirst(6);
        D1.removeLast();
        D1.addLast(8);
        D1.get(2);
        D1.addLast(10);
        D1.addLast(11);
        D1.get(3);
        D1.addLast(13);
        D1.addFirst(14);
        D1.addLast(15);
        D1.get(4);
        D1.removeLast();
        D1.addFirst(18);
        D1.addFirst(19);
        D1.removeFirst();
        D1.removeLast();

        /**
        D1.addLast(10);
        D1.addLast(11);
        D1.addLast(12);
        D1.addLast(13);
        D1.addLast(14);
        D1.addLast(15);
        D1.addLast(16);
        D1.addLast(17);
        D1.addLast(18);
        D1.addLast(19);
        */
    }
}
