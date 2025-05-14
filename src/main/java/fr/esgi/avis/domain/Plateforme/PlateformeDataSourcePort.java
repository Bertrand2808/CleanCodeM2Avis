package fr.esgi.avis.domain.Plateforme;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.List;
import java.util.Optional;

public interface PlateformeDataSourcePort {
    Plateforme save(Plateforme plateforme);
    List<Plateforme> findAll();
    Optional<Plateforme> findById(Long id);
    Optional<Plateforme> findByNom(String nom);
    List<Plateforme> findByNomContaining(String keyword);
    void deleteById(Long id);
}
