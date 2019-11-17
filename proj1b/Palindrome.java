/** test the if the given string is palindrome.
 * @author keshang */

public class Palindrome {

    /** Convert a string to a Deque of characters.
     * @param word  input string word
     * @return  boolean*/
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deqc = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            deqc.addLast(word.charAt(i));
        }
        return deqc;
    }

    /**helper method, test if the input deque is palindrome.
     * @return  boolean
     * @param a  input deque*/
    private boolean isPalindrome(Deque<Character> a) {
        if (a.size() <= 1) {
            return true;
        }
        Character charaf = a.removeFirst();
        Character charal = a.removeLast();
        if (charaf != charal) {
            return false;
        } else {
            return isPalindrome(a);
        }
    }

    /** method that test if the input string is palindrome.
     * @return  boolean
     * @param word  input string*/
    public boolean isPalindrome(String word) {
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a);
    }

    /** helper method, test if the input deque is palindrome
     * based on the character comparator.
     * @param a input deque
     * @param cc input character comparator
     * @return boolean
     */
    private boolean isPalindrome(Deque<Character> a, CharacterComparator cc) {
        if (a.size() <= 1) {
            return true;
        }
        Character charaf = a.removeFirst();
        Character charal = a.removeLast();
        if (!cc.equalChars(charaf, charal)) {
            return false;
        } else {
            return isPalindrome(a, cc);
        }
    }

    /** test if the string is palindrome or not based on
     * the character comparator.
     * @param word input string
     * @param cc input cc
     * @return boolean
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a, cc);
    }
}
