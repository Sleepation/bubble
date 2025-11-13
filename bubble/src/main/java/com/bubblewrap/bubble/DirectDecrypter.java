package com.bubblewrap.bubble;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DirectDecrypter {

    private LinkedHashMap<Character, String> emojiMap = new LinkedHashMap<>(EmojiMap.getEmoticonMap());
    private LinkedHashMap<String, Character> reverseMap = new LinkedHashMap<>();

    // Build reverse map ONCE (longest emojis first)
    public void newMap() {
        List<Map.Entry<Character, String>> entries =
                new ArrayList<>(emojiMap.entrySet());

        // sort by emoji length DESC
        entries.sort((a, b) -> b.getValue().length() - a.getValue().length());

        for (Map.Entry<Character, String> entry : entries) {
            Character c = entry.getKey();
            String emoji = entry.getValue();
            System.out.println("C " + c);
            reverseMap.put(emoji, c);
        }
    }

    // Convert emojis back to digits
    public String emojiToNumber(String emojiPassword) {
        System.out.println("PWD Emoji " + emojiPassword);
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < emojiPassword.length()) {
            boolean matched = false;

            for (String emoji : reverseMap.keySet()) {
                if (i + emoji.length() <= emojiPassword.length() &&
                        emojiPassword.startsWith(emoji, i)) {
                    result.append(reverseMap.get(emoji));

                    i += emoji.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                throw new RuntimeException("Unknown emoji at: " + emojiPassword.substring(i));
            }
        }

        System.out.println("Result " + result);
        return result.toString();
    }

    public String numberToLetters(String numeric) {
        // pad to even length
//        if (numeric.length() % 2 != 0) {
//            numeric = "0" + numeric;
//        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numeric.length(); i += 2) {
            int val = Integer.parseInt(numeric.substring(i, i + 2));
            if (val >= 10 && val <= 35) {
                char c = (char) ('A' + (val - 10));
                result.append(c);
            } else {
                throw new RuntimeException("Invalid encoded value: " + val);
            }
        }
        return result.toString();
    }

    // Decrypt file
    public String decrypt(String fileName) {

        try {
            newMap();

            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(":", 2);

                String name = parts[0].trim();
                String emojiPassword = parts[1].trim();

                String numericPassword = emojiToNumber(emojiPassword);

                BigInteger originalValue = new BigInteger(numericPassword);
                System.out.println("OG " + originalValue);

                String finalPassword = numberToLetters(originalValue.toString());
                System.out.println("Final " + finalPassword);
                System.out.println(name + ": " + finalPassword);

                return finalPassword;
            }

        } catch (Exception e) {
            System.out.println("Decrypt error: " + e.getMessage());
        }
        return null;
    }
}
