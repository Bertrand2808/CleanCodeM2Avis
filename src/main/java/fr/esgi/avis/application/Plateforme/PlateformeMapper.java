package fr.esgi.avis.application.Plateforme;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

public class PlateformeMapper {

    public static Plateforme toDomain(PlateformeEntity plateformeEntity) {
        if (plateformeEntity == null) return null;

        return Plateforme.builder()
                .id(plateformeEntity.getId())
                .nom(plateformeEntity.getNom())
                .jeux(
                    plateformeEntity.getJeux() != null ?
                    plateformeEntity.getJeux().stream()
                    .map(JeuMapper::toDomain)
                    .collect(Collectors.toList()) :
                    new ArrayList<>()
                )
                .dateDeSortie(plateformeEntity.getDateDeSortie())
                .build();
    }

    public static PlateformeEntity toEntity(Plateforme plateforme) {
        if (plateforme == null) return null;

        return PlateformeEntity.builder()
                .id(plateforme.getId())
                .nom(plateforme.getNom())
                .jeux(
                    plateforme.getJeux() != null ?
                    plateforme.getJeux().stream()
                    .map(JeuMapper::toEntity)
                    .collect(Collectors.toList()) :
                    new ArrayList<>()
                )
                .dateDeSortie(plateforme.getDateDeSortie())
                .build();
    }

    public static Plateforme toDomainWithoutJeux(PlateformeEntity plateformeEntity) {
        if (plateformeEntity == null) return null;

        return Plateforme.builder()
                .id(plateformeEntity.getId())
                .nom(plateformeEntity.getNom())
                .jeux(
                        plateformeEntity.getJeux() != null ?
                                plateformeEntity.getJeux().stream()
                                        .map(JeuMapper::toDomainWithoutRelations)
                                        .collect(Collectors.toList()) :
                                new ArrayList<>()
                )
                .dateDeSortie(plateformeEntity.getDateDeSortie())
                .build();
    }


    public static PlateformeEntity toEntityWithoutJeux(Plateforme plateforme) {
        if (plateforme == null) return null;

        return PlateformeEntity.builder()
                .id(plateforme.getId())
                .nom(plateforme.getNom())
                .jeux(
                    plateforme.getJeux() != null ?
                    plateforme.getJeux().stream()
                    .map(JeuMapper::toEntity)
                    .collect(Collectors.toList()) :
                    new ArrayList<>()
                )
                .dateDeSortie(plateforme.getDateDeSortie())
                .build();
    }
}
