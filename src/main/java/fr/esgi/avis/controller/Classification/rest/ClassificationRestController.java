package fr.esgi.avis.controller.Classification.rest;

import fr.esgi.avis.controller.Classification.ClassificationController;
import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
@RequiredArgsConstructor
public class ClassificationRestController {
    private final ClassificationController classificationController;

    @PostMapping
    public ResponseEntity<ClassificationDTO> createClassification(@RequestBody ClassificationDTO classificationDTO) {
        ClassificationDTO createdClassificationDto = classificationController.createClassification(classificationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClassificationDto);
    }

    @GetMapping
    public ResponseEntity<List<ClassificationDTO>> getClassifications() {
        List<ClassificationDTO> classifications = classificationController.getClassifications();
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassificationDTO> getClassificationById(@PathVariable Long id) {
        return classificationController.getClassificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClassificationDTO> getClassificationsByNom(@PathVariable String nom) {
        return classificationController.getClassificationByNom(nom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ClassificationDTO>> getClassificationsByNomContaining(@PathVariable String keyword) {
        List<ClassificationDTO> classifications = classificationController.getClassificationsByNomContaining(keyword);
        return ResponseEntity.ok(classifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Long id) {
        if (classificationController.getClassificationById(id).isPresent()) {
            classificationController.deleteClassificationById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}