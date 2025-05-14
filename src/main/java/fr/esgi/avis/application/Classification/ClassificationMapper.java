package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.domain.Classification.model.Classification;

import java.util.stream.Collectors;

public class ClassificationMapper {
    public static Classification toDomain(ClassificationEntity classificationEntity) {
        if (classificationEntity == null) return null;

        return Classification.builder()
                .id(classificationEntity.getId())
                .nom(classificationEntity.getNom())
                .couleurRGB(classificationEntity.getCouleurRGB())
                .jeux(
                        classificationEntity.getJeux().stream()
                                .map(JeuMapper::toDomain)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static ClassificationEntity toEntity(Classification classification) {
        if (classification == null) return null;

        return ClassificationEntity.builder()
                .id(classification.getId())
                .nom(classification.getNom())
                .couleurRGB(classification.getCouleurRGB())
                .jeux(
                        classification.getJeux().stream()
                                .map(JeuMapper::toEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
