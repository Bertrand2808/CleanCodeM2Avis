package fr.esgi.avis.domain.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository {
    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    Optional<Utilisateur> findByPseudo(String pseudo);
    void deleteById(Long id);

    Optional<Utilisateur> findByEmail(String email);
}
