package abudu.awsa.helpers;

public class SortResponse {
    private final int[] sortedArray;
    private final String complexity;

    public SortResponse(int[] sortedArray, String complexity) {
        this.sortedArray = sortedArray;
        this.complexity = complexity;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public String getComplexity() {
        return complexity;
    }
}