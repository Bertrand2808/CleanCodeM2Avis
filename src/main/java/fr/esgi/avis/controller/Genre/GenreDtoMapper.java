package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.domain.Genre.model.Genre;

import java.util.stream.Collectors;

public class GenreDtoMapper {
    public static GenreDTO toDto(Genre genre) {
        if (genre == null) return null;
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setNom(genre.getNom());
        genreDTO.setJeux(genre.getJeux().stream()
                        .map(JeuDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
        return genreDTO;
    }

    public static Genre toDomain(GenreDTO genreDTO) {
        if (genreDTO == null) return null;
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setNom(genreDTO.getNom());
        genre.setJeux(genreDTO.getJeux().stream()
                .map(JeuDtoMapper::toDomain)
                .collect(Collectors.toList())
        );
        return genre;
    }
}
