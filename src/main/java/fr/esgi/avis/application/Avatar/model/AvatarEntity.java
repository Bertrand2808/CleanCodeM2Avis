package fr.esgi.avis.application.Avatar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    //@OneToOne(mappedBy="avatar", fetch= FetchType.LAZY)
    // private JoueurEntity joueur;

    // TODO : Add JoueurEntity to constructor
    public AvatarEntity(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
