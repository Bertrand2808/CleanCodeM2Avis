package fr.esgi.avis.controller.Editeur.rest;

import fr.esgi.avis.controller.Editeur.EditeurController;
import fr.esgi.avis.controller.Editeur.dto.EditeurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editeurs")
@RequiredArgsConstructor
public class EditeurRestController {
    private final EditeurController editeurController;

    @PostMapping
    public ResponseEntity<EditeurDTO> createEditeur(@RequestBody EditeurDTO editeurDTO) {
        EditeurDTO createdEditeurDto = editeurController.createEditeur(editeurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEditeurDto);
    }

    @GetMapping
    public ResponseEntity<List<EditeurDTO>> getEditeurs() {
        List<EditeurDTO> editeurs = editeurController.getEditeurs();
        return ResponseEntity.ok(editeurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditeurDTO> getEditeurById(@PathVariable Long id) {
        return editeurController.getEditeurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EditeurDTO> getEditeursByNom(@PathVariable String nom) {
        return editeurController.getEditeurByNom(nom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<EditeurDTO>> getEditeursByNomContaining(@PathVariable String keyword) {
        List<EditeurDTO> editeurs = editeurController.getEditeursByNomContaining(keyword);
        return ResponseEntity.ok(editeurs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditeur(@PathVariable Long id) {
        if (editeurController.getEditeurById(id).isPresent()) {
            editeurController.deleteEditeurById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}