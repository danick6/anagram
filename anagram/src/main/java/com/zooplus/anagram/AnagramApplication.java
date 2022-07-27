package com.zooplus.anagram;

import com.zooplus.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;

@SpringBootApplication
public class AnagramApplication implements CommandLineRunner {

    public static final String ANAGRAM = "anagram > ";

    @Autowired
    AnagramService anagramService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AnagramApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println(ANAGRAM + "Type your word");
            input = in.next();
            System.out.println(ANAGRAM + "Your word is " + input);
            long start1 = System.currentTimeMillis();

            List<String> result1 = anagramService.findAnagram(input);
            if (result1 == null || result1.isEmpty()) {
                System.out.println(ANAGRAM + "There's no other combination");
            } else {
                System.out.println(ANAGRAM + "Anagrams for your word: ");
                result1.forEach(System.out::println);

            }
            long end1 = System.currentTimeMillis();
            long elapsedTime1 = end1 - start1;
            System.out.println(ANAGRAM + "Elapsed Time: [" + (elapsedTime1) + "] milliseconds");


            long start2 = System.currentTimeMillis();

            List<String> result2 = anagramService.findAnagramPrimeNumber(input);
            if (result2 == null || result2.isEmpty()) {
                System.out.println(ANAGRAM + "There's no other combination");
            } else {
                System.out.println(ANAGRAM + "Anagrams for your word: ");
                result2.forEach(System.out::println);

            }
            long end2 = System.currentTimeMillis();
            long elapsedTime2 = end2 - start2;
            System.out.println(ANAGRAM + "PRIME KEY Elapsed Time: [" + (elapsedTime2) + "] milliseconds");

            in.nextLine();
        }

    }
}
