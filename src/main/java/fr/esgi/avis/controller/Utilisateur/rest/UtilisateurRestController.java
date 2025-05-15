package fr.esgi.avis.controller.Utilisateur.rest;

import fr.esgi.avis.controller.Utilisateur.UtilisateurController;
import fr.esgi.avis.controller.Utilisateur.dto.ConnexionDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Utilisateur Rest Controller
 */
@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurRestController {

    private final UtilisateurController utilisateurController;

    /**
     * Create an utilisateur
     * @param utilisateurDTO : utilisateur to create
     * @return the created utilisateur
     */
    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            UtilisateurDTO createdUtilisateurDto = utilisateurController.createUtilisateur(utilisateurDTO);
            return ResponseEntity.ok(createdUtilisateurDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get an utilisateur by its pseudo
     * @param pseudo : pseudo of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/pseudo/{pseudo}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurByPseudo(@PathVariable String pseudo) {
        return utilisateurController.getUtilisateurByPseudo(pseudo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get an utilisateur by its email
     * @param email : email of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurByEmail(@PathVariable String email) {
        return utilisateurController.getUtilisateurByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get an utilisateur by its id
     * @param id : id of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
        return utilisateurController.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Connexion d'un utilisateur
     * @param connexionDTO : données de connexion
     * @return l'utilisateur connecté
     */
    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody ConnexionDTO connexionDTO) {
        try {
            UtilisateurDTO utilisateurDTO = utilisateurController.connexion(connexionDTO);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Déconnexion d'un utilisateur
     * @return réponse vide
     */
    @PostMapping("/deconnexion")
    public ResponseEntity<Void> deconnexion() {
        utilisateurController.deconnexion();
        return ResponseEntity.ok().build();
    }

    // TODO : Add other methods
}
