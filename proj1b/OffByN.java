public class OffByN implements CharacterComparator {
    private int k;

    /*Constructor of OffByN class*/
    public OffByN(int N){
        k = N;
    }

    public boolean equalChars(char x, char y){
        if(x - y == k  || x - y == -k){
            return true;
        }
        else{
            return false;
        }
    }
}
