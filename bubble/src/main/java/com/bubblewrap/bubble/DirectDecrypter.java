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

    for(Character c : emojiMap.keySet()) {
        String emoji = emojiMap.get(c);
        if (reverseMap.containsKey(emoji)) {
            System.out.println("Warning: duplicate emoji for characters " + reverseMap.get(emoji) + " and " + c);
        } else {
            reverseMap.put(emoji, c);
        }
    }

    public String emojiToNumber(String emojiPassword, HashMap<String, Character> reverseMap) {
        StringBuilder number = new StringBuilder();
        int i = 0;
        while (i < emojiPassword.length()) {
            boolean matched = false;

            // Try to match each emoji in reverseMap
            for (String emoji : reverseMap.keySet()) {
                if (emojiPassword.startsWith(emoji, i)) {
                    number.append(reverseMap.get(emoji));
                    i += emoji.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                // if no emoji matches, skip one char (or handle error)
                i++;
            }
        }

        return number.toString();
    }

}
