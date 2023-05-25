
/**
 * Elia Phan
 * CS231 S23 Lab 6
 * HashMapTests.java
 * last modified 04.10.2023
 */


import java.util.ArrayList;
import java.util.Comparator;

/**
 * Purpose: Test the BinarySearchTreeMap class
 */

public class HashMapTests {
    public static void main(String[] args){
        // case 1: testing HashMap(), HashMap(capacity), HashMap(capacity, loadFactor)
        System.out.println("\n Case 1: \n");
        {
            // set up
            HashMap<Integer, Integer> c1 = new HashMap();
            HashMap<Integer, Integer> c2 = new HashMap(20);
            HashMap<Integer, Integer> c3 = new HashMap(18, .65);


            // verify
            System.out.println(c1 + " == this is an empty HashMap.");
            System.out.println(c2 + " == this is an empty HashMap.");
            System.out.println(c3 + " == this is an empty HashMap.");

            // test
            assert c1 != null : "(Case 1) Error in HashMap::HashMap()";
            assert c2 != null : "(Case 1) Error in HashMap::HashMap()";
            assert c3 != null : "(Case 1) Error in HashMap::HashMap()";
        }


        // case 2: testing put(K key, V value) and size()
        System.out.println("\n Case 2: \n");
        {
            // set up
            HashMap<Integer, Integer> c1 = new HashMap();
            HashMap<Integer, Integer> c2 = new HashMap();


            c1.put(10, 10);
            c1.put(15, 15);
            c1.put(23, 23);
            c1.put(100, 100);
            c1.put(45, 45);
            c1.put(36, 36);
            c1.put(104, 104);
            c1.put(52, 52);
            c1.put(37, 37);
            c1.put(109, 109);
            c1.put(85, 85);
            c1.put(31, 31);
            c1.put(106, 106);
            c1.put(95, 95);
            c1.put(33, 33);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.size() + " == 15");
            System.out.println(c2.size() + " == 2");

            // test
            assert c1.size() == 15 : "(Case 2) Error in HashMap::put(K key, V value) or HashMap::size()";
            assert c2.size() == 2 : "(Case 2) Error in HashMap::put(K key, V value) and HashMap::size()";
        }


        // case 3: testing get(K key)
        System.out.println("\n Case 3: \n");
        {
            // set up
            HashMap<Integer, String> c1 = new HashMap();
            HashMap<Integer, String> c2 = new HashMap();

            c1.put(10, "ten");
            c1.put(5, "five");
            c1.put(3, "three");
            c2.put(11, "eleven");
            c2.put(6, "six");

            // verify
            System.out.println(c1.get(5) + " == five");
            System.out.println(c1.get(3) + " == three");
            System.out.println(c2.get(5) + " == null");

            // test
            assert c1.get(5) == "five" : "(Case 3) Error in HashMap::put(K key, V value) or HashMap::get(K key)";
            assert c1.get(3) == "three" : "(Case 3) Error in HashMap::put(K key, V value) or HashMap::get(K key)";
            assert c2.get(5) == null : "(Case 3) Error in HashMap::put(K key, V value) or HashMap::get(K key)";
        }


        // case 4: testing keySet()
        System.out.println("\n Case 4: \n");
        {
            // set up
            HashMap<Integer, Integer> c1 = new HashMap();
            HashMap<Integer, Integer> c2 = new HashMap();


            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);

            // verify
            System.out.println(c1.keySet() + " == [3, 5, 10]");
            System.out.println(c2.keySet() + " == [6, 11]");

