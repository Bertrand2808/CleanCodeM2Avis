package fr.esgi.avis.controller.Joueur.rest;

import fr.esgi.avis.controller.Joueur.JoueurController;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Joueur Rest Controller
 */
@RestController
@RequestMapping("/api/joueurs")
@RequiredArgsConstructor
public class JoueurRestController {

    private final JoueurController joueurController;

    /**
     * Create a Joueur
     * @param joueurDTO : joueur to create
     * @return the created Joueur
     */
    @PostMapping
    public ResponseEntity<JoueurDTO> createJoueur(JoueurDTO joueurDTO) {
        JoueurDTO createdJoueurDto = joueurController.createJoueur(joueurDTO);
        return ResponseEntity.ok(createdJoueurDto);
    }

    /**
     * Get all Joueurs
     * @return all Joueurs
     */
    @GetMapping
    public ResponseEntity<Iterable<JoueurDTO>> getAllJoueurs() {
        return ResponseEntity.ok(joueurController.getAllJoueurs());
    }

    /**
     * Get a Joueur by its pseudo
     * @param pseudo : pseudo of the joueur to find
     * @return the joueur found
     */
    @GetMapping("/{pseudo}")
    public ResponseEntity<JoueurDTO> getJoueurByPseudo(String pseudo) {
        return joueurController.getJoueurByPseudo(pseudo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get a Joueur by its birthdate
     * @param birthdate : birthdate of the joueur to find
     * @return Joueur found
     */
    @GetMapping("/{birthdate}")
    public ResponseEntity<JoueurDTO> getJoueurByBirthdate(LocalDate birthdate) {
        return joueurController.getJoueurByBirthdate(birthdate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // TODO : add update and delete methods ?

}
