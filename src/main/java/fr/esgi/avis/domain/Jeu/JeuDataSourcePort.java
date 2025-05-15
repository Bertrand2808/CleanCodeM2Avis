package fr.esgi.avis.domain.Jeu;

import fr.esgi.avis.domain.Jeu.model.Jeu;

import java.util.List;
import java.util.Optional;

public interface JeuDataSourcePort {
    Jeu save(Jeu jeu);
    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    Optional<Jeu> findByNom(String nom);
    List<Jeu> findByNomContaining(String keyword);
    void deleteById(Long id);
    long count();
}
