package fr.esgi.avis.domain.Moderateur.model;

import java.util.List;
import java.util.Optional;

/**
 * Port d'accès aux données pour le domaine `Moderateur`.
 * Définit les méthodes de persistance indépendantes de l'infrastructure.
 */
public interface ModerateurDataSourcePort {

    /**
     * Sauvegarde un modérateur.
     * @param moderateur Le modérateur à enregistrer.
     * @return Le modérateur enregistré.
     */
    Moderateur save(Moderateur moderateur);

    /**
     * Recherche un modérateur par id
     * @param id id du modérateur
     * @return un optional<Moderateur>
     */
    Optional<Moderateur> findById(Long id);
    /**
     * Recherche un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur.
     * @return Un `Optional<Moderateur>` s'il existe.
     */
    Optional<Moderateur> findByPseudo(String pseudo);

    /**
     * Recherche un modérateur par son email.
     * @param email L'email du modérateur.
     * @return Un `Optional<Moderateur>` s'il existe.
     */
    Optional<Moderateur> findByEmail(String email);

    /**
     * Recherche un modérateur par son numéro de téléphone.
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     * @return Un `Optional<Moderateur>` s'il existe.
     */
    Optional<Moderateur> findByNumeroDeTelephone(String numeroDeTelephone);

    /**
     * Supprime un modérateur par son ID.
     * @param id L'ID du modérateur à supprimer.
     */
    void deleteById(Long id);

    /**
     * Supprime un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur à supprimer.
     */
    void deleteByPseudo(String pseudo);

    /**
     * Retourne le nombre total de modérateurs.
     * @return Nombre de modérateurs en base.
     */
    long count();

    /**
     * Retourne tous les modérateurs existants.
     * @return Une liste de modérateurs.
     */
    List<Moderateur> findAll();
}
