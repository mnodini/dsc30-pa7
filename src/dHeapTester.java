import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {

    dHeap<Integer> test1;
    dHeap<Integer> test2;
    dHeap<Integer> minHeap;
    dHeap<Integer> test3;
    dHeap<Integer> test4;
    dHeap<Integer> test5;
    dHeap<Integer> test6;
    dHeap<Integer> test7;
    dHeap<Integer> test8;
    dHeap<Integer> test9;

    @org.junit.Before
    public void setUp() throws Exception {
        test1 = new dHeap(15,7,true);
        test1.add(1);
        test1.add(2);
        test1.add(3);
        test1.add(4);
        test1.add(5);
        test1.add(6);
        test1.add(7);
        test1.add(8);
        //test2
        test2 = new dHeap(4, 10, true);
        test3 = new dHeap(2, 10, true);
        test4 = new dHeap();
        test5 = new dHeap();
        test6 = new dHeap();
        test7 = new dHeap<>(7);
        test8 = new dHeap<>(8);
        test9 = new dHeap<>(9);
        //minHeap
        minHeap = new dHeap<>(7, 5, false);
        for(int i = 10; i >= 0; i--){
            minHeap.add(i);
        }
    }

    @org.junit.Test
    public void size() {
        assertEquals(8,test1.size());
        test1.add(9);
        assertEquals(9,test1.size());
        test1.add(9);
        assertEquals(10,test1.size());
        test1.add(9);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void add() {
        test1.add(null);
        test1.add(9);
        assertEquals(new Integer(9), test1.element());
        test1.add(10);
        assertEquals(new Integer(10), test1.element());
        test1.add(11);
        assertEquals(new Integer(11), test1.element());
    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void remove() {
        assertEquals(new Integer(8),test1.remove());
        assertEquals(new Integer(7),test1.remove());
        assertEquals(new Integer(6),test1.remove());
        assertEquals(new Integer(5),test1.remove());
        assertEquals(new Integer(4),test1.remove());
        assertEquals(new Integer(3),test1.remove());
        assertEquals(new Integer(2),test1.remove());
        assertEquals(new Integer(1),test1.remove());
        assertEquals(0,test1.size());
        for(int i = 0; i < 50; i++){
            test1.add(i);
        }
        for(int i = 49; i >= 0; i--){
            assertEquals(new Integer(i),test1.remove());
        }
        for(int i = 0; i <= 10; i++){
            assertEquals(new Integer(i), minHeap.remove());
        }
        test1.clear();
        test1.remove();
    }

    @org.junit.Test
    public void clear() {
        test1.clear();
        assertEquals(0,test1.size());
        test1.add(5);
        test1.clear();
        assertEquals(0,test1.size());
        test1.add(7);
        test1.add(8);
        test1.clear();
        assertEquals(0,test1.size());

    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void element() {
        assertEquals(new Integer(8),test1.element());
        test1.remove();
        assertEquals(new Integer(7),test1.element());
        test1.remove();
        assertEquals(new Integer(6),test1.element());
        test1.clear();
        test1.element();
    }

    @Test
    public void alternating(){
        for(int i = 0; i <= 10; i++){
            test2.add(i);
        }
        for(int i = 10; i >= 5; i--){
            assertEquals(new Integer(i), test2.remove());
        }
        for(int i = 20; i <= 40; i++){
            test2.add(i);
        }
        for(int i = 40; i >= 20; i--){
            assertEquals(new Integer(i), test2.remove());
        }
    }
}