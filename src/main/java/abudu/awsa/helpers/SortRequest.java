package abudu.awsa.helpers;

import java.util.List;

public class SortRequest {
    private String algorithm;
    private List<Integer> array;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }
}