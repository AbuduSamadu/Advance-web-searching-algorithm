package abudu.awsa.utils;

import java.util.Arrays;

public class SortingUtils {

    // Utility to print an array (for debugging purposes)
    public static String arrayToString(int[] array) {
        return Arrays.toString(array);
    }

    // Utility to validate if an array is sorted
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    // Generate a random array of integers for testing
    public static int[] generateRandomArray(int size, int min, int max) {
        return Arrays.stream(new int[size])
                .map(i -> (int) (Math.random() * (max - min)) + min)
                .toArray();
    }
}
