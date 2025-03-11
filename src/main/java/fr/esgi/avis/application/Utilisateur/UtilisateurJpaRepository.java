package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurJpaRepository extends JpaRepository<UtilisateurEntity, Long> {
    UtilisateurEntity findByPseudo(String pseudo);
    UtilisateurEntity findByEmail(String email);
    void deleteByPseudo(String pseudo);
}
