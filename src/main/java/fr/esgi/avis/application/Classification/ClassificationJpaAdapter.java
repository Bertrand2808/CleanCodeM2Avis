package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.ClassificationJpaRepository;
import fr.esgi.avis.application.Classification.ClassificationMapper;
import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClassificationJpaAdapter implements ClassificationDataSourcePort {
    private final ClassificationJpaRepository classificationJpaRepository;

    @Override
    public Classification save(Classification classification) {
        ClassificationEntity classificationEntity = ClassificationMapper.toEntity(classification);
        return ClassificationMapper.toDomain(classificationJpaRepository.save(classificationEntity));
    }

    @Override
    public List<Classification> findAll() {
        return classificationJpaRepository.findAll()
                .stream()
                .map(ClassificationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Classification> findById(Long id) {
        return classificationJpaRepository.findById(id).map(ClassificationMapper::toDomain);
    }

    @Override
    public Optional<Classification> findByNom(String nom) {
        return classificationJpaRepository.findByNom(nom).map(ClassificationMapper::toDomain);
    }

    @Override
    public List<Classification> findByNomContaining(String keyword) { // ✅ Implemented method
        return classificationJpaRepository.findByNomContaining(keyword)
                .stream()
                .map(ClassificationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        classificationJpaRepository.deleteById(id);
    }
}
