import java.util.*;

public class q5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the 10x10 crossword grid
        char[][] grid = new char[10][10];
        for (int i = 0; i < 10; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }

        // Read the words and split them by ';'
        String[] words = sc.nextLine().split(";");

        // Solve the crossword
        if (solveCrossword(grid, words, 0)) {
            // Print the solved crossword grid
            for (char[] row : grid) {
                System.out.println(new String(row));
            }
        }
    }

    // Backtracking function to solve the crossword
    public static boolean solveCrossword(char[][] grid, String[] words, int index) {
        if (index == words.length) {
            return true; // All words have been placed
        }

        String word = words[index];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (canPlaceHorizontally(grid, word, row, col)) {
                    boolean[] markers = placeHorizontally(grid, word, row, col);
                    if (solveCrossword(grid, words, index + 1)) {
                        return true;
                    }
                    unplaceHorizontally(grid, word, row, col, markers);
                }

                if (canPlaceVertically(grid, word, row, col)) {
                    boolean[] markers = placeVertically(grid, word, row, col);
                    if (solveCrossword(grid, words, index + 1)) {
                        return true;
                    }
                    unplaceVertically(grid, word, row, col, markers);
                }
            }
        }

        return false; // No valid position for this word
    }

    // Check if a word can be placed horizontally
    public static boolean canPlaceHorizontally(char[][] grid, String word, int row, int col) {
        if (col + word.length() > 10) return false;

        for (int i = 0; i < word.length(); i++) {
            if (grid[row][col + i] != '-' && grid[row][col + i] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    // Place a word horizontally
    public static boolean[] placeHorizontally(char[][] grid, String word, int row, int col) {
        boolean[] markers = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {
            if (grid[row][col + i] == '-') {
                grid[row][col + i] = word.charAt(i);
                markers[i] = true;
            }
        }

        return markers;
    }

    // Remove a word placed horizontally
    public static void unplaceHorizontally(char[][] grid, String word, int row, int col, boolean[] markers) {
        for (int i = 0; i < word.length(); i++) {
            if (markers[i]) {
                grid[row][col + i] = '-';
            }
        }
    }

    // Check if a word can be placed vertically
    public static boolean canPlaceVertically(char[][] grid, String word, int row, int col) {
        if (row + word.length() > 10) return false;

        for (int i = 0; i < word.length(); i++) {
            if (grid[row + i][col] != '-' && grid[row + i][col] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    // Place a word vertically
    public static boolean[] placeVertically(char[][] grid, String word, int row, int col) {
        boolean[] markers = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {
            if (grid[row + i][col] == '-') {
                grid[row + i][col] = word.charAt(i);
                markers[i] = true;
            }
        }

        return markers;
    }

    // Remove a word placed vertically
    public static void unplaceVertically(char[][] grid, String word, int row, int col, boolean[] markers) {
        for (int i = 0; i < word.length(); i++) {
            if (markers[i]) {
                grid[row + i][col] = '-';
            }
        }
    }
}