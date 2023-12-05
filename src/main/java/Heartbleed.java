public class Heartbleed {

    /**
     * This function take a string, cyphers it
     * and simulates "Heartbleeding" bug
     *
     * @param inputString
     * @param outputLength The desired length
     * @param key The Caeser cypher key
     * @return An encrypted string with the desired length
     */
    public static String manipulateString(String inputString, int outputLength, int key) {
        int inputLength = inputString.length();

        if (inputString == null || outputLength <= 0 || inputLength == 0) {
            return "";
        }

        if(key < 0) {
            key = 0;    // Soft value
        }
        else if(key > 5) {
            key = 5;    // Soft value
        }

        inputString = codify(inputString, key);
        if (inputLength == outputLength) {
            return inputString; // The length is the expected one, return the string.
        } else {
            // The length is not equal to the string length, we make a result string and copy the string.
            StringBuilder result = new StringBuilder(inputString);
            while (result.length() < outputLength) {
                result.append(inputString);
            }
            return result.substring(0, outputLength); // Cut at the desired length.
        }
    }

    /**
     * This method encrypts a message using the Caesar Cipher.
     *
     * @param inputString
     * @param key The Caeser cypher key
     * @return An encrypted string
     */
    private static String codify (String inputString, int key) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Iterate through each character in the message.
        for (int i = 0; i < inputString.length(); i++) {
            char originalChar = inputString.charAt(i);

            // Check if the character is a letter.
            if (Character.isLetter(originalChar)) {
                char shiftedChar = (char) (originalChar + key);

                // Handle uppercase letters.
                if (Character.isUpperCase(originalChar)) {
                    if (shiftedChar > 'Z') {
                        shiftedChar = (char) (shiftedChar - 26);
                    } else if (shiftedChar < 'A') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                }
                // Handle lowercase letters.
                else if (Character.isLowerCase(originalChar)) {
                    if (shiftedChar > 'z') {
                        shiftedChar = (char) (shiftedChar - 26);
                    } else if (shiftedChar < 'a') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                }

                // Append the shifted character to the result.
                encryptedMessage.append(shiftedChar);
            } else {
                // Keep non-alphabetic characters unchanged.
                encryptedMessage.append(originalChar);
            }
        }
        // Return the encrypted message as a string.
        return encryptedMessage.toString();
    }
}