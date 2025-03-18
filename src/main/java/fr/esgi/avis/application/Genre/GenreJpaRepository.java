package fr.esgi.avis.application.Genre;

import fr.esgi.avis.application.Genre.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
    Optional<GenreEntity> findByNom(String nom);
    List<GenreEntity> findByNomContaining(String keyword);
}