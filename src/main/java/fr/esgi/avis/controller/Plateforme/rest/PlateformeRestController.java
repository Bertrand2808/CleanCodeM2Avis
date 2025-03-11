package fr.esgi.avis.controller.Plateforme.rest;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import fr.esgi.avis.controller.Plateforme.PlateformeController;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@RestController
@RequestMapping("/api/plateformes")
@RequiredArgsConstructor
public class PlateformeRestController {

    private final PlateformeController plateformeController;

    @GetMapping
    public ResponseEntity<List<PlateformeDTO>> getPlateformes() {
        List<PlateformeDTO> plateformes = plateformeController.getPlateformes();
        return ResponseEntity.ok(plateformes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlateformeDTO> getPlateformeById(@PathVariable Long id) {
        return plateformeController.getPlateformeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlateformeDTO> getPlateformeByNom(@PathVariable String nom) {
        return plateformeController.getPlateformeByNom(nom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlateformeDTO> createPlateforme(@RequestBody PlateformeDTO plateformeDTO) {
        PlateformeDTO createdPlateforme = plateformeController.createPlateforme(plateformeDTO);
        return ResponseEntity.status(201).body(createdPlateforme);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlateforme(@PathVariable Long id) {
        if (plateformeController.getPlateformeById(id).isPresent()) {
            plateformeController.deletePlateformeById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}