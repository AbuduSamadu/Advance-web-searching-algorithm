package abudu.awsa.utils;

import abudu.awsa.models.Dataset;
import abudu.awsa.dto.DatasetDTO;

public class DatasetMapper {
    public static DatasetDTO toDTO(Dataset dataset){
        if(dataset == null){
            return null;
        }
        return new DatasetDTO(dataset.getId(), dataset.getName(), dataset.getValue());
    }

    public static Dataset toEntity(DatasetDTO datasetDTO){
        if(datasetDTO == null){
            return null;
        }
       return new Dataset(datasetDTO.getName(), datasetDTO.getValue());
    }
}
