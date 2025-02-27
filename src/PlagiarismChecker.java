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

    private static boolean added;
    private static int[] visited;
    private static final int ALPHABET = 256;

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        // New Idea: Do the same thing I've been doing before but keep a map that holds
        // the last instance that a letter was used (for example if the most recent A was on
        // index 4 then store that number under index 0 for A in the map. Then, when a new
        // A matches, check if the index it's matching with is greater than the most recent and
        // if it isn't, skip. Once a letter from the 2nd doc matches with the first doc, then prevent
        // everything from increasing for the entire row other than matching with left/up squares.


        // TODO Complete this function to return the length of the longest shared substring.
        doc1 = "aabbaaaaa";
        doc2 = "aaaaaaabbaaa";

        String temp = doc1;
        doc1 = doc2;
        doc2 = temp;


        added = false;
        int length = 0;

        int[][] map = new int[doc1.length()][doc2.length()];
        visited = new int[doc2.length()];
        // Iterate through the whole map and start filling it out;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = findMaxLength(i, j, map, doc1.charAt(i), doc2.charAt(j));
            }
        }


        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("[");
                if (map[i][j] < 10)
                System.out.print("0");
                System.out.print(map[i][j] + "]");
            }
        }
        System.out.println();
        System.out.println(doc1);
        System.out.println(doc2);
        return map[doc1.length() - 1][doc2.length() - 1];


        // Idea: You can accept or reject specific letters and do a recursive loop that converts
        // the two strings into a numerical code, then seeing whether it's possible to mod the value
        // or not. If the main string is evenly divisble by the second string, then that means they
        // are both present. Can't do this actually, but could do hash1 / hash 2 - that value * hash 2
        // and then see the remainder to that calculation, and check whether that's equal to
        // what's left over.

        // Another idea:
        // Create a 2D map for each of the possible characters that stores the location of each character.
        // Then do a similar approach to the temperature thing where you iterate through the map
        // and then use the lowest from each category. For this, would also need a 2nd map that
        // contains indexes that are the hashed values of each substring for doc2 and then
        // keep track of the location where that search ends, to find the next letter after that location.

        // Similar to the longest increasing substring (32-122)


        // Step 1: remove all letters/characters that aren't shared aka if their index is empty by
        // having the recursive call not include it whenever it decides to include/exclude
        // Step 2:

        // Idea #3: Create a 2d array with the # of characters of each string being row and col
        // then essentially do include/exclude using a table with tabulation and look up and left for
        // the value you want to know, potentially +1 with the included character if the two match.
        // Up and left are two different values not diagonally that would be your current longest.
    }

    public static int testing(String doc1, String doc2) {
        return 0;
    }

    public static int findMaxLength(int row, int col, int[][] map, char a, char b) {
        int currentLongest = 0;
        if (row > 0)
            currentLongest = map[row - 1][col];
        if (col > 0)
            currentLongest = map[row][col - 1] > currentLongest ? map[row][col - 1] : currentLongest;
        if (a == b) {
            System.out.println(a + " | " + row + " | " + col);
            updateVisited(row, col);
            if (visited[col] == row + 1)
                currentLongest++;
        }
        return currentLongest;
    }

    public static void updateVisited(int row, int col) {
        // If the letter has never been visited, update it to col.
        if (visited[col] == 0)
            visited[col] = row + 1;
        else if (col >= 1)
            if (visited[col-1] > visited[col])
                visited[col] = row + 1;
    }
}
