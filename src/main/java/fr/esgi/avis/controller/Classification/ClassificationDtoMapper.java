package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import fr.esgi.avis.domain.Classification.model.Classification;

public class ClassificationDtoMapper {
    public static ClassificationDTO toDto(Classification classification) {
        if (classification == null) {
            return null;
        }
        ClassificationDTO classificationDTO = new ClassificationDTO();
        classificationDTO.setId(classification.getId());
        classificationDTO.setNom(classification.getNom());
        classificationDTO.setCouleurRGB(classification.getCouleurRGB());
        classificationDTO.setJeux(classification.getJeux());
        return classificationDTO;
    }

    public static Classification toDomain(ClassificationDTO classificationDTO) {
        if (classificationDTO == null) {
            return null;
        }
        Classification classification = new Classification();
        classification.setId(classificationDTO.getId());
        classification.setNom(classificationDTO.getNom());
        classification.setCouleurRGB(classificationDTO.getCouleurRGB());
        classification.setJeux(classificationDTO.getJeux());
        return classification;
    }
}
