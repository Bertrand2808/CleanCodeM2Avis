package fr.esgi.avis.application.Avis.model;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Avis Entity for Avis model
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AvisEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NonNull
    private String description;

    /*@ManyToOne
    @NonNull
    private Jeu jeu;
     */

    @ManyToOne
    @NonNull
    private JoueurEntity joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;

    /*@ManyToOne
    private Moderateur moderateur;*/

    public AvisEntity(Long id, String description, JoueurEntity joueur, Float note, LocalDateTime dateDEnvoi) {
        this.id = id;
        this.description = description;
        this.joueur = joueur;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
    }

}
