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
            case "bucket":
                BucketSort.sort(array);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
        if (!ArrayIsSorted.isSorted(array)) {
            throw new RuntimeException("Sorting failed");
        }

    }

}
