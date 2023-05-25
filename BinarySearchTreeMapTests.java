
/**
 * Elia Phan
 * CS231 S23 Lab 6
 * BinarySearchTreeMapTests.java
 * last modified 04.03.2023
 */



import java.util.ArrayList;
import java.util.Comparator;

/**
 * Purpose: Test the BinarySearchTreeMap class
 */

public class BinarySearchTreeMapTests {
    public static void main(String[] args){
        // case 1: testing BinarySearchTreeMap() and BinarySearchTreeMap(comp)
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            // verify
            System.out.println(c1 + " == root: null (Case 1)");
            System.out.println(c2 + " == root: null (Case 1)");

            // test
            assert c1 != null : "(Case 1) Error in BinarySearchTreeMap::BinarySearchTreeMap()";
            assert c2 != null : "(Case 1) Error in BinarySearchTreeMap::BinarySearchTreeMap()";
        }


        // case 2: testing put(K key, V value) and size()
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.size() + " == 3 (Case 2)");
            System.out.println(c2.size() + " == 2 (Case 2)");

            // test
            assert c1.size() == 3 : "(Case 2) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::size()";
            assert c2.size() == 2 : "(Case 2) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::size()";
        }


        // case 3: testing get(K key)
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.get(5) + " == 5 (Case 3)");
            System.out.println(c1.get(3) + " == 3 (Case 3)");
            System.out.println(c2.get(5) + " == null (Case 3)");

            // test
            assert c1.get(5) == 5 : "(Case 3) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::get(K key)";
            assert c1.get(3) == 3 : "(Case 3) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::get(K key)";
            assert c2.get(5) == null : "(Case 3) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::get(K key)";
        }


        // case 4: testing keySet()
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.keySet() + " == [3, 5, 10] (Case 4)");
            System.out.println(c2.keySet() + " == [6, 11] (Case 4)");

            // test
            assert c1.keySet() instanceof ArrayList<Integer> : "(Case 4) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::keySet()";
            assert c2.keySet() instanceof ArrayList<Integer> : "(Case 4) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::keySet()";
        }


        // case 5: testing maxDepth()
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(5, 5);
            c1.put(10, 10);
            c1.put(3, 3);
            c1.put(6, 6);
            c1.put(4, 4);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.maxDepth() + " == 3 (Case 5)");
            System.out.println(c2.maxDepth() + " == 2 (Case 5)");

            // test
            assert c1.maxDepth() == 3 : "(Case 5) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::maxDepth()";
            assert c2.maxDepth() == 2 : "(Case 5) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::maxDepth()";
        }


        // case 6: testing containsKey(K key)
        {
            // set up
            BinarySearchTreeMap<Integer, Integer> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, Integer> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.containsKey(10) + " == true (Case 6)");
            System.out.println(c1.containsKey(4) + " == false (Case 6)");
            System.out.println(c2.containsKey(5) + " == false (Case 6)");

            // test
            assert c1.containsKey(10) == true : "(Case 6) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::containsKey(K key)";
            assert c1.containsKey(4) == false : "(Case 6) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::containsKey(K key)";
            assert c2.containsKey(5) == false : "(Case 6) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::containsKey(K key)";
        }


        // case 7: testing values()
        {
            // set up
            BinarySearchTreeMap<Integer, String> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, String> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, "ten");
            c1.put(5, "five");
            c1.put(3, "three");
            c2.put(11, "eleven");
            c2.put(6, "six");

            // verify
            System.out.println(c1.values() + " == [three, five, ten] (Case 7)");
            System.out.println(c2.values() + " == [six, eleven] (Case 7)");

            // test
            assert c1.values() instanceof ArrayList<String> : "(Case 7) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::values()";
            assert c2.values() instanceof ArrayList<String> : "(Case 7) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::values()";
        }


        // case 8: testing entrySet()
        {
            // set up
            BinarySearchTreeMap<Integer, String> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, String> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(10, "ten");
            c1.put(5, "five");
            c1.put(3, "three");
            c2.put(11, "eleven");
            c2.put(6, "six");

            // verify
            System.out.println(c1.entrySet() + " == [<3 -> three>, <5 -> five>, <10 -> ten>] (Case 8)");
            System.out.println(c2.entrySet() + " == [<6 -> six>, <11 -> eleven>] (Case 8)");

            // test
            assert c1.entrySet() instanceof ArrayList<MapSet.KeyValuePair<Integer, String>> : "(Case 8) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::entrySet()";
            assert c2.entrySet() instanceof ArrayList<MapSet.KeyValuePair<Integer, String>> : "(Case 8) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::entrySet()";
        }


        // case 9: testing remove(K key)
        {
            // set up
            BinarySearchTreeMap<Integer, String> c1 = new BinarySearchTreeMap();
            BinarySearchTreeMap<Integer, String> c2 = new BinarySearchTreeMap<>(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }

            });

            c1.put(4, "four");
            c1.put(5, "five");
            c1.put(3, "three");
            c1.put(10, "ten");
            c1.put(6, "six");
            c1.put(1, "one");
            c2.put(5, "five");
            c2.put(6, "six");
            c2.put(11, "eleven");
            c2.put(3, "three");


            // verify
            System.out.println("c1: \n" + c1 + " (Case 9)");
            System.out.println("c2: \n" + c2 + " (Case 9)");
            c1.remove(5);
            c2.remove(6);
            System.out.println("c1: \n" + c1 + " (Case 9)");
            System.out.println("c2: \n" + c2 + " (Case 9)");

            // test
            assert c1.size() == 5 : "(Case 9) Error in BinarySearchTreeMap::put(K key, V value) or BinarySearchTreeMap::remove(K key)";
            assert c2.size() == 3 : "(Case 9) Error in BinarySearchTreeMap::put(K key, V value) and BinarySearchTreeMap::remove(K key)";
        }
    }
}
