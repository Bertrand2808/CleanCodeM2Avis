package fr.esgi.avis.useCases.Classification;

import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassificationUseCases {

    private final ClassificationDataSourcePort ClassificationDataSourcePort;

    public Classification createClassification(Classification Classification) {
        return ClassificationDataSourcePort.save(Classification);
    }

    public List<Classification> getClassifications() {
        return ClassificationDataSourcePort.findAll();
    }

    public Optional<Classification> getClassificationById(Long id) {
        return ClassificationDataSourcePort.findById(id);
    }

    public Optional<Classification> getClassificationByNom(String nom) {
        return ClassificationDataSourcePort.findByNom(nom);
    }

    public List<Classification> getClassificationsByNomContaining(String keyword) {
        return ClassificationDataSourcePort.findByNomContaining(keyword);
    }

    public void deleteClassificationById(Long id) {
        ClassificationDataSourcePort.deleteById(id);
    }
}
