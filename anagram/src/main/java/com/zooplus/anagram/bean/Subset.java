package com.zooplus.anagram.bean;

import lombok.Builder;
import java.util.List;

@Builder
public class Subset {
    private long primeKey;
    private String word;
    private List<String> anagrams;
}
