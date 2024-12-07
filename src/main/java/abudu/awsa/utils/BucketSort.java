package abudu.awsa.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void sort(int[] array) {
        if (ArrayIsSorted.isSorted(array)) return;
        int n = array.length;

        if (n == 0) return;

        // Find the maximum value in the array to determine the range
        int maxValue = array[0];
        for (int value : array) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        List<Integer>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int value : array) {
            // Calculate the bucket index based on the value and the maximum value
            int bucketIndex = (value * n) / (maxValue + 1);
            buckets[bucketIndex].add(value);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                array[index++] = value;
            }
        }
    }
}