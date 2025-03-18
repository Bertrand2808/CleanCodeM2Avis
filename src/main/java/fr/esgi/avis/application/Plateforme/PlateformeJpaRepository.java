package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlateformeJpaRepository extends JpaRepository<PlateformeEntity, Long> {
    Optional<PlateformeEntity> findByNom(String nom);
    List<PlateformeEntity> findByNomContaining(String keyword);
}
