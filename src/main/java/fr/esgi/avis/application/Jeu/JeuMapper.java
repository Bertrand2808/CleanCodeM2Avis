package fr.esgi.avis.application.Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import fr.esgi.avis.application.Classification.ClassificationMapper;
import fr.esgi.avis.application.Editeur.EditeurMapper;
import fr.esgi.avis.application.Genre.GenreMapper;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.PlateformeMapper;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;

public class JeuMapper {

    public static Jeu toDomain(JeuEntity jeuEntity) {
        if (jeuEntity == null) return null;

        return Jeu.builder()
                .id(jeuEntity.getId())
                .nom(jeuEntity.getNom())
                .editeur(EditeurMapper.toDomainWithoutJeux(jeuEntity.getEditeur()))
                .genre(GenreMapper.toDomainWithoutJeux(jeuEntity.getGenre()))
                .classification(ClassificationMapper.toDomainWithoutJeux(jeuEntity.getClassification()))
                .description(jeuEntity.getDescription())
                .dateDeSortie(jeuEntity.getDateDeSortie())
                .plateformes(
                        jeuEntity.getPlateformes().stream()
                                .map(PlateformeMapper::toDomainWithoutJeux)
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
                .editeur(EditeurMapper.toEntityWithoutJeux(jeu.getEditeur()))
                .genre(GenreMapper.toEntityWithoutJeux(jeu.getGenre()))
                .classification(ClassificationMapper.toEntityWithoutJeux(jeu.getClassification()))
                .description(jeu.getDescription())
                .dateDeSortie(jeu.getDateDeSortie())
                .plateformes(
                        jeu.getPlateformes().stream()
                                .map(PlateformeMapper::toEntityWithoutJeux)
                                .collect(Collectors.toList())
                )
                .image(jeu.getImage())
                .prix(jeu.getPrix())
                .build();
    }

    public static Jeu toDomainWithoutRelations(JeuEntity jeuEntity) {
        if (jeuEntity == null) return null;

        return Jeu.builder()
                .id(jeuEntity.getId())
                .nom(jeuEntity.getNom())
                .description(jeuEntity.getDescription())
                .dateDeSortie(jeuEntity.getDateDeSortie())
                .image(jeuEntity.getImage())
                .prix(jeuEntity.getPrix())
                .plateformes(new ArrayList<>())
                .editeur(Editeur.builder().id(0L).nom("unknown").jeux(new ArrayList<>()).build())
                .genre(Genre.builder().id(0L).nom("unknown").jeux(new ArrayList<>()).build())
                .classification(Classification.builder().id(0L).nom("unknown").couleurRGB("#000000").jeux(new ArrayList<>()).build())
                .build();
    }


}
