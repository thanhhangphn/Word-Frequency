
/**
 * Elia Phan
 * CS231 S23 Lab 6
 * WordCounterTests.java
 * last modified 04.24.2023
 */


import java.util.ArrayList;

/**
 * Purpose: Test the WordCounter class
 */

public class WordCounterTests {
    public static void main(String[] args){

        // case 1: testing WordCounter()
        System.out.println("\n Case 1: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");


            // verify
            System.out.println(c1.map + " == root: null.");
            System.out.println(c2.map + " == this is an empty HashMap.");

            // test
            assert c1 != null : "(Case 1) Error in WordCounter::WordCounter()";
            assert c2 != null : "(Case 1) Error in WordCounter::WordCounter()";
        }


        // case 2: testing buildMap() and readWords()
        System.out.println("\n Case 2: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            double time1 = c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            double time2 = c2.buildMap(c2.readWords("reddit_comments_2010.txt"));


            // verify
            System.out.println("time1: " + time1);
            System.out.println("time2: " + time2);

            // test
            assert time1 > 0 : "(Case 2) Error in WordCounter::buildMap() or WordCounter::readWords()";
            assert time2 > 0 : "(Case 2) Error in WordCounter::buildMap() and WordCounter::readWords()";
        }


        // case 3: testing totalWordCount()
        System.out.println("\n Case 3: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            c2.buildMap(c2.readWords("reddit_comments_2010.txt"));

            // verify
            System.out.println("c1.totalWordCount(): " + c1.totalWordCount());
            System.out.println("c2.totalWordCount(): " + c2.totalWordCount());

            // test
            assert c1.totalWordCount() > 0 : "(Case 3) Error in WordCounter::totalWordCount()";
            assert c2.totalWordCount() > 0 : "(Case 3) Error in WordCounter::totalWordCount()";
        }


        // case 4: testing uniqueWordCount()
        System.out.println("\n Case 4: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            c2.buildMap(c2.readWords("reddit_comments_2010.txt"));

            // verify
            System.out.println("c1.uniqueWordCount(): " + c1.uniqueWordCount());
            System.out.println("c2.uniqueWordCount(): " + c2.uniqueWordCount());

            // test
            assert c1.uniqueWordCount() > 0 : "(Case 4) Error in WordCounter::uniqueWordCount()";
            assert c2.uniqueWordCount() > 0 : "(Case 4) Error in WordCounter::uniqueWordCount()";
        }


        // case 5: testing getCount()
        System.out.println("\n Case 5: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            c2.buildMap(c2.readWords("reddit_comments_2010.txt"));

            // verify
            System.out.println("c1.getCount(): " + c1.getCount("Russian"));
            System.out.println("c2.getCount(): " + c2.getCount("art"));

            // test
            assert Integer.class.isInstance(c1.getCount("Russian")) : "(Case 5) Error in WordCounter::getCount()";
            assert Integer.class.isInstance(c2.getCount("art"))  : "(Case 5) Error in WordCounter::getCount()";
        }


        // case 6: testing getFrequency()
        System.out.println("\n Case 6: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            c2.buildMap(c2.readWords("reddit_comments_2010.txt"));

            // verify
            System.out.println("c1.getFrequency(): " + c1.getFrequency("Russian"));
            System.out.println("c2.getFrequency(): " + c2.getFrequency("art"));

            // test
            assert Double.class.isInstance(c1.getFrequency("Russian")) : "(Case 6) Error in WordCounter::getFrequency()";
            assert Double.class.isInstance(c2.getFrequency("art"))  : "(Case 6) Error in WordCounter::getFrequency()";
        }


        // case 7: testing writeWordCount() and readWordCount()
        System.out.println("\n Case 7: \n");
        {
            // set up
            WordCounter c1 = new WordCounter("bst");
            WordCounter c2 = new WordCounter("hashmap");

            c1.buildMap(c1.readWords("reddit_comments_2010.txt"));
            int count1 = c1.totalWordCount();
            c1.writeWordCount("c1.txt");

            c2.buildMap(c2.readWords("reddit_comments_2010.txt"));
            int count2 = c2.totalWordCount();
            c2.writeWordCount("c2.txt");

            c1.readWordCount("c2.txt");
            c2.readWordCount("c1.txt");

            // verify
            System.out.println(c2.totalWordCount() + "==" + count1);
            System.out.println(c1.totalWordCount() + "==" + count2);

            // test
            assert c2.totalWordCount() == count1 : "(Case 7) Error in WordCounter::writeWordCount() or readWordCount()";
            assert c1.totalWordCount() == count2  : "(Case 7) Error in WordCounter::writeWordCount() or readWordCount()";
        }

    }
}
