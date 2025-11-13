package com.bubblewrap.bubble;

import java.math.BigInteger;
import java.util.HashMap;

public class EmojiMap {
    public static HashMap<Character, String> getEmoticonMap() {
        HashMap<Character, String> map = new HashMap<>();

        // Uppercase letters
        map.put('A', "(^◡^)");
        map.put('B', "(>‿<)");
        map.put('C', "(¬‿¬)");
        map.put('D', "(•‿•)");
        map.put('E', "(❁´◡`❁)");
        map.put('F', "(✿◠‿◠)");
        map.put('G', "(•ᴗ•)");
        map.put('H', "(≧◡≦)");
        map.put('I', "(´｡• ᵕ •｡`)");
        map.put('J', "(´• ω •`)");
        map.put('K', "(＾▽＾)");
        map.put('L', "(⌒‿⌒)");
        map.put('M', "(ʘ‿ʘ)");
        map.put('N', "(─‿‿─)");
        map.put('O', "(✧ω✧)");
        map.put('P', "(´∀`)");
        map.put('Q', "(ᵔᴥᵔ)");
        map.put('R', "(¬°‿‿°¬)");
        map.put('S', "(◕‿◕)");
        map.put('T', "(•‿•✿)");
        map.put('U', "(≧ω≦)");
        map.put('V', "(´▽`ʃ♡ƪ)");
        map.put('W', "(◠‿◠✿)");
        map.put('X', "(￣▽￣)");
        map.put('Y', "(✿´‿`)");
        map.put('Z', "(⌒‿⌒✿)");

        // Lowercase letters
        map.put('a', "(^人^)");
        map.put('b', "(⁀ᗢ⁀)");
        map.put('c', "(´▽`)");
        map.put('d', "(๑˃̵ᴗ˂̵)");
        map.put('e', "(≧▽≦)");
        map.put('f', "(☆▽☆)");
        map.put('g', "(´｡• ω •｡`)");
        map.put('h', "(｡♥‿♥｡)");
        map.put('i', "(◕‿◕✿)");
        map.put('j', "(•‿•)");
        map.put('k', "(＾ω＾)");
        map.put('l', "(っ˘ω˘ς)");
        map.put('m', "(✿❛‿❛✿)");
        map.put('n', "(￣ω￣)");
        map.put('o', "(⌒▽⌒)");
        map.put('p', "(•ω•)");
        map.put('q', "(✧‿✧)");
        map.put('r', "(◕‿◕)");
        map.put('s', "(ღ✪v✪)ღ");
        map.put('t', "(•‿•✿)");
        map.put('u', "(⊃｡•́‿•̀｡)⊃");
        map.put('v', "(✿╹◡╹)");
        map.put('w', "(◕ᴗ◕✿)");
        map.put('x', "(^_<)〜☆");
        map.put('y', "(❁´◡`❁)");
        map.put('z', "(๑>◡<๑)");

        // Numbers
        map.put('0', "(￣ー￣)");
        map.put('1', "(^▽^)");
        map.put('2', "(>ω<)");
        map.put('3', "(•̀ᴗ•́)و");
        map.put('4', "(^_−)☆");
        map.put('5', "(o^▽^o)");
        map.put('6', "(✧ω✧)");
        map.put('7', "(^з^)-☆");
        map.put('8', "(⌒‿⌒)");
        map.put('9', "(✿◠‿◠)");

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
