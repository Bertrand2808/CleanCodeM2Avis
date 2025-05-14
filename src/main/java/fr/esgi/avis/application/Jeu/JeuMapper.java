package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.domain.Jeu.model.Jeu;

import fr.esgi.avis.application.Editeur.EditeurMapper;
import fr.esgi.avis.application.Genre.GenreMapper;
import fr.esgi.avis.application.Classification.ClassificationMapper;
import fr.esgi.avis.application.Plateforme.PlateformeMapper;

import java.util.stream.Collectors;

public class JeuMapper {

    public static Jeu toDomain(JeuEntity jeuEntity) {
        if (jeuEntity == null) return null;

        return Jeu.builder()
                .id(jeuEntity.getId())
                .nom(jeuEntity.getNom())
                .editeur(EditeurMapper.toDomain(jeuEntity.getEditeur()))
                .genre(GenreMapper.toDomain(jeuEntity.getGenre()))
                .classification(ClassificationMapper.toDomain(jeuEntity.getClassification()))
                .description(jeuEntity.getDescription())
                .dateDeSortie(jeuEntity.getDateDeSortie())
                .plateformes(
                        jeuEntity.getPlateformes().stream()
                                .map(PlateformeMapper::toDomain)
                                .collect(Collectors.toList())
                )
                .image(jeuEntity.getImage())
                .prix(jeuEntity.getPrix())
                .build();
    }

    public static JeuEntity toEntity(Jeu jeu) {
        if (jeu == null) return null;

        return JeuEntity.builder()
                .id(jeu.getId())
                .nom(jeu.getNom())
                .editeur(EditeurMapper.toEntity(jeu.getEditeur()))
                .genre(GenreMapper.toEntity(jeu.getGenre()))
                .classification(ClassificationMapper.toEntity(jeu.getClassification()))
                .description(jeu.getDescription())
                .dateDeSortie(jeu.getDateDeSortie())
                .plateformes(
                        jeu.getPlateformes().stream()
                                .map(PlateformeMapper::toEntity)
                                .collect(Collectors.toList())
                )
                .image(jeu.getImage())
                .prix(jeu.getPrix())
                .build();
    }
}