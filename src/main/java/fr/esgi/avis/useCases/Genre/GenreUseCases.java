package fr.esgi.avis.useCases.Genre;

import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Genre.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreUseCases {

    private final GenreDataSourcePort GenreDataSourcePort;

    public Genre createGenre(Genre Genre) {
        return GenreDataSourcePort.save(Genre);
    }

    public List<Genre> getGenres() {
        return GenreDataSourcePort.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return GenreDataSourcePort.findById(id);
    }

    public Optional<Genre> getGenreByNom(String nom) {
        return GenreDataSourcePort.findByNom(nom);
    }

    public List<Genre> getGenresByNomContaining(String keyword) {
        return GenreDataSourcePort.findByNomContaining(keyword);
    }

    public void deleteGenreById(Long id) {
        GenreDataSourcePort.deleteById(id);
    }
}
