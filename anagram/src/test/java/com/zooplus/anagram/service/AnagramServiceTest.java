package com.zooplus.anagram.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class AnagramServiceTest {

    AnagramService anagramService = new AnagramService();

    @Test
    void permutations() {

        List<String> result = anagramService.permutations("dog").collect(Collectors.toList());

        assertTrue(result.contains("god"));
        assertEquals(6, result.size());
    }

    @Test
    void findAnagram() {
    }

    @Test
    void findAnagramPrimeNumber() {
    }

    @Test
    void getValidSubsets() {
    }

    @Test
    void combinations() {
    }
}