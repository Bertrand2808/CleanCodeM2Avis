package fr.esgi.avis.domain.Editeur;

import fr.esgi.avis.domain.Editeur.model.Editeur;

import java.util.List;
import java.util.Optional;

public interface EditeurDataSourcePort {
    Editeur save(Editeur Editeur);
    List<Editeur> findAll();
    Optional<Editeur> findById(Long id);
    Optional<Editeur> findByNom(String nom);
    List<Editeur> findByNomContaining(String keyword);
    void deleteById(Long id);
}
