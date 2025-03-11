package fr.esgi.avis.domain.Jeu;

import fr.esgi.avis.domain.Jeu.model.Jeu;

import java.util.List;
import java.util.Optional;


public interface JeuDataSourcePort {
    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    Optional<Jeu> findByNom(String nom);
    Jeu save(Jeu jeu);
    void delete(Jeu jeu);
}
