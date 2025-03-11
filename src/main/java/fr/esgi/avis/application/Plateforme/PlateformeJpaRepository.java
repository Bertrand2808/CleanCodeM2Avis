package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlateformeJpaRepository extends JpaRepository<PlateformeEntity, Long> {
    Optional<PlateformeEntity> findByName(String name);
}
