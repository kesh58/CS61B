/** test the if two characters are N apart of the other.
 * @author keshang */

public class OffByN implements CharacterComparator {
    /**private integer k.*/
    private int k;

    /**Constructor of OffByN class.
     * @param N The input integer N*/
    public OffByN(int N) {
        k = N;
    }

    /** A method that check if the two characters are apart by N.
     * @return boolean
     * @param x the first character
     * @param y the second character*/
    public boolean equalChars(char x, char y) {
        return x - y == k || x - y == -k;
    }
}
