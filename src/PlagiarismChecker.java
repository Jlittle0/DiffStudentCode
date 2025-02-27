import java.util.ArrayList;
import java.util.List;

/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
 */
public class PlagiarismChecker {

    private static int[] map;
    private static boolean increase;
    private static final int ALPHABET = 256;

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        // Initialize a "grid" to conduct tabulation by comparing doc1 and doc2.
        int[][] grid = new int[doc2.length()][doc1.length()];

        // Create a map that holds the most recent index for each letter in the alphabet.
        map = new int[ALPHABET];

        // Iterate through the whole grid and fill using findMaxLength method.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = findMaxLength(i, j, grid, doc1.charAt(j) == doc2.charAt(i), doc1.charAt(j));
//            increase = false;
        }

        // Prints out formatting
        System.out.print("[--]");
        for (int i = 0; i < doc1.length(); i++)
            System.out.print("[" + doc1.charAt(i) + "]");
        System.out.println();

        // Prints out grid
        for (int i = 0; i < grid.length; i++) {
            System.out.print("[" + doc2.charAt(i) + "]");
            for (int j = 0; j < grid[0].length; j++)
                System.out.print("[" + grid[i][j] + "]");
            System.out.println();
        }

        // Returns bottom right grid space (aka largest)
        return grid[doc2.length() - 1][doc1.length() - 1];

    }
    // Method to find current longest string of letters that has parameters:
    // current row, collumn, the entire grid, whether chars are equal, and the value of the char.
    public static int findMaxLength(int row, int col, int[][] grid, boolean equal, int index) {

        // Base cases to establish currentLongestString based on left and above neighbors.
        int currentLongest = 0;
        if (row > 0)
            currentLongest = grid[row - 1][col];
        if (col > 0)
            currentLongest = grid[row][col - 1] > currentLongest ? grid[row][col - 1] : currentLongest;
        // If the two letters are equal, then find more information to know whether to increase longest.
        if (equal && !increase) {
            if (map[index] == 0) {
                currentLongest++;
                increase = true;
            }
            else
                map[index] = 1;
        }
        return currentLongest;
    }
}
