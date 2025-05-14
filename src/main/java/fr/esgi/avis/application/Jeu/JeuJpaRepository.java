package fr.esgi.avis.application.Jeu;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JeuJpaRepository extends JpaRepository<JeuEntity, Long> {
    Optional<JeuEntity> findByNom(String nom);
    List<JeuEntity> findByNomContaining(String keyword);
}
