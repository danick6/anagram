package com.zooplus.anagram.service;

import com.zooplus.anagram.bean.Subset;
import com.zooplus.anagram.components.AnagramHelper;
import com.zooplus.anagram.components.Dictionary;
import com.zooplus.anagram.components.DictionaryPrimeNumbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class AnagramService {

    private final Logger LOG = LoggerFactory.getLogger(AnagramService.class);

    private Dictionary dictionary;

    private DictionaryPrimeNumbers primeNumberDictionary;

    @Value("${dictionary.path}")
    private String pathFile;

    @Value("${dictionary.fileName}")
    private String fileName;

    @PostConstruct
    public void init() {
        try {
            dictionary = new Dictionary(pathFile, fileName);
            primeNumberDictionary = new DictionaryPrimeNumbers(pathFile, fileName);

        } catch (IOException e) {
            LOG.error("Error loading dictionary on [" + pathFile + fileName + "]");
        }
    }

    /**
     * Gets all permutations of a given word
     *
     * @param word
     * @return
     */
    protected Stream<String> permutations(String word) {
        if (word.isEmpty()) {
            return Stream.of("");
        }
        return IntStream.range(0, word.length()).boxed()
                .flatMap(i -> permutations(word.substring(0, i) + word.substring(i + 1)).map(t -> word.charAt(i) + t));
    }

    /**
     * Gets all existing words from a word's permutations
     *
     * @param word
     * @return
     */
    public List<String> findAnagram(String word){
        return dictionary.getSetWords(word);
    }

    /**
     * Search for an anagram using prime factorization
     * @param word
     * @return
     */
    public List<String> findAnagramPrimeNumber(String word){
        // Phase 1 - Search for an anagram word
        List<String> result = primeNumberDictionary.getPrimeAnagram(word);
        if(!result.isEmpty()){
            return result;
        }
        // Did not find any anagram word
        // Phase 2 - Search for an anagra with a subset of the word
        String[] rawSubsets = AnagramHelper.getAllSubsetOf(word);
        // Check which of the subsets are a valid word
        List<Subset> validSubsets = getValidSubsets(rawSubsets);
        long primeKey = AnagramHelper.wordToPrimeNumber(word);

        // With valids words, and the value of that word with prime numbers factorization
        // We have to find a combination that multiplying their numbers equals to prime key
        // TODO


        return primeNumberDictionary.getPrimeAnagram(word);
    }

    /**
     * Given an array of subsets of a word, returns a List of Subsets that is a valid word
     * Validation by primeNumberDictionary
     *
     * @param subsets
     * @return
     */
    protected List<Subset> getValidSubsets(String[] subsets){
        List<Subset> validSubsets = new ArrayList<Subset>();
        List<String> tmpAnagram;
        Subset tmpSubset;
        for(String subset : subsets){
            tmpAnagram = primeNumberDictionary.getPrimeAnagram(subset);
            // Subset is a valid word
            if (!tmpAnagram.isEmpty()){
                tmpSubset = Subset.builder()
                        .primeKey(AnagramHelper.wordToPrimeNumber(subset))
                        .word(subset)
                        .anagrams(tmpAnagram).build();

                validSubsets.add(tmpSubset);
            }
        }
        return validSubsets;
    }

    /**
     * TODO
     * @param subsets
     * @param totalKey
     * @return
     */
    protected String[] combinations(List<Subset> subsets, long totalKey){
        String[] result = null;

        return result;
    }
}
