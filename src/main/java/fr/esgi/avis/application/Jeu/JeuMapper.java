package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.domain.Jeu.model.Jeu;

public class JeuMapper {

    public static Jeu toDomain(JeuEntity jeuEntity) {
        if (jeuEntity == null) return null;

        return Jeu.builder()
                .id(jeuEntity.getId())
                .nom(jeuEntity.getNom())
                .editeur(jeuEntity.getEditeur())
                .genre(jeuEntity.getGenre())
                .classification(jeuEntity.getClassification())
                .description(jeuEntity.getDescription())
                .dateDeSortie(jeuEntity.getDateDeSortie())
                .plateformes(jeuEntity.getPlateformes())
                .image(jeuEntity.getImage())
                .prix(jeuEntity.getPrix())
                .build();
    }

    public static JeuEntity toEntity(Jeu jeu) {
        if (jeu == null) return null;

        return JeuEntity.builder()
                .id(jeu.getId())
                .nom(jeu.getNom())
                .editeur(jeu.getEditeur())
                .genre(jeu.getGenre())
                .classification(jeu.getClassification())
                .description(jeu.getDescription())
                .dateDeSortie(jeu.getDateDeSortie())
                .plateformes(jeu.getPlateformes())
                .image(jeu.getImage())
                .prix(jeu.getPrix())
                .build();
    }
}