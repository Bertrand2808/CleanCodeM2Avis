package fr.esgi.avis.application.Editeur;

import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditeurJpaRepository extends JpaRepository<EditeurEntity, Long> {
    Optional<EditeurEntity> findByNom(String nom);
    List<EditeurEntity> findByNomContaining(String keyword);
}
