package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JoueurJpaRepository extends JpaRepository<JoueurEntity, Long> {
    JoueurEntity findByPseudo(String pseudo);
    Optional<JoueurEntity> findByDateDeNaissance(LocalDate dateDeNaissance);
    void deleteByPseudo(String pseudo);
}
