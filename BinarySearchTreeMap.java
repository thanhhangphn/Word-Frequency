
/**
 * Elia Phan
 * CS231 S23 Lab 6
 * BinarySearchTreeMap.java
 * last modified 04.10.2023
 */


import java.util.ArrayList;
import java.util.Comparator;

/**
 * Purpose: Binary Search Tree that maps a set of keys to specific values
 */

public class BinarySearchTreeMap<K, V> implements MapSet<K, V> {
    Node<K, V> root; //the root from which we can reach every other Node
    int size; //the number of entries in the mapping
    Comparator<K> comp; //comparator of the structure, which will help us organize the structure


    /**
     * If comparator isn't null, saves it to the matching field.
     * If it is null, creates a new Comparator<K> assuming that K is Comparable.
     */
    public BinarySearchTreeMap(Comparator<K> comparator) { //TODO: tested
        root = null;
        size = 0;
        if (comp != null){
            comp = comparator;
        } else {
            comp = new Comparator<K>() {
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }
            };
        }
    }


    /**
     * Calls the first constructor with a null Comparator
     */
    public BinarySearchTreeMap() {
        this(null);
    } //TODO: tested


    /**
     * Node class
     */
    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> left; //field for the left child Node
        Node<K, V> right; //field for the right child Node

        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
        }
    }


    /**
     * returns the size.
     */
    public int size(){
        return size;
    } //TODO: tested


    /**
     * resets fields to default values
     */
    public void clear(){ //TODO: tested
        root = null;
        size = 0;
        comp = null;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced. Does nothing if value is null.
     * Returns the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value){ //TODO: tested
        if (size == 0){
            root = new Node<K, V>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }
    }


    /**
     * Private helper function for the function above: put(K key, V value)
     */
    private V put(K key, V value, Node<K, V> cur) { //TODO: tested
        if (comp.compare(key, cur.getKey()) < 0){
            if (cur.left != null){ //if there is a Node to the left of cur
                return put(key, value, cur.left); //return the recursive call's result to the left
            } else {
                cur.left = new Node(key, value); //insert a new Node with the given KeyValuePair to the left of cur
                size++;
                return null;
            }
        } else if (comp.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return put(key, value, cur.right); //return the recursive call's result to the right
            } else {
                cur.right = new Node(key, value); //insert a new Node with the given KeyValuePair to the right of cur
                size++;
                return null;
            }
        } else { // in this case, cur.getKey() == key
            //set the value of cur's KeyValuePair to be the given value and return the old one
            V output = cur.getValue();
            cur.setValue(value);
            return output;
        }
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key){
        return get(key, root);
    } //TODO: tested


    /**
     * Private helper function for the function above: get(K key)
     */
    private V get(K key, Node<K, V> cur){ //TODO: tested
        if (comp.compare(key, cur.getKey()) < 0){
            if (cur.left != null){ //if there is a Node to the left of cur
                return get(key, cur.left); //return the recursive call's result to the left
            } else {
                return null;
            }
        } else if (comp.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return get(key, cur.right); //return the recursive call's result to the right
            } else {
                return null;
            }
        } else { // in this case, cur.getKey() == key
            return cur.getValue();
        }
    }


    /**
     * Returns true if this map contains a mapping for the specified key to a value
     */
    public boolean containsKey(K key){
        return get(key) != null;
    }


    /**
     * Returns an ArrayList of all the keys in the map in sorted order from least to greatest
     * Return null if there is nothing in the map.
     */
    public ArrayList<K> keySet(){ //TODO: tested
        if (size == 0){
            return null;
        }
        ArrayList<K> output = new ArrayList<>();
        keySet(root, output);
        return output;
    }


    /**
     * Private helper function for the function above: keySet()
     */
    private void keySet(Node<K, V> cur, ArrayList<K> output){ //TODO: tested
        if (cur == null){
            return;
        }
        keySet(cur.left, output);
        output.add(cur.getKey());//add my own key to the output
        keySet(cur.right, output);
    }


    /**
     * Returns an ArrayList of all the values in the map in the same order returned by keySet()
     */
    public ArrayList<V> values(){ //TODO: tested
        ArrayList<V> output = new ArrayList<>();
        for (int i = 0; i < keySet().size(); i++){
            output.add(get(keySet().get(i)));
        }
        return output;
    }


    /**
     * Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet()
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){ //TODO: tested
        ArrayList<KeyValuePair<K, V>> output = new ArrayList<>();
        for (int i = 0; i < keySet().size(); i++){
            output.add(new KeyValuePair(keySet().get(i), get(keySet().get(i))));
        }
        return output;
    }


    /**
     * Returns a String that represents the BSTMap accurately depicting the levels.
     * e.g. the String representation for the KVPs (10, "ten"), (1, "one"), (20, "twenty"), (15, "fifteen") is:
     *          right: <20 -> twenty>
     *              left: <15 -> fifteen>
     *     root: <10 -> ten>
     *          left: <1 -> one>
     */
    public String toString(){ //TODO: tested
        String output;
        if (size == 0){
            return "root: null";
        }
        int repeat = 0;
        output = "\t".repeat(repeat+1) + toStrRight(root, repeat+1) + "\n" + "\t".repeat(repeat) + "root: " + root  + "\n" + "\t".repeat(repeat+1) + toStrLeft(root, repeat+1);
        return output;
    }


    /**
     * Private helper function for the function above: toString()
     */
    private String toStrLeft(Node<K, V> cur, int repeat){ //TODO: tested
        if (cur.left == null){
            return "";
        }
        return "\t".repeat(repeat+1) + toStrRight(cur.left, repeat+1) + "\n" + "\t".repeat(repeat) + "left: " + cur.left + "\n" + "\t".repeat(repeat+1) + toStrLeft(cur.left, repeat+1);
    }


    /**
     * Private helper function for the function above: toString()
     */
    private String toStrRight(Node<K, V> cur, int repeat){ //TODO: tested
        if (cur.right == null){
            return "";
        }
        return "\t".repeat(repeat+1) + toStrRight(cur.right, repeat+1) + "\n" + "\t".repeat(repeat) + "right: " + cur.right + "\n" + "\t".repeat(repeat+1) + toStrLeft(cur.right, repeat+1);
    }


    /**
     * Returns the length of a maximal root to leaf (a leaf is a Node which has no children) path.
     */
    public int maxDepth(){ //TODO: tested
        int height = 1;
        if (size == 0){
            return 0;
        }
        return maxDepthRoot(root, height);
    }


    /**
     * Private helper function for the function above: maxDepth()
     */
    public int maxDepthRoot(Node<K, V> cur, int height){ //TODO: tested
        if (cur.left == null && cur.right == null){
            return height;
        }
        else if (cur.left == null){
            height = maxDepthRoot(cur.right, height) + 1;
            return height;
        }
        else if (cur.right == null){
            height = maxDepthRoot(cur.left, height) + 1;
            return height;
        }
        else{
            height = Math.max(maxDepthRoot(cur.left, height), maxDepthRoot(cur.right, height)) + 1;
            return height;
        }
    }


    /**
     * Find the Node that has a given key
     */
    private Node<K, V> find(K key){
        return find(key, root);
    }


    /**
     * Private helper function for the function above: find(K key)
     */
    private Node<K, V> find(K key, Node<K, V> cur){
        if (comp.compare(key, cur.getKey()) < 0){
            if (cur.left != null){ //if there is a Node to the left of cur
                return find(key, cur.left); //return the recursive call's result to the left
            } else {
                return null;
            }
        } else if (comp.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return find(key, cur.right); //return the recursive call's result to the right
            } else {
                return null;
            }
        } else { // in this case, cur.getKey() == key
            return cur;
        }
    }


    /**
     * Find the parent Node of a Node that has a given key
     * Return null if the Node that has the given key is the root (which has no parent)
     * or if there is no such Node that has the given key.
     */
    private Node<K, V> findParent(K key){
        if (root.getKey() == key){
            System.out.println("findParent(K key): the Node that has the given key is the root (which has no parent).");
            return null;
        }
        return findParent(key, root, root);
    }


    /**
     * Private helper function for the function above: findParent(K key)
     */
    private Node<K, V> findParent(K key, Node<K, V> cur, Node<K, V> parent){
        if (comp.compare(key, cur.getKey()) < 0){
            if (cur.left != null){ //if there is a Node to the left of cur
                return findParent(key, cur.left, cur); //return the recursive call's result to the left
            } else {
                return null;
            }
        } else if (comp.compare(key, cur.getKey()) > 0){
            if (cur.right != null){
                return findParent(key, cur.right, cur); //return the recursive call's result to the right
            } else {
                return null;
            }
        } else { // in this case, cur.getKey() == key
            return parent;
        }
    }


    /**
     * Removes the given key from the structure and returns whatever value is currently associated with it.
     * Be careful to not delete all of that Node's children though!
     */
    public V remove(K key){
        //find the Node to remove:
        Node<K, V> node = find(key);
        V output =node.getValue();
        //call handleReplacement(that node, its parent)
        handleReplacement(node, findParent(node.getKey()));
        return output;
    }


    /**
     * Private helper function for the function above: remove(K key)
     */
    private void handleReplacement(Node toDelete, Node toDeleteParent){
        Node replacement; // to be found in the following conditionals
        if (toDelete.left == null && toDelete.right == null){
            System.out.println("hereD");
            return;
        }
        else if (toDelete.left == null){
            System.out.println("hereA");
            replacement = toDelete.right;
        }
        else if (toDelete.right == null){
            System.out.println("hereB");
            replacement = toDelete.left;
        }
        else {
            System.out.println("hereC");
            //set replacement to be the next largest (or smallest) Node
            //I chose largest (?)
            replacement = toDelete.right;
            Node<K, V> store = toDelete.left;
            //recursively call handleReplacement on this Node (or just do it yourself, I guarantee you this node has just one child)
            handleReplacement(replacement, toDelete);
            replacement.left = store;
        }
        //update toDeleteParent's child (whichever is currently toDelete) to be replacement
        if (toDeleteParent == null){ //that means toDelete is root
            root = replacement;
        }
        else if (toDeleteParent.right == toDelete){
            toDeleteParent.right = replacement;
        }
        else if (toDeleteParent.left == toDelete){
            toDeleteParent.left = replacement;
        }

    }


    /**
     * Tests:
     */
    public static void main(String[] args) {
        // this will sort the strings lexicographically (dictionary-order)
        BinarySearchTreeMap<Integer, Integer> words = new BinarySearchTreeMap<>();
        words.put(7, 7);
        words.put(4, 4);
        words.put(14, 14);
        words.put(2, 2);
        words.put(6, 6);
        words.put(10, 10);
        words.put(18, 18);
        words.put(1, 1);
        words.put(3, 3);
        words.put(5, 5);
        words.put(9, 9);
        words.put(12, 12);
        words.put(15, 15);
        words.put(20, 20);
        System.out.println("words: " + "\n" + words);
        words.remove(4);
        System.out.println("words: " + "\n" + words);


//        // this will sort the strings in reverse lexicographic order
//        BinarySearchTreeMap<Integer, Integer> wordsReverse = new BinarySearchTreeMap<>(new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
//
//        });
//        wordsReverse.put(10, 10);
//        wordsReverse.put(5, 5);
//        wordsReverse.put(3, 3);
//        System.out.println("wordsReverse:" + "\n" + wordsReverse);

    }
}