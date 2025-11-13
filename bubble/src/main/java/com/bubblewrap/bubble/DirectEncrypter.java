    package com.bubblewrap.bubble;

    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.math.BigInteger;
    import java.util.Random;

    public class DirectEncrypter {
//    private static final String SECRET_KEY = "1234567890123456";

        public static void encryptAndStore(String text, String websiteName, String password) {
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
                    }
                }

                // Step 3: Convert letters to numbers starting from 10
                StringBuilder values = new StringBuilder();
                for (int i = 0; i < upperCasePassword.length(); i++) {
                    char c = upperCasePassword.charAt(i);
                    if (Character.isLetter(c)) {
                        int val = c - 'A' + 10; // A=10, B=11, ..., Z=35
                        values.append(val);
                    }
                }

                // Convert to BigInteger
                String values1 = values.toString();
                BigInteger value = new BigInteger(values1);

                // Generate two primes
                int[] primes = generateTwoPrimes();

                BigInteger prime1 = BigInteger.valueOf(primes[0]);
                BigInteger prime2 = BigInteger.valueOf(primes[1]);
                BigInteger key = prime1.multiply(prime2);

                // Step 5: Multiply encryption key with password number

                BigInteger encryptedData = value.multiply(prime1).multiply(prime2);

                String output = ": " + encryptedData.toString();



                int[] primes = generateTwoPrimes();
                int encryptionKey = primes[0] * primes[1];

                // Step 5: Multiply encryption key with each password number
                String encryptedData = "" + (value * primes[0] * primes[1]);

                // Step 6: Add website name and save to a data file
                String output = websiteName + ":" + encryptedData;
                writeToFile("datafile.txt", output);

                System.out.println("✅ Encrypted data saved successfully to datafile.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            try (FileWriter writer = new FileWriter(filename, false)) {
                writer.write(content);
            }
            // Mark file as read-only (system-level)
            java.io.File file = new java.io.File(filename);
            file.setReadOnly();
        }

        public static BigInteger getKey(BigInteger key){
            return key;
        }

        public static void main(String[] args) {
            encryptAndStore("Hello from server", "myKey", "MyPassword123");
        }
    }
