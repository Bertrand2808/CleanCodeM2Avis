package fr.esgi.avis.domain.Classification;

import fr.esgi.avis.domain.Classification.model.Classification;

import java.util.List;
import java.util.Optional;

public interface ClassificationDataSourcePort {
    Classification save(Classification Classification);
    List<Classification> findAll();
    Optional<Classification> findById(Long id);
    Optional<Classification> findByNom(String nom);
    List<Classification> findByNomContaining(String keyword);
    void deleteById(Long id);
    long count();
}
