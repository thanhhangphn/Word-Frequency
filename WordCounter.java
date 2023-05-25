/**
 * Elia Phan
 * CS231 S23 Lab 6
 * WordCounter.java
 * last modified 04.24.2023
 */

import java.io.*;
import java.util.ArrayList;

/**
 * Purpose: WordCounter
 */

public class WordCounter {

    MapSet<String, Integer> map;
    int totalCount;


    /**
     *  constructor, where data_structure is either "bst" or "hashmap".
     *  It should create the appropriate data structure and store it in the map field.
     */
    public WordCounter( String data_structure ){
        if (data_structure.equals("bst")){
            map = new BinarySearchTreeMap<>();
            totalCount = 0;
        }
        if (data_structure.equals("hashmap")){
            map = new HashMap<>();
            totalCount = 0;
        }
    }


    /**
     *  given the filename of a text file, read the text file and return an ArrayList list of all the words in the file.
     */
    public ArrayList<String> readWords(String filename){

        ArrayList<String> arr_commonWords = readWords_helper1("commonWords.txt");
//        ArrayList<String> arr_wordsDictionary = readWords_helper1("words_alpha.txt");

        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing fr to the constructor
            BufferedReader br = new BufferedReader(fr);
            // assign to a variable of type Queue<Job> a new LinkedList.
            ArrayList<String> wordList = new ArrayList<>();

            // assign to a variable of type String line the result of calling the readLine method of the BufferedReader.
            String line = br.readLine();
            // everytime we call br.readLine(), we advance to the next line of the file we are reading.
            // start a while loop that loops while line isn't null
            while(line != null){
                line = line.replaceAll("\\p{Punct}", " ");
                // assign to an array of type String the result of calling split on the line with the argument ","
                String[] arr = line.split(" ");
                for (String item : arr){
                    if (!arr_commonWords.contains(item)){
                        wordList.add(item);
                        totalCount++;
                    }
                }
                line = br.readLine();
            }
            // call the close method of the BufferedReader:
            br.close();
            return wordList;
        }
        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.readWords():: unable to open file " + filename + ": file not found");
            return null;
        }
        catch(IOException ex) {
            System.out.println("WordCounter.readWords():: error reading file " + filename);
            return null;
        }
    }


    public ArrayList<String> readWords_helper1(String filename){


        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing fr to the constructor
            BufferedReader br = new BufferedReader(fr);
            // assign to a variable of type Queue<Job> a new LinkedList.
            ArrayList<String> wordList = new ArrayList<>();

            // assign to a variable of type String line the result of calling the readLine method of the BufferedReader.
            String line = br.readLine();
            // everytime we call br.readLine(), we advance to the next line of the file we are reading.
            // start a while loop that loops while line isn't null
            while(line != null){
                line = line.replaceAll("\\p{Punct}", " ");
                // assign to an array of type String the result of calling split on the line with the argument ","
                String[] arr = line.split(" ");
                for (String item : arr){
                    wordList.add(item);
                    totalCount++;
                }
                line = br.readLine();
            }
            // call the close method of the BufferedReader:
            br.close();
            return wordList;
        }
        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.readWords():: unable to open file " + filename + ": file not found");
            return null;
        }
        catch(IOException ex) {
            System.out.println("WordCounter.readWords():: error reading file " + filename);
            return null;
        }
    }



    /**
     *  given an ArrayList of words, put the words into the map data structure.
     *  Return the time taken in ms.
     *  Record the time using System.currentTimeMillis().
     */
    public double buildMap( ArrayList<String> words ) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < words.size(); i++){
            String word = words.get(i);
            if (map.size() == 0){ //if there isn't a mapping for word yet
                map.put(word, 1);
            }
            else if (map.get(word) == null){ //if there isn't a mapping for word yet
                map.put(word, 1);
            }
            else{ //if there is already a mapping for word, increase the value by 1
                map.put(word, map.get(word) + 1);
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }


    /**
     *  clear the map data structure.
     */
    public void clearMap(){
        map = null;
        totalCount = 0;
    }


    /**
     *  return the total word count from the last time readWords was called.
     */
    public int totalWordCount(){
        return totalCount;
    }


    /**
     *  return the unique word count, which should be the size of the map data structure.
     */
    public int uniqueWordCount(){
        return map.size();
    }


    /**
     *  return the number of times the word occurred in the list of words.
     *  Query the data structure to get the information.
     *  Return 0 if the word does not exist in the data structure.
     */
    public int getCount( String word ){
        if (map.get(word) == null){
            return 0;
        }
        return map.get(word);
    }


    /**
     *  return the frequency of the word in the list of words.
     *  Query the data structure to get the word count and then divide by the total number of words to get the frequency.
     *  Return 0 if the word does not exist in the data structure.
     */
    public double getFrequency( String word ){
        if (map.get(word) == null){
            return 0;
        }
        return Double.valueOf(map.get(word))/totalWordCount();
    }


    /**
     *  write a word count file given the current set of words in the data structure.
     *  The first line of the file should contain the total number of words.
     *  Each subsequent line should contain a word and its frequency.
     */
    public boolean writeWordCount( String filename ){
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write(totalWordCount());
            for (MapSet.KeyValuePair<String,Integer> item : map.entrySet()){
                myWriter.write("\n" + item.getKey() + ", " + item.getValue());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred with wordCounter.writeWordCount().");
            e.printStackTrace();
            return false;
        }
    }


    /**
     *  Read a word count file given the filename.
     *  The function should clear the map and then put all the words, with their counts, into the map data structure.
     *  You might find that this method doesn't work when you read a wordCount file that was written from a BSTMap
     *  - part of the reflection section deals with this.
     */
    public boolean readWordCount( String filename ) {
        clearMap();

        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing fr to the constructor
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine method of the BufferedReader.
            String line = br.readLine();
            // everytime we call br.readLine(), we advance to the next line of the file we are reading.
            totalCount = Integer.parseInt(line);

            // the first line of the job files are just the headers,
            //   let's skip the first line by calling br.readLine again:
            line = br.readLine();
            // start a while loop that loops while line isn't null
            while(line != null){
                // assign to an array of type String the result of calling split on the line with the argument ","
                String[] arr = line.split(",");
                map.put(arr[0], Integer.valueOf(arr[1]));
                line = br.readLine();
            }
            // call the close method of the BufferedReader:
            br.close();
            return true;
        }
        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.readWordCount():: unable to open file " + filename + ": file not found");
            return false;
        }
        catch(IOException ex) {
            System.out.println("WordCounter.readWordCount():: error reading file " + filename);
            return false;
        }
    }


    /**
     *  test
     */
    public static void main (String[] args){
        WordCounter w = new WordCounter("hashmap");
        w.buildMap(w.readWords("reddit_comments_2008.txt"));
        w.writeWordCount("2008.txt");
        w.buildMap(w.readWords("reddit_comments_2009.txt"));
        w.writeWordCount("2009.txt");
        w.buildMap(w.readWords("reddit_comments_2010.txt"));
        w.writeWordCount("2010.txt");
        w.buildMap(w.readWords("reddit_comments_2011.txt"));
        w.writeWordCount("2011.txt");
    }
}
