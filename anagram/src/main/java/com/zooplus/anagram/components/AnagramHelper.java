package com.zooplus.anagram.components;

import com.zooplus.anagram.utils.Constants;

import java.util.Arrays;

/**
 * Class that contains useful methods for anagrams
 */
public class AnagramHelper {
    /**
     * Check if string is null or empty
     * @param str
     * @return
     */
    public static boolean checkEmptyString(String str){
        return str == null || str.trim().isEmpty();
    }

    /**
     * Sort string
     *
     * @param str
     * @return
     */
    public static String sortWord(String str){
        if (str.isEmpty()) {
            return null;
        }
        byte[] charBytes = str.getBytes();
        Arrays.sort(charBytes);

        return new String(charBytes);
    }

    /**
     * Every word from A to Z has prime number assigned
     * Transform every letter in a prime number and then multiplied it together
     * to get a unique representation of the word
     * @param word
     * @return
     */
    public static long wordToPrimeNumber(String word){
        long result = 1L;

        char[] charBytes = word.toCharArray();
        // Transform char to ascii number minus base number = 97
        for(char singleChar : charBytes){
            result = result * Constants.PRIME_NUMBERS[singleChar - Constants.BASE_NUM];
        }

        return result;
    }

    /**
     * Given a word, extract all subsets of strings
     *
     * @param word
     * @return
     */
    public static String[] getAllSubsetOf(String word){
        int len = word.length();
        int temp = 0;
        // Total possible subsets for string of size n is n*(n+1)/2
        String arr[] = new String[len*(len+1)/2];

        // This loop maintains the starting character
        for(int i = 0; i < len; i++) {
            // This loop adds the next character every iteration for the subset to form and add it to the array
            for(int j = i; j < len; j++) {
                arr[temp] = word.substring(i, j+1);
                temp++;
            }
        }
        return arr;
    }
}
