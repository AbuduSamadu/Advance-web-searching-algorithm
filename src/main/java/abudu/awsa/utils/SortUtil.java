package abudu.awsa.utils;

public class SortUtil {
    public static void applySort(String algorithm, int[] array) {
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
            case "radix":
                RadixSort.sort(array);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
    }

    public static void applySort(String algorithm, float[] array) {
        if ("bucket".equalsIgnoreCase(algorithm)) {
            BucketSort.sort(array);
        } else {
            throw new IllegalArgumentException("Invalid sorting algorithm for float: " + algorithm);
        }
    }
}
