package fr.esgi.avis.usecases.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.model.ModerateurDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les cas d'utilisation liés aux modérateurs.
 * Cette classe interagit avec le port {@link ModerateurDataSourcePort} pour effectuer
 * des opérations de création, récupération et suppression des modérateurs.
 *
 * <p>Annotations utilisées :</p>
 * <ul>
 *     <li>{@code @Service} : Indique que cette classe est un service Spring.</li>
 *     <li>{@code @RequiredArgsConstructor} : Génère un constructeur injectant les dépendances requises.</li>
 * </ul>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
@RequiredArgsConstructor
@Service
public class ModerateurUseCases {

    /**
     * Port permettant d'accéder aux données des modérateurs.
     */
    private final ModerateurDataSourcePort moderateurDataSourcePort;

    /**
     * Crée un nouveau modérateur dans le système.
     *
     * @param moderateur L'objet {@link Moderateur} à enregistrer.
     * @return Le modérateur enregistré.
     */
    public Moderateur createModerateur(Moderateur moderateur) {
        return moderateurDataSourcePort.save(moderateur);
    }

    /**
     * Récupère la liste complète des modérateurs enregistrés.
     *
     * @return Une liste contenant tous les modérateurs disponibles.
     */
    public List<Moderateur> getAllModerateurs() {
        return moderateurDataSourcePort.findAll();
    }

    /**
     * Recherche un modérateur par son pseudo.
     *
     * @param pseudo Le pseudo du modérateur recherché.
     * @return Un {@link Optional} contenant le modérateur si trouvé, sinon un {@link Optional#empty()}.
     */
    public Optional<Moderateur> getModerateurByPseudo(String pseudo) {
        return moderateurDataSourcePort.findByPseudo(pseudo);
    }

    /**
     * Recherche un modérateur par son identifiant unique.
     *
     * @param id L'identifiant du modérateur.
     * @return Un {@link Optional} contenant le modérateur si trouvé, sinon un {@link Optional#empty()}.
     */
    public Optional<Moderateur> getModerateurById(Long id) {
        return moderateurDataSourcePort.findById(id);
    }

    /**
     * Recherche un modérateur par son adresse email.
     *
     * @param email L'adresse email du modérateur.
     * @return Un {@link Optional} contenant le modérateur si trouvé, sinon un {@link Optional#empty()}.
     */
    public Optional<Moderateur> getModerateurByEmail(String email) {
        return moderateurDataSourcePort.findByEmail(email);
    }

    /**
     * Recherche un modérateur par son numéro de téléphone.
     *
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     * @return Un {@link Optional} contenant le modérateur si trouvé, sinon un {@link Optional#empty()}.
     */
    public Optional<Moderateur> getByNumeroDeTelephone(String numeroDeTelephone) {
        return moderateurDataSourcePort.findByNumeroDeTelephone(numeroDeTelephone);
    }

    /**
     * Supprime un modérateur par son pseudo.
     *
     * @param pseudo Le pseudo du modérateur à supprimer.
     */
    public void deleteModerateurByPseudo(String pseudo) {
        moderateurDataSourcePort.deleteByPseudo(pseudo);
    }

    /**
     * Supprime un modérateur par son identifiant unique.
     *
     * @param id L'identifiant du modérateur à supprimer.
     */
    public void deleteModerateurById(Long id) {
        moderateurDataSourcePort.deleteById(id);
    }
}
