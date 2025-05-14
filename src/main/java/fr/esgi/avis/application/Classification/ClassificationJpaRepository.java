package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassificationJpaRepository extends JpaRepository<ClassificationEntity, Long> {
    Optional<ClassificationEntity> findByNom(String nom);
    List<ClassificationEntity> findByNomContaining(String keyword);
}