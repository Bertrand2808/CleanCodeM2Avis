package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisJpaRepository extends JpaRepository<AvisEntity, Long> {
    @Query("SELECT a FROM AvisEntity a WHERE a.jeu.id = :jeuId")
    List<AvisEntity> findByJeu_Id(@Param("jeuId") Long jeuId);
}
