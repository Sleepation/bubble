    package com.bubblewrap.bubble;

    import java.io.FileWriter;
    import java.io.IOException;
    import java.math.BigInteger;
    import java.util.HashMap;
    import java.util.Random;

    public class DirectEncrypter {
//    private static final String SECRET_KEY = "1234567890123456";
        private DirectDecrypter decrypter;
        private String fileName;
        private String encryptedValue;



        DirectEncrypter(String password, DirectDecrypter decrypter){
            this.fileName = "data";
            this.encryptedValue = encryptAndStore("hi", "ho", password);
            this.decrypter = decrypter;
        }

        public String encryptAndStore(String text, String websiteName, String password) {
            try {
                // Step 2: Uppercase password
                String upperCasePassword = password.toUpperCase();

                StringBuilder values = new StringBuilder();

                // Step 3: Convert letters to two-digit numbers (A=01, B=02, ..., Z=26) with remap 1-9 → 28-36
                for (int i = 0; i < upperCasePassword.length(); i++) {
                    char c = upperCasePassword.charAt(i);
                    if (Character.isLetter(c)) {
                        int val = c - 'A' + 10; // A=10, B=11
                        values.append(val);
                        System.out.println("Encrypting "  +c);
                    }
                }


                // Convert to BigInteger
                String values1 = values.toString();
                BigInteger encryptedData = new BigInteger(values1);


                // Generate two primes


                // Step 5: Multiply encryption key with password number


                HashMap<Character, String> emojiMap = EmojiMap.getEmoticonMap();

                String numericPassword = encryptedData.toString(); // "80000451040"
                String emojiPassword = EmojiMap.numberToEmoji(numericPassword, emojiMap);

                String output = websiteName + ": " + (emojiPassword);
                System.out.println(output);
                writeToFile(fileName, output);

                System.out.println("✅ Encrypted data saved successfully to datafile.txt");
                return emojiPassword;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // Generate two random primes (simple example)
        private static int[] generateTwoPrimes() {
            int[] smallPrimes = {101, 103, 107, 109, 113, 127, 131, 137, 139};
            Random rand = new Random();
            int p1 = smallPrimes[rand.nextInt(smallPrimes.length)];
            int p2 = smallPrimes[rand.nextInt(smallPrimes.length)];
            return new int[]{p1, p2};
        }

        // Write the encrypted text to file (read-only suggestion)
        private static void writeToFile(String filename, String content) throws IOException {
            try (FileWriter writer = new FileWriter(filename, false)) { //Append and add it to next line
                writer.write(content + "\n");
            }
        }
        public String getEncryptedValue() {
            return encryptedValue;
        }

        public String decrypt(){
            return decrypter.decrypt(fileName);
        }
    }