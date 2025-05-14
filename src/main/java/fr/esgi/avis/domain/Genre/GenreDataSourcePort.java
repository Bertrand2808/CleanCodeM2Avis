package fr.esgi.avis.domain.Genre;

import fr.esgi.avis.domain.Genre.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDataSourcePort {
    Genre save(Genre Genre);
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
    Optional<Genre> findByNom(String nom);
    List<Genre> findByNomContaining(String keyword);
    void deleteById(Long id);
}
