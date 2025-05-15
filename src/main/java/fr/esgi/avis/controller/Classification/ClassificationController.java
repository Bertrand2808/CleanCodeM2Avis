package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.useCases.Classification.ClassificationUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationUseCases classificationUseCases;

    public ClassificationDTO createClassification(ClassificationDTO classificationDTO) {
        Classification classification = ClassificationDtoMapper.toDomain(classificationDTO);
        Classification createdClassification = classificationUseCases.createClassification(classification);
        return ClassificationDtoMapper.toDto(createdClassification);
    }

    public List<ClassificationDTO> getClassifications() {
        return classificationUseCases.getClassifications().stream()
                .map(ClassificationDtoMapper::toDto)
                .toList();
    }

    public Optional<ClassificationDTO> getClassificationById(Long id) {
        return classificationUseCases.getClassificationById(id)
                .map(ClassificationDtoMapper::toDto);
    }

    public Optional<ClassificationDTO> getClassificationByNom(String nom) {
        return classificationUseCases.getClassificationByNom(nom)
                .map(ClassificationDtoMapper::toDto);
    }

    public List<ClassificationDTO> getClassificationsByNomContaining(String keyword) {
        return classificationUseCases.getClassificationsByNomContaining(keyword).stream()
                .map(ClassificationDtoMapper::toDto)
                .toList();
    }

    public void deleteClassificationById(Long id) {
        classificationUseCases.deleteClassificationById(id);
    }
}
