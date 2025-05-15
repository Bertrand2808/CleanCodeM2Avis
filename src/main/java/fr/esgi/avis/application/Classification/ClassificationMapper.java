package fr.esgi.avis.application.Classification;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.domain.Classification.model.Classification;

public class ClassificationMapper {
    public static Classification toDomain(ClassificationEntity classificationEntity) {
        if (classificationEntity == null) return null;

        return Classification.builder()
                .id(classificationEntity.getId())
                .nom(classificationEntity.getNom())
                .couleurRGB(classificationEntity.getCouleurRGB())
                .jeux(
                    classificationEntity.getJeux() != null ?
                    classificationEntity.getJeux().stream()
                    .map(JeuMapper::toDomain)
                    .toList() :
                    new ArrayList<>()
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
                    classification.getJeux() != null ?
                    classification.getJeux().stream()
                    .map(JeuMapper::toEntity)
                    .toList() :
                    new ArrayList<>()
                )
                .build();
    }

    public static Classification toDomainWithoutJeux(ClassificationEntity classificationEntity) {
        if (classificationEntity == null) return null;

        return Classification.builder()
                .id(classificationEntity.getId())
                .nom(classificationEntity.getNom())
                .couleurRGB(classificationEntity.getCouleurRGB())
                .jeux(
                        classificationEntity.getJeux().stream()
                                .map(JeuMapper::toDomainWithoutRelations)
                                .toList()
                )
                .build();
    }

    public static ClassificationEntity toEntityWithoutJeux(Classification classification) {
        if (classification == null) return null;

        return ClassificationEntity.builder()
                .id(classification.getId())
                .nom(classification.getNom())
                .couleurRGB(classification.getCouleurRGB())
                .jeux(
                    classification.getJeux() != null ?
                    classification.getJeux().stream()
                    .map(JeuMapper::toEntity)
                    .toList() :
                    new ArrayList<>()
                )
                .build();
    }
}
