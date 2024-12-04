package abudu.awsa.utils;

import java.util.Arrays;

public class SortUtil {

    public static void applySort(String algorithm, int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        switch (algorithm.toLowerCase()) {
            case "heap":
                HeapSort.sort(array);
                break;
            case "quick":
                QuickSort.sort(array, 0, array.length - 1);
                break;
            case "merge":
                MergeSort.sort(array, 0, array.length - 1);
                break;
            case "bucket":
                BucketSort.sort(array);
                break;
            case "default":
                Arrays.sort(array);
                break;
            case "parallel":
                Arrays.parallelSort(array);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
    }



}
