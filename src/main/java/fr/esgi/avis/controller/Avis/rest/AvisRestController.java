package fr.esgi.avis.controller.Avis.rest;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Avis Rest Controller
 */
@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
@Slf4j
public class AvisRestController {

    private final AvisController avisController;

    /**
     * Create an Avis
     * @param avisDTO : avis to create
     * @param bindingResult : validation results
     * @param session : the HTTP session
     * @return the created Avis
     */
    @PostMapping
    @Operation(description = "Méthode pour créer un avis.")
    public ResponseEntity<?> createAvis(
            @Valid @RequestBody AvisDTO avisDTO,
            BindingResult bindingResult,
            HttpSession session) {

        // Validation des erreurs de format
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }

        // Validation de la session
        Object utilisateur = session.getAttribute("utilisateur");
        if (utilisateur == null || !(utilisateur instanceof JoueurDTO)) {
            log.warn("Tentative de création d'avis sans authentification valide");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Vous devez être connecté en tant que joueur pour poster un avis");
        }

        try {
            JoueurDTO joueur = (JoueurDTO) utilisateur;

            // Set required fields
            avisDTO.setJoueurId(joueur.getId());
            avisDTO.setDateDEnvoi(LocalDateTime.now());
            avisDTO.setModerateurId(1L); // Temporairement, on met le premier modérateur

            log.info("Création d'un avis pour le jeu {} par le joueur {}", avisDTO.getJeuId(), joueur.getId());

            AvisDTO createdAvis = avisController.createAvis(avisDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAvis);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'avis", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de la création de l'avis");
        }
    }

    /**
     * Get an Avis by its id
     * @param id : id of the avis to find
     * @return the avis found
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvisDTO> getAvisById(@PathVariable Long id) {
        return avisController.getAvisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete an Avis by its id
     * @param id : id of the avis to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Méthode pour supprimer un avis.")
    public void deleteAvis(@PathVariable Long id) {
        avisController.deleteAvis(id);
    }
}
