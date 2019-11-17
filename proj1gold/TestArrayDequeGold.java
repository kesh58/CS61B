import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {


    @Test
    public void testStudentArray(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                good1.addLast(i);
            } else {
                sad1.addFirst(i);
                good1.addFirst(i);
            }
        }
        for (int j = 0; j < 100; j += 1) {
            double numberBetweenZeroAndOne_1 = StdRandom.uniform();

            if (numberBetweenZeroAndOne_1 < 0.5) {
                assertEquals(good1.removeFirst(), sad1.removeFirst());
            } else {
                assertEquals(good1.removeLast(), sad1.removeLast());
            }
        }

    }
}
