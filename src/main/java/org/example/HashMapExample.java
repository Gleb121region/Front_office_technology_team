package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Map<String, String> phoneBook = new HashMap<>();
        final int RECORD_COUNT = 18758328;
        while (phoneBook.size() != RECORD_COUNT) {
            phoneBook.put(generator.generatePhoneNumber(), generator.generateFullName());
        }
        System.out.println("Всего записей: " + phoneBook.size());
    }
}
