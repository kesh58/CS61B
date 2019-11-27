public class Dequelauncher {
    public static void main(String[] args) {
        LinkedListDeque<String> D1 = new LinkedListDeque<>("good");
        D1.isEmpty();
        D1.addFirst("is");
        D1.addFirst("this");
        D1.addLast("day!");
        D1.addLast("haha!");
        D1.printDeque();
        int a = D1.size();

        System.out.println(a);

        D1.removeFirst();
        D1.removeLast();

    }
}
