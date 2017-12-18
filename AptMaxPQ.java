/******************************************************************************
 *  Compilation:  javac MaxPQ.java
 *  Execution:    java MaxPQ < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *  
 *  Generic max priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order,
 *  but the generic Key type must still be Comparable.
 *
 *  % java MaxPQ < tinyPQ.txt 
 *  Q X P (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


/*
Adapted from the MaxPQ.java implementation provided on the course webiste. This is a MaxPQ meant specifically for square footage.
*/

public class AptMaxPQ{
    private Apt[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private boolean cityOrNot;           // boolean determining whether or not to insert into a heap for a certain city or not

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public AptMaxPQ(boolean cityOrRegular) {
        pq = new Apt[255];
        n = 0;
        cityOrNot = cityOrRegular;
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Apt max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize() {
        Apt[] temp = new Apt[n*2];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the new key to add to this priority queue
     */
    public void insert(Apt x) {

        if(x==null){
            return;
        }
        n++;
        if(n>pq.length){
            resize();
        }

        // add x, and percolate it up to maintain heap invariant
        pq[n] = x;
        swim(n);
    }

    public void updateValue(int i){
        if (i < 0){
            throw new IndexOutOfBoundsException();
        }
        swim(i);
        sink(i);
    }

    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public void del(int j) {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        if (j < 0){
            throw new IndexOutOfBoundsException();
        }
        exch(j, n--);
        swim(j);
        sink(j);
        pq[n+1] = null;    // to avoid loiterig and help with garbage collection
    }


   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
        if(cityOrNot){
            pq[k].setCitySquareFootageIndex(k);
        } else {
            pq[k].setSquareFootageIndex(k);
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
        if(cityOrNot){
            pq[k].setCitySquareFootageIndex(k);
        } else {
            pq[k].setSquareFootageIndex(k);
        }
    }

   /***************************************************************************
    * Helper functions for compares and swaps.
    ***************************************************************************/
    private boolean less(int i, int j) {
            return pq[i].getSq_footage() < pq[j].getSq_footage();
    }

    private void exch(int i, int j) {
        Apt swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        if(cityOrNot){
            pq[i].setCitySquareFootageIndex(i);
            pq[j].setCitySquareFootageIndex(j);
        } else {
            pq[i].setSquareFootageIndex(i);
            pq[j].setSquareFootageIndex(j);
        }
    }

    // is pq[1..N] a max heap?
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }



    /**
     * Unit tests the {@code MaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    

}