            // test
            assert c1.keySet() instanceof ArrayList<Integer> : "(Case 4) Error in HashMap::put(K key, V value) or HashMap::keySet()";
            assert c2.keySet() instanceof ArrayList<Integer> : "(Case 4) Error in HashMap::put(K key, V value) or HashMap::keySet()";
        }


        // case 5: testing maxDepth()
        System.out.println("\n Case 5: \n");
        {
            // set up
            HashMap<Integer, Integer> c1 = new HashMap(3);
            HashMap<Integer, Integer> c2 = new HashMap(5);

            c1.put(5, 5);
            c1.put(10, 10);
            c1.put(3, 3);
            c1.put(17, 17);
            c1.put(11, 11);
            c2.put(11, 11);
            c2.put(7, 7);
            System.out.println(c1);

            // verify
            System.out.println(c1.maxDepth() + " == 2");
            System.out.println(c2.maxDepth() + " == 1");

            // test
            assert c1.maxDepth() == 3 : "(Case 5) Error in HashMap::put(K key, V value) or HashMap::maxDepth()";
            assert c2.maxDepth() == 1 : "(Case 5) Error in HashMap::put(K key, V value) or HashMap::maxDepth()";
        }


        // case 6: testing containsKey(K key)
        System.out.println("\n Case 6: \n");
        {
            // set up
            HashMap<Integer, Integer> c1 = new HashMap();
            HashMap<Integer, Integer> c2 = new HashMap();


            c1.put(10, 10);
            c1.put(5, 5);
            c1.put(3, 3);
            c2.put(11, 11);
            c2.put(6, 6);


            // verify
            System.out.println(c1.containsKey(10) + " == true");
            System.out.println(c1.containsKey(4) + " == false");
            System.out.println(c2.containsKey(5) + " == false");

            // test
            assert c1.containsKey(10) == true : "(Case 6) Error in HashMap::put(K key, V value) or HashMap::containsKey(K key)";
            assert c1.containsKey(4) == false : "(Case 6) Error in HashMap::put(K key, V value) and HashMap::containsKey(K key)";
            assert c2.containsKey(5) == false : "(Case 6) Error in HashMap::put(K key, V value) and HashMap::containsKey(K key)";
        }


        // case 7: testing values()
        System.out.println("\n Case 7: \n");
        {
            // set up
            HashMap<Integer, String> c1 = new HashMap();
            HashMap<Integer, String> c2 = new HashMap();


            c1.put(10, "ten");
            c1.put(5, "five");
            c1.put(4, "four");
            c2.put(11, "eleven");
            c2.put(6, "six");

            // verify
            System.out.println(c1.values() + " == [four, five, ten]");
            System.out.println(c2.values() + " == [six, eleven]");

            // test
            assert c1.values() instanceof ArrayList<String> : "(Case 7) Error in HashMap::put(K key, V value) or HashMap::values()";
            assert c2.values() instanceof ArrayList<String> : "(Case 7) Error in HashMap::put(K key, V value) or HashMap::values()";
        }


        // case 8: testing entrySet()
        System.out.println("\n Case 8: \n");
        {
            // set up
            HashMap<Integer, String> c1 = new HashMap(3);
            HashMap<Integer, String> c2 = new HashMap(4);


            c1.put(10, "ten");
            c1.put(5, "five");
            c1.put(4, "four");
            c2.put(11, "eleven");
            c2.put(6, "six");

            // verify
            System.out.println(c1.entrySet() + " == [<4 -> four>, <10 -> ten>, <5 -> five>]");
            System.out.println(c2.entrySet() + " == [<6 -> six>, <11 -> eleven>]");

            // test
            assert c1.entrySet() instanceof ArrayList<MapSet.KeyValuePair<Integer, String>> : "(Case 8) Error in HashMap::put(K key, V value) or HashMap::entrySet()";
            assert c2.entrySet() instanceof ArrayList<MapSet.KeyValuePair<Integer, String>> : "(Case 8) Error in HashMap::put(K key, V value) or HashMap::entrySet()";
        }


        // case 9: testing remove(K key)
        System.out.println("\n Case 9: \n");
        {
            // set up
            HashMap<Integer, String> c1 = new HashMap();
            HashMap<Integer, String> c2 = new HashMap();


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
            System.out.println("\nc1: \n" + c1);
            System.out.println("\nc2: \n" + c2);
            c1.remove(5);
            c2.remove(6);
            System.out.println("\nc1: \n" + c1);
            System.out.println("\nc2: \n" + c2);
            System.out.println(c1.size() + " == 5");
            System.out.println(c2.size() + " == 3");

            // test
            assert c1.size() == 5 : "(Case 9) Error in HashMap::put(K key, V value) or HashMap::remove(K key)";
            assert c2.size() == 3 : "(Case 9) Error in HashMap::put(K key, V value) and HashMap::remove(K key)";
        }
    }
}
