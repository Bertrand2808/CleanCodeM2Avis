package fr.esgi.avis.controller.Jeu.rest;

import fr.esgi.avis.controller.Jeu.JeuController;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
@RequiredArgsConstructor
public class JeuRestController {

    private final JeuController jeuController;

    @PostMapping
    public ResponseEntity<JeuDTO> createJeu(@RequestBody JeuDTO jeuDTO) {
        JeuDTO createdJeuDto = jeuController.createJeu(jeuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJeuDto);
    }

    @GetMapping
    public ResponseEntity<List<JeuDTO>> getJeux() {
        List<JeuDTO> jeux = jeuController.getJeux();
        return ResponseEntity.ok(jeux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JeuDTO> getJeuById(@PathVariable Long id) {
        return jeuController.getJeuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<JeuDTO> getJeuxByNom(@PathVariable String nom) {
        return jeuController.getJeuByNom(nom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<JeuDTO>> getJeuxByNomContaining(@PathVariable String keyword) {
        List<JeuDTO> jeux = jeuController.getJeuxByNomContaining(keyword);
        return ResponseEntity.ok(jeux);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJeu(@PathVariable Long id) {
        if (jeuController.getJeuById(id).isPresent()) {
            jeuController.deleteJeuById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
