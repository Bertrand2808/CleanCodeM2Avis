package fr.esgi.avis.controller.Avis.rest;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Avis Rest Controller
 */
@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
public class AvisRestController {

    private final AvisController avisController;

    /**
     * Create an Avis
     * @param avisDTO : avis to create
     * @return the created Avis
     */
    @PostMapping
    public ResponseEntity<AvisDTO> createAvis(AvisDTO avisDTO) {
        AvisDTO createdAvisDto = avisController.createAvis(avisDTO);
        return ResponseEntity.ok(createdAvisDto);
    }

    /**
     * Get an Avis by its id
     * @param id : id of the avis to find
     * @return the avis found
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvisDTO> getAvisById(Long id) {
        return avisController.getAvisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete an Avis by its id
     * @param id : id of the avis to delete
     */
    @DeleteMapping("/{id}")
    public void deleteAvis(Long id) {
        avisController.deleteAvis(id);
    }
}
