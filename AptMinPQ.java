/******************************************************************************
 *  Compilation:  javac MinPQ.java
 *  Execution:    java MinPQ < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *  
 *  Generic min priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************/

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code MinPQ} class represents a priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 *  operations, along with methods for peeking at the minimum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  This implementation uses a binary heap.
 *  The <em>insert</em> and <em>delete-the-minimum</em> operations take
 *  logarithmic amortized time.
 *  The <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes time proportional to the specified capacity or the number of
 *  items used to initialize the data structure.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Key> the generic type of key on this priority queue
 */
public class AptMinPQ{
    private Apt[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private boolean cityOrNot;           // boolean determining whether or not to insert into a heap for a certain city or not

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public AptMinPQ(boolean cityOrRegular) {
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
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Apt min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize() {
        Apt[] temp = new Apt[n*2];
        for(int x = 0; x <= n; x++){
            temp[x] = pq[x];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the key to add to this priority queue
     */
    public void insert(Apt x) {
        if(x == null){
            return;
        }
        n++;
        // double size of array if necessary
        if (n > pq.length) {
            resize();
        }

        // add x, and percolate it up to maintain heap invariant
        pq[n] = x;
        swim(n);
    }

    public void updateValue(int i){
        if(i < 0){
            throw new IndexOutOfBoundsException();
        }
        swim(i);
        sink(i);
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
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
        pq[n+1] = null;     // to avoid loitering and help with garbage collection
    }


   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }

        if(cityOrNot){
            pq[k].setCityPriceIndex(k);
        } else {
            pq[k].setPricesIndex(k);
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }

        if(cityOrNot){
            pq[k].setCityPriceIndex(k);
        } else {
            pq[k].setPricesIndex(k);
        }
    }

   /***************************************************************************
    * Helper functions for compares and swaps.
    ***************************************************************************/
    private boolean greater(int i, int j) {
        return pq[i].getPrice() > pq[j].getPrice();
    }

    private void exch(int i, int j) {
        Apt swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        if(cityOrNot){
            pq[i].setCityPriceIndex(i);
            pq[j].setCityPriceIndex(j);
        } else {
            pq[i].setPricesIndex(i);
            pq[j].setPricesIndex(j);
        }
    }

    // is pq[1..N] a min heap?
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }


    /**
     * Unit tests the {@code MinPQ} data type.
     *
     * @param args the command-line arguments
     */


}
