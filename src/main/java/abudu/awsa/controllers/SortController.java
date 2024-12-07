package abudu.awsa.controllers;

import abudu.awsa.utils.SortUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class SortController {

    /**
     * Sorts an array using a specified algorithm and returns the sorted result and complexity.
     *
     * @param request A map containing the sorting algorithm and array to be sorted.
     * @return A ResponseEntity with the sorted array and the algorithm's time complexity.
     */
    @PostMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortArray(@RequestBody Map<String, Object> request) {
        // Extract the algorithm and array from the request payload
        String algorithm = extractAlgorithm(request);
        int[] array = extractAndValidateArray(request.get("array"));

        // Apply the sorting algorithm to the array
        SortUtil.applySort(algorithm, array);

        // Prepare and return the response
        Map<String, Object> response = prepareResponse(array, algorithm);
        return ResponseEntity.ok(response);
    }

    /**
     * Extracts and validates the sorting algorithm from the request.
     *
     * @param request The input map containing the algorithm name.
     * @return The validated algorithm name.
     * @throws IllegalArgumentException if the algorithm is missing or invalid.
     */
    private String extractAlgorithm(Map<String, Object> request) {
        String algorithm = (String) request.get("algorithm");
        if (algorithm == null || algorithm.isBlank()) {
            throw new IllegalArgumentException("Algorithm must be specified.");
        }
        return algorithm.trim().toLowerCase();
    }

    /**
     * Extracts and validates the array from the input object.
     *
     * @param arrayObj The object representing the array.
     * @return A valid int[] representation of the array.
     * @throws IllegalArgumentException if the input is not a valid array of integers.
     */
    private int[] extractAndValidateArray(Object arrayObj) {
        if (!(arrayObj instanceof List<?> arrayList)) {
            throw new IllegalArgumentException("Array must be a list of integers.");
        }

        // Validate that the list contains only integers
        try {
            return arrayList.stream()
                    .mapToInt(obj -> (int) obj) // Direct cast to int
                    .toArray();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Array elements must be integers.");
        }
    }

    /**
     * Prepares the response containing the sorted array and algorithm complexity.
     *
     * @param sortedArray The sorted array.
     * @param algorithm The sorting algorithm used.
     * @return A map containing the sorted array and its time complexity.
     */
    private Map<String, Object> prepareResponse(int[] sortedArray, String algorithm) {
        Map<String, Object> response = new HashMap<>();
        response.put("sortedArray", sortedArray);
        response.put("complexity", determineAlgorithmComplexity(algorithm));
        return response;
    }

    /**
     * Determines the time complexity of the given sorting algorithm.
     *
     * @param algorithm The sorting algorithm name.
     * @return A string representing the time complexity.
     */
    private String determineAlgorithmComplexity(String algorithm) {
        return switch (algorithm) {
            case "heap", "merge" -> "O(n log n)";
            case "quick", "bucket" -> "O(n^2)";
            case "radix" -> "O(nk)";
            default -> "Unknown complexity";
        };
    }
}
