package abudu.awsa;

import abudu.awsa.utils.SortUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SortUtilTest {

    // Generic test method for int[] arrays
    private void testSortIntArray(String algorithm, int[] input, int[] expected) {
        int[] arrayCopy = Arrays.copyOf(input, input.length);
        SortUtil.applySort(algorithm, arrayCopy);
        Assertions.assertArrayEquals(expected, arrayCopy, "Sorting failed for algorithm: " + algorithm);
    }

//    // Generic test method for float[] arrays
//    private void testSortFloatArray(String algorithm, float[] input, float[] expected) {
//        float[] arrayCopy = Arrays.copyOf(input, input.length);
//        SortUtil.applySort(algorithm, arrayCopy);
//        Assertions.assertArrayEquals(expected, arrayCopy, "Sorting failed for algorithm: " + algorithm);
//    }

    @Test
    public void testSortingAlgorithms() {
        // Test cases for int[] sorting
        int[] input = {5, 3, 8, 6, 2, 7, 4, 1};
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8};

        testSortIntArray("heap", input, sorted);
        testSortIntArray("quick", input, sorted);
        testSortIntArray("merge", input, sorted);
        testSortIntArray("radix", input, sorted);

        // Edge cases
        testSortIntArray("quick", new int[]{}, new int[]{});
        testSortIntArray("merge", new int[]{42}, new int[]{42});
//        testSortIntArray("radix", new int[]{10, 0, 5}, new int[]{10, 0, 5});

//        // Test cases for float[] sorting
//        float[] floatInput = {3.14f, 2.71f, 1.61f, 0.99f};
//        float[] floatSorted = {0.99f, 1.61f, 2.71f, 3.14f};
//
//        testSortFloatArray("bucket", floatInput, floatSorted);
    }
}
