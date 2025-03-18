package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.List;
import java.util.stream.Collectors;

public class PlateformeMapper {

    public static Plateforme toDomain(PlateformeEntity plateformeEntity) {
        if (plateformeEntity == null) return null;

        return Plateforme.builder()
                .id(plateformeEntity.getId())
                .nom(plateformeEntity.getNom())
                .jeux(plateformeEntity.getJeux())
                .dateDeSortie(plateformeEntity.getDateDeSortie())
                .build();
    }

    public static PlateformeEntity toEntity(Plateforme plateforme) {
        if (plateforme == null) return null;

        return PlateformeEntity.builder()
                .id(plateforme.getId())
                .nom(plateforme.getNom())
                .jeux(plateforme.getJeux())
                .dateDeSortie(plateforme.getDateDeSortie())
                .build();
    }
}