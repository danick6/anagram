Zooplus Coding Exercise - Anagrams
==================================

Compile and execute
----------

Go to `anagram` folder execute this commands to

`$ mvnw.cmd install`

`$ java -jar "./target/anagram-0.0.1-SNAPSHOT.jar`

Solution - Step by step
------------

### Step 1: Generate Command Line application structure and get all permutations of a given word
For my first step, I wanted to start with something easy and on top of that develop my solution.

First, let's go to what an anagram is about.

> "An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once", [Link](https://en.wikipedia.org/wiki/Anagram)。

For my first solution:
1. Get all permutations of a word
2. Check if any of permutations are words with a dictionary

### Step 2: Checking on a dictionary

After trying on longer words, the performance is pretty bad, checking all permutation is not efficient

To improve it, I have changed the structure of the dictionary by saving the word sorted as key and the word as object Collection. 

For example the word "dog" would be store like this

`<key:"dgo", object:{"dog","god">`

As you can see the word "god" would be store in the same collection. 

### Step 3: Anagram phrase
Until now the solution is based on anagram word

``dog --> god``

But for some words doesn't exist an anagram word. So instead of a word it can be a pharse anagram

``Falling --> Ill Fang``

For this solution, I have changed the dictionary based on prime number uniqueness.

_Every integer either is a prime number itself or can be represented as the product of prime numbers and that, moreover, this representation is unique, the order of the factors._
[Links](https://hackernoon.com/an-algorithm-for-finding-anagrams-2fe7655de85b)

If we assign every letter from A to Z, a prime number

``
A = 2, B = 3, C = 5, ..., Z = 101 
``

Then multiplied it, and we get a number that represent a uniqueness of words or sentences that have the same words aka anagrams
From now on we will call it **prime key** of the word


![](https://hackernoon.com/hn-images/1*LlzBw_tdfQOrzAaFUQKg-w.png)

Dictionary will have this structure

`<key:30, object:{"bac","abc">`

The priority for searching an anagram will be:
1. Search for a word anagram
2. If there's no word anagram, search for anagram phrase

###### Step 1:
For input word, get the prime key and search it on dictionary. If there is result we return anagram word and finish.
If there's no result go to step 2

###### Step 2:
1. Get all subsets of the word
2. Remove subsets that are not a valid word and get the prime keys of each subset
3. Find a combination of subsets that multiply their prime keys together equals the input word prime key.


// TODO Step 2.3 Not finished