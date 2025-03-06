import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Josh Little
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        // Initialize a "grid" to conduct tabulation by comparing doc1 and doc2.
        int[][] grid = new int[doc2.length() + 1][doc1.length() + 1];

        // Iterate through the whole grid and fill using findMaxLength method.
        // starts at i=1 and j=1 to have a buffer col/row filled with zeros.
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++)
                grid[i][j] = findMaxLength(i, j, grid, doc1.charAt(j - 1) == doc2.charAt(i - 1));
        }

        // Returns bottom right grid space (the largest)
        return grid[doc2.length()][doc1.length()];
    }

    // Method to find current longest string of letters that has parameters:
    // current row, collumn, the entire grid, and whether the two chars being compared are equal.
    public static int findMaxLength(int row, int col, int[][] grid, boolean equal) {
        int currentLongest = 0;
        // If the two letters are equal, then take top left square to current grid location and add 1.
        if (equal)
            currentLongest = grid[row-1][col-1] + 1;
        else
            // otherwise set to the largest between its left neighbor and above neighbor.
            currentLongest = grid[row][col-1] > grid[row-1][col] ? grid[row][col-1] : grid[row-1][col];
        return currentLongest;
    }

}
