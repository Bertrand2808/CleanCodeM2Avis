package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisJpaRepository extends JpaRepository<AvisEntity, Long> {
}
