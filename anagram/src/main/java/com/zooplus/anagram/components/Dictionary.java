package com.zooplus.anagram.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Dictionary
 */
public class Dictionary {
    private Map<String, Set<String>> sortedStringMap;

    public Dictionary(String pathFile, String fileName) throws IOException{
        Map<String, Set<String>> resultDictionary =  new TreeMap<String, Set<String>>();

        Path path = Paths.get(pathFile + fileName);

        byte[] readBytes = Files.readAllBytes(path);
        String wordListContents = new String(readBytes, "UTF-8");
        String[] words = wordListContents.split("\r\n");
        for (String singleWord : words){
            if(AnagramHelper.checkEmptyString(singleWord)){
                continue;
            }
            String processedWord =  singleWord.trim().toLowerCase();
            processedWord = AnagramHelper.sortWord(processedWord);

            if(resultDictionary.get(processedWord) == null){
                Set<String> tempSet = new HashSet<String>();
                tempSet.add(singleWord);
                resultDictionary.put(processedWord, tempSet);
            } else {
                Set<String> tempSet = resultDictionary.get(processedWord);
                tempSet.add(singleWord);
            }
        }

        this.sortedStringMap = resultDictionary;
    }

    public boolean contains(String word) {
        if (sortedStringMap.get(AnagramHelper.sortWord(word)) == null){
            return false;
        }
        return true;
    }

    public List<String> getSetWords (String word){
        List<String> result = null;
        String sortedWord = AnagramHelper.sortWord(word);
        if(sortedStringMap.get(sortedWord) != null){
            result = new ArrayList<String>();
            result.addAll(sortedStringMap.get(sortedWord));
            result.remove(word);
        }

        return result;
    }

}
