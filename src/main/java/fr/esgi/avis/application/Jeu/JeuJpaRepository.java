package fr.esgi.avis.application.Jeu;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.avis.application.Jeu.model.JeuEntity;

public interface JeuJpaRepository extends JpaRepository<JeuEntity, Long> {}