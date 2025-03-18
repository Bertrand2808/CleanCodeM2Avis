package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import fr.esgi.avis.domain.Genre.model.Genre;

public class GenreDtoMapper {
    public static GenreDTO toDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setNom(genre.getNom());
        genreDTO.setJeux(genre.getJeux());
        return genreDTO;
    }

    public static Genre toDomain(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setNom(genreDTO.getNom());
        genre.setJeux(genreDTO.getJeux());
        return genre;
    }
}
