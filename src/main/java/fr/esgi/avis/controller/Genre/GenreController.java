package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreUseCases genreUseCases;

    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = GenreDtoMapper.toDomain(genreDTO);
        Genre createdGenre = genreUseCases.createGenre(genre);
        return GenreDtoMapper.toDto(createdGenre);
    }

    public List<GenreDTO> getGenres() {
        return genreUseCases.getGenres().stream()
                .map(GenreDtoMapper::toDto)
                .toList();
    }

    public Optional<GenreDTO> getGenreById(Long id) {
        return genreUseCases.getGenreById(id)
                .map(GenreDtoMapper::toDto);
    }

    public Optional<GenreDTO> getGenreByNom(String nom) {
        return genreUseCases.getGenreByNom(nom)
                .map(GenreDtoMapper::toDto);
    }

    public List<GenreDTO> getGenresByNomContaining(String keyword) {
        return genreUseCases.getGenresByNomContaining(keyword).stream()
                .map(GenreDtoMapper::toDto)
                .toList();
    }

    public void deleteGenreById(Long id) {
        genreUseCases.deleteGenreById(id);
    }
}