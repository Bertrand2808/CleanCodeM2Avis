package fr.esgi.avis.controller.Utilisateur.rest;

import fr.esgi.avis.controller.Utilisateur.UtilisateurController;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UtilisateurDTO> createUtilisateur(UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO createdUtilisateurDto = utilisateurController.createUtilisateur(utilisateurDTO);
        return ResponseEntity.ok(createdUtilisateurDto);
    }

    /**
     * Get an utilisateur by its pseudo
     * @param pseudo : pseudo of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/{pseudo}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurByPseudo(String pseudo) {
        return utilisateurController.getUtilisateurByPseudo(pseudo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get an utilisateur by its email
     * @param email : email of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/{email}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurByEmail(String email) {
        return utilisateurController.getUtilisateurByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get an utilisateur by its id
     * @param id : id of the utilisateur to find
     * @return the utilisateur found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(Long id) {
        return utilisateurController.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO : Add other methods
}
