/*
 * Name: Michael Nodini
 * PID:  A16007357
 */

import java.util.*;

/**
 * Implementation of a dHeap
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private static final int DEFAULT_SIZE = 6;
    private static final int DEFAULT_BRANCH = 2;
    private static final int ARRAY_SCALE = 2;

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        d = DEFAULT_BRANCH;
        nelems = 0;
        isMaxHeap = true;

    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        heap = (T[]) new Comparable[heapSize];
        d = DEFAULT_BRANCH;
        nelems = 0;
        isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if(d < 1){
            throw new IllegalArgumentException();
        }
        heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems = 0;
    }

    /**
     * Returns size of heap
     * @return Number of elements in heap
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Adds data to heap
     * @param data data to be added
     * @throws NullPointerException if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if(data == null){
            throw new NullPointerException();
        }
        resize();
        heap[nelems] = data;
        if(nelems != 0){
            bubbleUp(nelems);
        }
        nelems += 1;
    }

    /**
     * Returns and removes the root element from the heap
     * @return Root element
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        T data = element();
        //Swap the first and last element
        swap(0,nelems-1);
        nelems -= 1;
        //Trickle down the new root
        trickleDown(0);

        return data;
    }

    /**
     * Clears all elements in the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        int size = heap.length;
        heap = (T[]) new Comparable[size];
        this.nelems = 0;
    }

    /**
     * Returns the root element in the heap
     * @return root element
     * @throws NoSuchElementException if the heap is empty
     */
    public T element() throws NoSuchElementException {
        if(this.nelems == 0){
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * Helper for remove
     * @param index element that is being moved down
     */
    private void trickleDown(int index) {
        //Min-Heap Change
        int minheap = 1;
        if(!isMaxHeap){
            minheap = -1;
        }
        int[] child_idxs = child(index);
        //Base Cases
        //If value @ index is a leaf
        if(child_idxs[0] >= nelems){
            return;
        }
        int max_child_idx = child_idxs[0];
        //If value @ index has no children greater than it
        boolean greaterThan = true;
        for(int i = 0; i < child_idxs.length; i++){
            //Prevents ArrayOutOfBounds and Checks if parent is greater than children
            if(child_idxs[i] < nelems && heap[index].compareTo(heap[child_idxs[i]]) * minheap < 0){
                //If there is a greater child
                greaterThan = false;
                //Save the idx of greatest child
                if(heap[max_child_idx].compareTo(heap[child_idxs[i]]) * minheap < 0){
                    max_child_idx = child_idxs[i];
                }
            }
        }
        //Base Case
        if(greaterThan){
            return;
        }
        //Continue recursion
        else{
            swap(index, max_child_idx);
            trickleDown(max_child_idx);
        }
    }

    /**
     * Helper for add
     * @param index element that is being moved up
     */
    private void bubbleUp(int index) {
        //Base Case
        if(index == 0){
            return;
        }
        //Get values for child/parent
        T child = heap[index];
        int p_idx = parent(index);
        T parent = heap[p_idx];
        T temp;
        //If min-heap compare the parent to the child instead
        if(!isMaxHeap){
            temp = child;
            child = parent;
            parent = temp;
        }
        //Determine whether swap is required and continue recursion
        if(child.compareTo(parent) > 0){
            swap(index,p_idx);
            bubbleUp(p_idx);
        }
    }

    /**
     * Doubles the size of the array before adding if it's full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        if(nelems == heap.length){
            T[] new_heap = (T[]) new Comparable[heap.length*ARRAY_SCALE];
            System.arraycopy(heap, 0, new_heap, 0, heap.length);
            heap = new_heap;
        }
    }

    /**
     * Finds the parent index
     * @param index of child
     * @return parent index
     */
    private int parent(int index) {
        return (index-1)/d;
    }

    /**
     * Finds the children's indices
     * @param index of parent
     * @return child indices
     */
    private int[] child(int index){
        int[] children = new int[d];
        for(int i = 0; i < children.length; i++){
            children[i] = d * index + i+1;
        }
        return children;
    }

    /**
     * Swaps two elements in heap
     * @param idx1
     * @param idx2
     */
    private void swap(int idx1, int idx2){
        T temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;
    }

    /**
     * Helper method for testing
     */
    private void heapPrint(){
        String heap = "[";
        for(int i = 0; i < nelems; i++){
            heap += this.heap[i].toString() + " ";
        }
        heap += "]";
        System.out.println(heap);
    }

}
