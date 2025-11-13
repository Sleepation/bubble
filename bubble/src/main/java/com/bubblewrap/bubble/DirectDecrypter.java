package com.bubblewrap.bubble;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectDecrypter {

    HashMap<Character, String> emojiMap = EmojiMap.getEmoticonMap();
    HashMap<String, Character> reverseMap = new HashMap<>();

    public void newMap(){
        for(Character c : emojiMap.keySet()) {
            String emoji = emojiMap.get(c);
            if (reverseMap.containsKey(emoji)) {
                System.out.println("Warning: duplicate emoji for characters " + reverseMap.get(emoji) + " and " + c);
            } else {
                reverseMap.put(emoji, c);
            }
        }
    }

    // Convert emoji string to number/letter string using reverseMap
    private String emojiToNumber(String emojiPassword) {
        StringBuilder numeric = new StringBuilder();
        int i = 0;

        while (i < emojiPassword.length()) {
            boolean matched = false;

            // Try each emoji in reverseMap
            for (String emoji : reverseMap.keySet()) {
                if (emojiPassword.startsWith(emoji, i)) {
                    numeric.append(reverseMap.get(emoji));
                    i += emoji.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                // skip unknown chars
                i++;
            }
        }

        return numeric.toString();
    }

    // Decrypt file
    public void decrypt(BigInteger key, String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(":", 2);
                String name = parts[0].trim();
                String emojiPassword = parts[1].trim();

                // Convert emoji to numeric string
                String numericPassword = emojiToNumber(emojiPassword);

                // Convert numeric string to BigInteger and decrypt
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
