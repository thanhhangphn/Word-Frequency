# Word-Frequency

This project uses different implementations of MapSet (HashMap and Binary Search Tree Map) to determine the frequencies of all the words in a given text document---in a dataset of 2008 Reddit comments.

The result is a textual presentation of the word frequency analysis of the Reddit comments, along with analysis of the efficiency of two different data structures.

## Description

Implementing a HashMap is better than a BSTMap in this project: Insertion and search operations on a hashmap only take O(1) on average,
while on a self-balancing BSTMap take O(log(n)) on average, which is significantly slower. Hashmap is more time-efficient to process large text files that
require multiple insertion and search. Also, maxDepth() for BSTMap is significantly higher in case the BSTMap is not self-balancing, which probably
leads to time complexity for insertion and search becoming O(n), which is even slower.

## Sources/Credits
Colby College's CS231 (Data Structures and Algorithms)'s resources
