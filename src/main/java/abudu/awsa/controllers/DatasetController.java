package abudu.awsa.controllers;


import abudu.awsa.models.dto.DatasetDTO;
import abudu.awsa.services.DatasetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datasets")

public class DatasetController {

    private final DatasetService datasetService;

    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping
    public ResponseEntity<List<DatasetDTO>> getAllDatasets() {
        List<DatasetDTO> datasets = datasetService.getAllDatasets();
        return new ResponseEntity<>(datasets, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatasetDTO> getDatasetById(@PathVariable Long id) {
        DatasetDTO dataset = datasetService.getDatasetById(id);
        if (dataset != null) {
            return new ResponseEntity<>(dataset, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<DatasetDTO>createDataset(@Valid  @RequestBody DatasetDTO datasetDTO) {
        DatasetDTO createdDataset = datasetService.createDataset(datasetDTO);
        return new ResponseEntity<>(createdDataset, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatasetDTO> updateDataset(@PathVariable Long id, @Valid @RequestBody DatasetDTO datasetDTO) {
        DatasetDTO updatedDataset = datasetService.updateDataset(id, datasetDTO);
        if (updatedDataset != null) {
            return new ResponseEntity<>(updatedDataset, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable Long id) {
        boolean isDeleted = datasetService.deleteDataset(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
