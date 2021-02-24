import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {

    MyPriorityQueue<Integer> test1;

    @Before
    public void setUp() throws Exception{
        test1 = new MyPriorityQueue<>(10);
        for(int i = 0; i < 10; i++){
            test1.offer(i);
        }
    }

    @Test
    public void offer() {
    }

    @Test
    public void poll() {
        for(int i = 9; i >= 0; i--){
            assertEquals(new Integer(i), test1.poll());
        }
    }

    @Test
    public void clear() {
        test1.clear();
        assertTrue(test1.isEmpty());
    }

    @Test
    public void peek() {
        assertEquals(new Integer(9), test1.peek());
        assertEquals(new Integer(9), test1.peek());
    }

    @Test
    public void isEmpty() {
        assertFalse(test1.isEmpty());
    }
}