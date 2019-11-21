public class HorribleSteve {
    public static void main(String [] args) {
        int a = 129;
        int b = 129;
        System.out.print(Flik.isSameNumber(a, b));
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
