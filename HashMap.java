/**
 * Elia Phan
 * CS231 S23 Lab 6
 * HashMap.java
 * last modified 04.10.2023
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Purpose: HashMap that maps a set of keys to specific values
 */

public class HashMap<K, V> implements MapSet<K, V>{

    int size; //An int size
    Node<K, V>[] arr; //An array of Nodes (Node[])
    double maxLoadFactor; //A double storing the maximal loadFactor, double maxLoadFactor


    /**
     * Node class
     */
    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> next;

        public Node(K key, V value) {
            super(key, value);
            next = null;
        }
    }


    /**
     * calls the following constructor with a reasonably chosen starting capacity (Java uses 16)
     */
    public HashMap(){
        this(16);
    }


    /**
     * calls the following constructor with a reasonable load factor (Java uses .75)
     */
    public HashMap(int capacity){
        this(capacity, .75);
    }


    /**
     *  initializes the HashMap with the given capacity and stores the given loadFactor
     */
    public HashMap(int capacity, double loadFactor){
        arr = new Node[capacity];
        size = 0;
        maxLoadFactor = loadFactor;
    }


    /**
     *  returns the length of the array handling this map.
     *  note that this is not necessarily the number of items actually stored in that array; there can (and should) be many empty slots.
     */
    public int capacity(){
        return arr.length;
    }


    /**
     * return the index of the underlying array in which the given key should be stored
     */
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity());
    }


    /**
     * returns the size
     */
    public int size(){
        return size;
    }


    /**
     * resets fields to default values
     */
    public void clear(){
        arr = null;
        size = 0;
        maxLoadFactor = 0;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * Does nothing if value is null. Returns the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value){
        V toReturn = null;
        Node<K, V> check = arr[hash(key)];
        while (check != null){
            if (check.getKey().equals(key)){
                break;
            }
            check = check.next;
        }
        if (check != null) {
            toReturn = check.getValue();
            check.setValue(value);
        }
        else {
            Node<K, V> node = new Node(key, value);
            if (arr[hash(key)] != null) {
                node.next = arr[hash(key)];
            }
            arr[hash(key)] = node;
            size++;
            if (size > maxLoadFactor * capacity()) {
                resize((int) (capacity() * 2));
            }
        }
        return toReturn;
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public V get(K key){
        V toReturn = null;
        Node<K, V> check = arr[hash(key)];
        while (check != null){
            if (check.getKey().equals(key)){
                break;
            }
            check = check.next;
        }
        if (check != null) {
            toReturn = check.getValue();
        }
        return toReturn;
    }


    /**
     *  Returns true if this map contains a mapping for the specified key to a value.
     */
    public boolean containsKey(K key){
        Node<K, V> check = arr[hash(key)];
        while (check != null){
            if (check.getKey().equals(key)){
                return true;
            }
            check = check.next;
        }
        return false;
    }


    /**
     *  Removes the specified key (and its associated value) from this mapping and returns the value it was mapped to.
     */
    public V remove(K key){
        Node<K, V> check = arr[hash(key)];
        if (check == null){
            return null;
        }

        if (check.getKey().equals(key)){
            arr[hash(key)] = check.next;
            size--;
            if (size <= maxLoadFactor * capacity() / 4)
                resize((int) (maxLoadFactor  * capacity() / 2));
            return check.getValue();
        }

        while (check.next != null){
            if (check.next.getKey().equals(key)){
                V returnValue = check.next.getValue();
                check.next = check.next.next;
                size--;
                if (size <= maxLoadFactor * capacity() / 4)
                    resize((int) (maxLoadFactor  * capacity() / 2));
                return returnValue;
            }
            check = check.next;
        }

        return null;
    }



    /**
     *  Returns an ArrayList of all the keys in the map in no particular order
     */
    public ArrayList<K> keySet(){
        ArrayList<K> a = new ArrayList<K>();
        for (Node<K,V> bucket : arr){
            if (bucket != null){
                while (bucket != null){
                    a.add(bucket.getKey());
                    bucket = bucket.next;
                }
            }
        }
        return a;
    }


    /**
     * Returns an ArrayList of all the values in the map in the same order returned by keySet()
     */
    public ArrayList<V> values(){
        ArrayList<V> a = new ArrayList();
        for (Node<K,V> bucket : arr){
            if (bucket != null){
                while (bucket != null){
                    a.add(bucket.getValue());
                    bucket = bucket.next;
                }
            }
        }
        return a;
    }


    /**
     * Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> a = new ArrayList();
        for (Node<K,V> bucket : arr){
            if (bucket != null){
                while (bucket != null){
                    a.add(new KeyValuePair(bucket.getKey(), bucket.getValue()));
                    bucket = bucket.next;
                }
            }
        }
        return a;
    }


    /**
     *  Returns a String that represents the HashMap
     */
    public String toString(){
        if (size == 0){
            return "this is an empty HashMap.";
        }
        StringBuilder s = new StringBuilder();
        for (Node<K,V> bucket : arr){
            s.append("\n|");
            s.append("\n|");
            if (bucket != null){
                while (bucket != null){
                    s.append("[");
                    s.append(bucket.getKey());
                    s.append(", ");
                    s.append(bucket.getValue());
                    s.append("]\t");
                    bucket = bucket.next;
                }
            }
        }
        return s.toString();
    }


    /**
     *  returns the size of the biggest bucket (the number of items in the bucket with the most items)
     */
    public int maxDepth(){
        int max = 0;
        for (Node<K,V> bucket : arr){
            int count = 0;
            while (bucket != null) {
                count++;
                bucket = bucket.next;
            }
            if (count >= max){
                max = count;
            }
        }
        return max;
    }


    /**
     *  resize the array
     */
    private void resize(int newCapacity) {
        System.out.println("list has just been resized to newCapacity == " + newCapacity);
        HashMap<K, V> h = new HashMap<>(newCapacity, maxLoadFactor);
        for (Node<K,V> bucket : arr){
            if (bucket != null){
                while (bucket != null){
                    h.put(bucket.getKey(), bucket.getValue());
                    bucket = bucket.next;
                }
            }
        }
        size = h.size;
        maxLoadFactor = h.maxLoadFactor;
        arr = h.arr;
    }
}