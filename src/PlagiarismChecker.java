/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
 */
public class PlagiarismChecker {

    private static boolean added;
    private static boolean[] visited;

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {

        // TODO Complete this function to return the length of the longest shared substring.

        added = false;
        int length = 0;

        int[][] map = new int[doc1.length()][doc2.length()];
        visited = new boolean[doc1.length()];
        // Iterate through the whole map and start filling it out;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = findMaxLength(i, j, map, doc1.charAt(i), doc2.charAt(j));
            }
        }


        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map[0].length; j++)
                System.out.print("[" + map[i][j] + "]");
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

    public static int findMaxLength(int row, int col, int[][] map, char a, char b) {
        // Need to make sure to remove the opportunity for a letter to be double or triple counted
        // by tracking which ones have already been added via a map and a base case here that
        // basically says if visited[location] = 1, then for the a==b don't add 1.
        int currentLongest = 0;
        if (row > 0)
            currentLongest = map[row - 1][col];
        if (col > 0)
            currentLongest = map[row][col - 1] > currentLongest ? map[row][col - 1] : currentLongest;
        if (a == b) {
            System.out.println("ping " + a);
            currentLongest++;
        }
        System.out.println(row + " | " + col + " | " + currentLongest);
        return currentLongest;
    }
}
