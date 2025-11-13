package com.bubblewrap.bubble;

import java.math.BigInteger;
import java.util.HashMap;

public class EmojiMap {
    public static HashMap<Character, String> getEmoticonMap() {
        HashMap<Character, String> map = new HashMap<>();

        // Numbers
        map.put('0', "(￣ー￣)");
        map.put('1', "(^▽^)");
        map.put('2', "(>ω<)");
        map.put('3', "(^人^)");
        map.put('4', "(＾ω＾)");
        map.put('5', "(o▽o)");
        map.put('6', "(✧ω✧)");
        map.put('7', "(^з^)");
        map.put('8', "(⌒‿⌒)");
        map.put('9', "(´▽`)");

        return map;
    }

    // Converts a numeric password to emoji string
    public static String numberToEmoji(String numericPassword, HashMap<Character, String> emojiMap) {
        StringBuilder emojiPassword = new StringBuilder();
        for (char c : numericPassword.toCharArray()) {
            emojiPassword.append(emojiMap.getOrDefault(c, String.valueOf(c)));
        }
        return emojiPassword.toString();
    }

    public static void main(String[] args) {
        HashMap<Character, String> emojiMap = getEmoticonMap();

        BigInteger decryptedPassword = new BigInteger("80000451040");

        String numericPassword = decryptedPassword.toString(); // "80000451040"
        String emojiPassword = numberToEmoji(numericPassword, emojiMap);

        System.out.println("Numeric password: " + numericPassword);
        System.out.println("Emoji password: " + emojiPassword);
    }
}
