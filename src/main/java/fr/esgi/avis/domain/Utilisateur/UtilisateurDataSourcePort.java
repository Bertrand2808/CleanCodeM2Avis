package fr.esgi.avis.domain.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import java.util.Optional;

public interface UtilisateurDataSourcePort {
    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    Optional<Utilisateur> findByPseudo(String pseudo);
    Optional<Utilisateur> findByEmail(String email);
    void deleteById(Long id);
}

