package fr.esgi.avis.controller.Jeu.rest;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Jeu.JeuController;
import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.PlateformeController;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jeux")
@RequiredArgsConstructor
public class JeuRestController {

    private final JeuController jeuController;

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

    @PostMapping
    public ResponseEntity<JeuDTO> createJeu(@RequestBody JeuDTO jeuDTO) {
        JeuDTO createdJeuDto = jeuController.createJeu(jeuDTO);
        return ResponseEntity.status(201).body(createdJeuDto);
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