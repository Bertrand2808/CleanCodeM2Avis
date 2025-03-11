package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.List;
import java.util.stream.Collectors;

public class PlateformeMapper {

    public static Plateforme toDomain(PlateformeEntity entity) {
        if (entity == null) return null;
        return new Plateforme(
                entity.getId(),
                entity.getNom(),
                entity.getJeux(),
                entity.getDateDeSortie()
        );
    }

    public static PlateformeEntity toEntity(Plateforme plateforme) {
        if (plateforme == null) return null;
        return new PlateformeEntity(
                plateforme.getId(),
                plateforme.getNom(),
                plateforme.getJeux(),
                plateforme.getDateDeSortie()
        );
    }

    public static List<Plateforme> toDomainList(List<PlateformeEntity> entities) {
        return entities.stream().map(PlateformeMapper::toDomain).collect(Collectors.toList());
    }
}