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

    @PostMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortArray(@RequestBody Map<String, Object> request) {
        String algorithm = (String) request.get("algorithm");
        Object arrayObj = request.get("array");

        Map<String, Object> response = new HashMap<>();

        if (arrayObj instanceof List<?> arrayList) {
            if (!arrayList.isEmpty() && arrayList.getFirst() instanceof Integer) {
                int[] array = arrayList.stream().mapToInt(i -> (Integer) i).toArray();
                SortUtil.applySort(algorithm, array);
                response.put("sortedArray", array);
            } else if (!arrayList.isEmpty() && arrayList.getFirst() instanceof Float) {
                float[] array = new float[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    array[i] = (Float) arrayList.get(i);
                }
                SortUtil.applySort(algorithm, array);
                response.put("sortedArray", array);
            } else {
                throw new IllegalArgumentException("Array type not supported");
            }
        } else {
            throw new IllegalArgumentException("Invalid array format");
        }

        response.put("complexity", getAlgorithmComplexity(algorithm));
        return ResponseEntity.ok(response);
    }

    private String getAlgorithmComplexity(String algorithm) {
        return switch (algorithm.toLowerCase()) {
            case "heap", "merge" -> "O(n log n)";
            case "bucket","quick" -> "O(n^2)";
            case "radix" -> "O(nk)";

            default -> "Unknown complexity";
        };
    }
}