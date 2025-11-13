package com.bubblewrap.bubble;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectDecrypter {

    private HashMap<Character, String> emojiMap = EmojiMap.getEmoticonMap();
    private HashMap<String, Character> reverseMap = new HashMap<>();

    // Build reverse map ONCE
    public void newMap() {
        for (Character c : emojiMap.keySet()) {
            String emoji = emojiMap.get(c);

            if (reverseMap.containsKey(emoji)) {
                System.out.println("Warning: duplicate emoji for characters "
                        + reverseMap.get(emoji) + " and " + c);
            } else {
                reverseMap.put(emoji, c);
            }
        }
    }

    // Convert emojis back to digits
    public String emojiToNumber(String emojiPassword) {

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < emojiPassword.length()) {
            boolean matched = false;

            // iterate over the emoji tokens
            for (String emoji : reverseMap.keySet()) {
                if (i + emoji.length() <= emojiPassword.length() &&
                        emojiPassword.substring(i, i + emoji.length()).equals(emoji)) {

                    result.append(reverseMap.get(emoji));
                    i += emoji.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                throw new RuntimeException(
                        "Unknown emoji starting at: " + emojiPassword.substring(i)
                );
            }
        }

        return result.toString();
    }

    // Decrypt file
    public void decrypt(BigInteger key, String fileName) {

        try {
            // MUST build the reverseMap first
            newMap();

            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(":", 2);
                String name = parts[0].trim();
                String emojiPassword = parts[1].trim();

                String numericPassword = emojiToNumber(emojiPassword);

                BigInteger encryptedValue = new BigInteger(numericPassword);
                BigInteger originalValue = encryptedValue.divide(key);

                System.out.println(name + ": " + originalValue);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file.");
        }
    }
}
