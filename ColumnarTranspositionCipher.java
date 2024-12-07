import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ColumnarTranspositionCipher {

    // Encrypts the message using the columnar transposition cipher
    public static String encrypt(String message, String keyword) {
        // Remove spaces and make uppercase
        message = message.replaceAll("\\s+", "").toUpperCase();

        // Get the number of columns from the keyword length
        int numCols = keyword.length();
        // Calculate rows needed to fit the message
        int numRows = (int) Math.ceil((double) message.length() / numCols);

        // Fill the grid with characters from the message, add 'X' if needed
        char[][] grid = new char[numRows][numCols];
        int index = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (index < message.length()) {
                    grid[row][col] = message.charAt(index++);  // Fill grid with message characters
                } else {
                    grid[row][col] = 'X';  // Pad empty spots with 'X'
                }
            }
        }

        // Get the column order based on alphabetical order of the keyword letters
        Integer[] order = getOrder(keyword);

        // Read the columns in the specified order to create the ciphertext
        StringBuilder ciphertext = new StringBuilder();
        for (int col : order) {
            for (int row = 0; row < numRows; row++) {
                ciphertext.append(grid[row][col]);  // Append each character from the grid
            }
        }

        return ciphertext.toString();
    }

    // Gets the order to read columns based on alphabetical order of keyword letters
    private static Integer[] getOrder(String keyword) {
        // Convert keyword into array of characters
        Character[] chars = new Character[keyword.length()];
        for (int i = 0; i < keyword.length(); i++) {
            chars[i] = keyword.charAt(i);
        }

        // Create an array with column indices
        Integer[] order = new Integer[keyword.length()];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }

        // Sort columns based on alphabetical order of keyword letters
        Arrays.sort(order, Comparator.comparingInt(i -> chars[i]));

        return order;  // Return the column order
    }

    // Main method to get input from user and perform encryption
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get message input from user
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        // Get keyword input from user
        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine().toUpperCase();

        // Encrypt and display the encrypted message
        String encryptedMessage = encrypt(message, keyword);
        System.out.println("Encrypted Message: " + encryptedMessage);

        scanner.close();
    }
}
