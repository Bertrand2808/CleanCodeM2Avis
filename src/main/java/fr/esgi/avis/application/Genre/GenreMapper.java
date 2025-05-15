package fr.esgi.avis.application.Genre;

import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.domain.Genre.model.Genre;

import java.util.ArrayList;

public class GenreMapper {
    public static Genre toDomain(GenreEntity genreEntity) {
        if (genreEntity == null) return null;

        return Genre.builder()
                .id(genreEntity.getId())
                .nom(genreEntity.getNom())
                .jeux(new ArrayList<>())
                .build();
    }

    public static GenreEntity toEntity(Genre genre) {
        if (genre == null) return null;

        return GenreEntity.builder()
                .id(genre.getId())
                .nom(genre.getNom())
                .jeux(new ArrayList<>())
                .build();
    }

    public static Genre toDomainWithoutJeux(GenreEntity genreEntity) {
        if (genreEntity == null) return null;

        return Genre.builder()
                .id(genreEntity.getId())
                .nom(genreEntity.getNom())
                .jeux(new ArrayList<>())
                .build();
    }

    public static GenreEntity toEntityWithoutJeux(Genre genre) {
        if (genre == null) return null;

        return GenreEntity.builder()
                .id(genre.getId())
                .nom(genre.getNom())
                .jeux(new ArrayList<>())
                .build();
    }
}
