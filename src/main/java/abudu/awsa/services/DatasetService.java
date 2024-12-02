package abudu.awsa.services;


import abudu.awsa.models.Dataset;
import abudu.awsa.dto.DatasetDTO;
import abudu.awsa.repositories.DatasetRepository;
import abudu.awsa.utils.DatasetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatasetService {
    private final DatasetRepository datasetRepository;

    @Autowired
    public DatasetService(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    public List<DatasetDTO> getAllDatasets(){
        List<Dataset> datasets = datasetRepository.findAll();
        return datasets.stream()
                .map(DatasetMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DatasetDTO getDatasetById(Long id ) {
        Optional<Dataset> dataset = datasetRepository.findById(id);
        return dataset.map(DatasetMapper::toDTO).orElse(null);
    }

    public DatasetDTO createDataset(DatasetDTO datasetDTO) {
        Dataset dataset = DatasetMapper.toEntity(datasetDTO);
        Dataset  savedDataset = datasetRepository.save(dataset);
        return DatasetMapper.toDTO(savedDataset);
    }

    public DatasetDTO updateDataset(Long id, DatasetDTO datasetDTO) {
        Optional<Dataset> existingDataset = datasetRepository.findById(id);
        if (existingDataset.isPresent()) {
            Dataset dataset = existingDataset.get();
            dataset.setName(datasetDTO.getName());
            dataset.setValue(datasetDTO.getValue());
            Dataset updatedDataset = datasetRepository.save(dataset);
            return DatasetMapper.toDTO(updatedDataset);
        }
        return null;
    }

    public boolean deleteDataset(Long id) {
        if (datasetRepository.existsById(id)) {
            datasetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
