package util;

public class LevenshteinDistance {
    public static int calLevenshteinDistance(String testWords,
                                             String target) {
        int[][] distance = new int[testWords.length() + 1][target.length() + 1];

        for (int i = 0; i <= testWords.length(); i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= target.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= testWords.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {
                if (testWords.charAt(i - 1) == target.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = 1 + Math.min(distance[i - 1][j - 1], Math.min(distance[i][j - 1], distance[i
                            - 1][j]));
                }
            }
        }
        return distance[testWords.length()][target.length()];
    }
}
