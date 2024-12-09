package abudu.awsa.controllers;

import abudu.awsa.helpers.SortRequest;
import abudu.awsa.helpers.SortResponse;
import abudu.awsa.utils.SortUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class SortController {

    @PostMapping("/sort")
    public ResponseEntity<SortResponse> sortArray(@RequestBody SortRequest request) {
        String algorithm = extractAlgorithm(request);
        int[] array = extractAndValidateArray(request.getArray());

        SortUtil.applySort(algorithm, array);

        SortResponse response = new SortResponse(array, determineAlgorithmComplexity(algorithm));
        return ResponseEntity.ok(response);
    }

    private String extractAlgorithm(SortRequest request) {
        String algorithm = request.getAlgorithm();
        if (algorithm == null || algorithm.isBlank()) {
            throw new IllegalArgumentException("Algorithm must be specified.");
        }
        return algorithm.trim().toLowerCase();
    }

    private int[] extractAndValidateArray(List<Integer> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            throw new IllegalArgumentException("Array must be a list of integers.");
        }

        try {
            return arrayList.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Array elements must be integers.");
        }
    }

    private String determineAlgorithmComplexity(String algorithm) {
        return switch (algorithm) {
            case "heap", "merge" -> "O(n log n)";
            case "quick", "bucket" -> "O(n^2)";
            case "radix" -> "O(nk)";
            default -> "Unknown complexity";
        };
    }
}