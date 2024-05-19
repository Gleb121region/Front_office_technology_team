package org.example;

import java.util.Random;

public class Generator {
    Random random = new Random();

    public String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("+");
        for (int i = 0; i < 11; i++) {
            int digit = random.nextInt(10);
            phoneNumber.append(digit);
        }
        return phoneNumber.toString();
    }

    public String generateFullName() {

        StringBuilder fullName = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            fullName.append((char) ('a' + random.nextInt(26)));
        }
        return fullName.toString();
    }
}
