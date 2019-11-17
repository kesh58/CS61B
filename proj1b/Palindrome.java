public class Palindrome {

    /* Convert a string to a Deque of characters.*/
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d_c = new LinkedListDeque<Character> ();
        for (int i = 0; i < word.length(); i += 1){
            d_c.addLast(word.charAt(i));
        }
        return d_c;
    }


    private boolean isPalindrome(Deque<Character> a){
        if (a.size() <= 1){
            return true;
        }
        Character a_f = a.removeFirst();
        Character a_l = a.removeLast();
        if (a_f != a_l){
            return false;
        }
        else{
            return isPalindrome(a);
        }
    }

    public boolean isPalindrome(String word){
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a);
    }

    private boolean isPalindrome(Deque<Character> a, CharacterComparator cc){
        if(a.size() <= 1){
            return true;
        }
        Character a_f = a.removeFirst();
        Character a_l = a.removeLast();
        if(!cc.equalChars(a_f, a_l)){
            return false;
        }
        else{
            return isPalindrome(a, cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> a = wordToDeque(word);
        return isPalindrome(a, cc);
    }
}
