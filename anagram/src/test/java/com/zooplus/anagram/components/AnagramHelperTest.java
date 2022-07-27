package com.zooplus.anagram.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AnagramHelperTest {
    AnagramHelper anagramHelper = new AnagramHelper();

    @Test
    void testCheckEmptyString() {
       assertTrue(anagramHelper.checkEmptyString(""));
       assertTrue(anagramHelper.checkEmptyString(null));
       assertFalse(anagramHelper.checkEmptyString("afesd"));
    }

    @Test
    void testSortWord() {
        String resultSorted = anagramHelper.sortWord("cba");
        assertEquals("abc", resultSorted);
    }

    @Test
    void testWordToPrimeNumber() {
        long result = anagramHelper.wordToPrimeNumber("ab");
        assertEquals(6, result);
    }

    @Test
    void testGetAllSubsetOf() {
        String[] result = anagramHelper.getAllSubsetOf("abc");
        assertEquals(6, result.length);

    }
}