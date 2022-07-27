package com.zooplus.anagram.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Dictionary based on the fundamental theorem of arithmetic states
 * Every integer either is a prime number itself or can be represented as the product of prime numbers
 * and that, moreover, this representation is unique, the order of the factors.
 * @see <a href="https://hackernoon.com/an-algorithm-for-finding-anagrams-2fe7655de85b">...</a>
 *
 */
public class DictionaryPrimeNumbers {
    private Map<Long, Set<String>> primeNumberMap;

    public DictionaryPrimeNumbers(String pathFile, String fileName) throws IOException {
        Map<Long, Set<String>> resultDictionary = new TreeMap<Long, Set<String>>();
        long primeKey;
        Set<String> tmpSet;
        String[] words = readFromFile(pathFile, fileName);
        for(String singleWord: words){
            primeKey = AnagramHelper.wordToPrimeNumber(singleWord);
            // Adding a new set for a new prime key
            if(resultDictionary.get(primeKey) == null){
                tmpSet = new HashSet<String>();
                tmpSet.add(singleWord);
                resultDictionary.put(primeKey, tmpSet);
            }
            // There's are set with a prime key - is anagram
            // Retrieve set and add the word to the set
            else {
                tmpSet = resultDictionary.get(primeKey);
                tmpSet.add(singleWord);
            }
        }
        this.primeNumberMap = resultDictionary;
    }

    public DictionaryPrimeNumbers(){}

    /**
     * Reading from file, path and file name define in application.properties
     *
     * @param pathFile
     * @param fileName
     * @return
     * @throws IOException
     */
    private String[] readFromFile(String pathFile, String fileName) throws IOException{
        Path path = Paths.get(pathFile + fileName);

        String wordListContents = null;
        byte[] readBytes = Files.readAllBytes(path);
        wordListContents = new String(readBytes, "UTF-8");

        return wordListContents.split("\r\n");
    }

    /**
     * Gets anagram word from dictionary by prime number factorization
     *
     * @param word
     * @return
     */
    public List<String> getPrimeAnagram(String word){
        List<String> result = new ArrayList<String>();
        long primeKey = AnagramHelper.wordToPrimeNumber(word);
        if(primeNumberMap.get(primeKey) != null){
            result.addAll(primeNumberMap.get(primeKey));
            result.remove(word);
        }

        return result;
    }



}
