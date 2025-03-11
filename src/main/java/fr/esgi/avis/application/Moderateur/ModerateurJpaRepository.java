package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModerateurJpaRepository extends JpaRepository<ModerateurEntity, Long> {

    /**
     * Recherche un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur.
     * @return Un `Optional<ModerateurEntity>` contenant le modérateur s'il existe.
     */
    Optional<ModerateurEntity> findByPseudo(String pseudo);

    /**
     * Recherche un modérateur par son email.
     * @param email L'email du modérateur.
     * @return Un `Optional<ModerateurEntity>` contenant le modérateur s'il existe.
     */
    Optional<ModerateurEntity> findByEmail(String email);

    /**
     * Recherche un modérateur par son numéro de téléphone.
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     * @return Un `Optional<ModerateurEntity>` contenant le modérateur s'il existe.
     */
    Optional<ModerateurEntity> findByNumeroDeTelephone(String numeroDeTelephone);

    /**
     * Supprime un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur à supprimer.
     */
    void deleteByPseudo(String pseudo);
}
