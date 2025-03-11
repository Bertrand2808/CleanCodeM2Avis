package fr.esgi.avis.domain.Joueur;

import fr.esgi.avis.domain.Joueur.model.Joueur;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JoueurRepository {
    Joueur save(Joueur joueur);
    Optional<Joueur> findByPseudo(String pseudo);
    Optional<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance);
    long count(); // remplace le count() de Spring Data JPA
    List<Joueur> findAll();
}
