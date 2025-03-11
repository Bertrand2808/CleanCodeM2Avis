package fr.esgi.avis.controller.Moderateur.rest;

import fr.esgi.avis.controller.Moderateur.ModerateurController;
import fr.esgi.avis.controller.Moderateur.dto.ModerateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST permettant de gérer les opérations relatives aux modérateurs.
 * Ce contrôleur expose des endpoints pour la création, la récupération et la suppression
 * des modérateurs via des requêtes HTTP.
 *
 * <p>Il délègue les traitements métier à {@link ModerateurController}.</p>
 *
 * <p>Annotations utilisées :</p>
 * <ul>
 *     <li>{@code @RestController} : Indique que cette classe est un contrôleur REST.</li>
 *     <li>{@code @RequestMapping("/api/moderateurs")} : Définit le préfixe des routes.</li>
 *     <li>{@code @RequiredArgsConstructor} : Génère un constructeur injectant les dépendances requises.</li>
 * </ul>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
@RestController
@RequestMapping("/api/moderateurs")
@RequiredArgsConstructor
public class ModerateurRestController {

    /**
     * Contrôleur métier permettant de gérer les opérations sur les modérateurs.
     */
    private final ModerateurController moderateurController;

    /**
     * Crée un nouveau modérateur.
     *
     * @param moderateurDTO Les informations du modérateur à créer.
     * @return Le {@link ModerateurDTO} correspondant au modérateur créé.
     */
    @PostMapping
    public ResponseEntity<ModerateurDTO> createModerateur(@RequestBody ModerateurDTO moderateurDTO) {
        ModerateurDTO createdModerateurDto = moderateurController.createModerateur(moderateurDTO);
        return ResponseEntity.ok(createdModerateurDto);
    }

    /**
     * Récupère un modérateur par son identifiant.
     *
     * @param id L'identifiant du modérateur.
     * @return Une réponse contenant le {@link ModerateurDTO}, ou une réponse 404 si non trouvé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModerateurDTO> getModerateurById(@PathVariable Long id) {
        return moderateurController.getModerateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère un modérateur par son pseudo.
     *
     * @param pseudo Le pseudo du modérateur.
     * @return Une réponse contenant le {@link ModerateurDTO}, ou une réponse 404 si non trouvé.
     */
    @GetMapping("/pseudo/{pseudo}")
    public ResponseEntity<ModerateurDTO> getModerateurByPseudo(@PathVariable String pseudo) {
        return moderateurController.getModerateurByPseudo(pseudo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère un modérateur par son email.
     *
     * @param email L'adresse email du modérateur.
     * @return Une réponse contenant le {@link ModerateurDTO}, ou une réponse 404 si non trouvé.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ModerateurDTO> getModerateurByEmail(@PathVariable String email) {
        return moderateurController.getModerateurByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère un modérateur par son numéro de téléphone.
     *
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     * @return Une réponse contenant le {@link ModerateurDTO}, ou une réponse 404 si non trouvé.
     */
    @GetMapping("/telephone/{numeroDeTelephone}")
    public ResponseEntity<ModerateurDTO> getByNumeroDeTelephone(@PathVariable String numeroDeTelephone) {
        return moderateurController.getByNumeroDeTelephone(numeroDeTelephone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un modérateur par son pseudo.
     *
     * @param pseudo Le pseudo du modérateur à supprimer.
     */
    @DeleteMapping("/pseudo/{pseudo}")
    public ResponseEntity<Void> deleteModerateurByPseudo(@PathVariable String pseudo) {
        moderateurController.deleteModerateurByPseudo(pseudo);
        return ResponseEntity.noContent().build();
    }

    /**
     * Supprime un modérateur par son identifiant.
     *
     * @param id L'identifiant du modérateur à supprimer.
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteModerateurById(@PathVariable Long id) {
        moderateurController.deleteModerateurById(id);
        return ResponseEntity.noContent().build();
    }
}
