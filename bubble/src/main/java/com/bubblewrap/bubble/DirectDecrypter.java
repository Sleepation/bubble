package com.bubblewrap.bubble;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DirectDecrypter {

    private HashMap<Character, String> emojiMap;

    public DirectDecrypter() {
        // Get the emoji map from EmojiMap class
        this.emojiMap = EmojiMap.getEmoticonMap();
    }

    // Helper method: convert numeric string to emoji string
    private String numberToEmoji(String numericPassword) {
        StringBuilder emojiPassword = new StringBuilder();
        for (char c : numericPassword.toCharArray()) {
            emojiPassword.append(emojiMap.getOrDefault(c, String.valueOf(c)));
        }
        return emojiPassword.toString();
    }

    //Decrypt method: reads file, decrypts numbers, converts to emoji
    public void decrypt(BigInteger key, String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(":", 2);
                String name = parts[0].trim();
                String encryptedString = parts[1].trim();

                BigInteger encryptedValue = new BigInteger(encryptedString);
                BigInteger originalValue = encryptedValue.divide(key);

                String emojiPassword = numberToEmoji(originalValue.toString());

                // Print result
                System.out.println(name + ": " + emojiPassword);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid encrypted number format in file.");
        }
    }

}
