package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompactPhoneBook {
    private final long[] phoneNumbers;
    private final char[] names;
    private final int[] nameIndices;
    private final int[] nameLengths;

    public CompactPhoneBook(List<String> phoneNumbersList, List<String> namesList) {
        int size = phoneNumbersList.size();
        this.phoneNumbers = new long[size];
        this.nameIndices = new int[size];
        this.nameLengths = new int[size];

        int totalChars = 0;
        for (String name : namesList) {
            totalChars += name.length();
        }
        this.names = new char[totalChars];

        int currentIndex = 0;
        for (int i = 0; i < size; i++) {
            this.phoneNumbers[i] = Long.parseLong(phoneNumbersList.get(i));
            this.nameIndices[i] = currentIndex;
            String name = namesList.get(i);
            this.nameLengths[i] = name.length();
            for (char c : name.toCharArray()) {
                this.names[currentIndex++] = c;
            }
        }
    }

    public String getNameByPhoneNumber(long phoneNumber) {
        int index = Arrays.binarySearch(phoneNumbers, phoneNumber);
        if (index < 0) {
            return null;
        }
        int nameStart = nameIndices[index];
        int nameLength = nameLengths[index];
        return new String(names, nameStart, nameLength);
    }


    public static void main(String[] args) {
        Generator generator = new Generator();
        List<String> phoneNumbersList = new ArrayList<>();
        List<String> namesList = new ArrayList<>();
        final int RECORD_COUNT = 18758328;
        while (phoneNumbersList.size() != RECORD_COUNT) {
            phoneNumbersList.add(generator.generatePhoneNumber());
        }
        while (namesList.size() != RECORD_COUNT) {
            namesList.add(generator.generateFullName());
        }
        CompactPhoneBook phoneBook = new CompactPhoneBook(phoneNumbersList, namesList);

        phoneBook.getNameByPhoneNumber(89061337450L);
        System.out.println("Всего записей: " + phoneNumbersList.size());
        System.out.println("Всего записей: " + namesList.size());

    }
}
