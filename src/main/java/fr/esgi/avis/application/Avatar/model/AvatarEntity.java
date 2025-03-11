package fr.esgi.avis.application.Avatar.model;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Avatar Entity
 */
@Entity
@Data
@NoArgsConstructor
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Long joueurId;

    public AvatarEntity(Long id, String nom, Long joueurId) {
        this.id = id;
        this.nom = nom;
        this.joueurId = joueurId;
    }
}
