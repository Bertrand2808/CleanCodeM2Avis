package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.domain.Classification.model.Classification;

public class ClassificationMapper {
    public static Classification toDomain(ClassificationEntity classificationEntity) {
        if (classificationEntity == null) return null;

        return Classification.builder()
                .id(classificationEntity.getId())
                .nom(classificationEntity.getNom())
                .couleurRGB(classificationEntity.getCouleurRGB())
                .jeux(classificationEntity.getJeux())
                .build();
    }

    public static ClassificationEntity toEntity(Classification classification) {
        if (classification == null) return null;

        return ClassificationEntity.builder()
                .id(classification.getId())
                .nom(classification.getNom())
                .couleurRGB(classification.getCouleurRGB())
                .jeux(classification.getJeux())
                .build();
    }
}
