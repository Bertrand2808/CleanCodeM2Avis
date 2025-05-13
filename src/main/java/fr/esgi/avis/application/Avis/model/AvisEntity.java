package fr.esgi.avis.application.Avis.model;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Avis Entity for Avis model
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class AvisEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NonNull
    private String description;

    @ManyToOne
    @NonNull
    private JeuEntity jeu;

    @ManyToOne
    @NonNull
    private JoueurEntity joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;

    @ManyToOne
    private ModerateurEntity moderateur;

    public AvisEntity(Long id, String description, JeuEntity jeu, JoueurEntity joueur, Float note, LocalDateTime dateDEnvoi, ModerateurEntity moderateur) {
        this.id = id;
        this.description = description;
        this.jeu = jeu;
        this.joueur = joueur;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
        this.moderateur = moderateur;
    }

}
