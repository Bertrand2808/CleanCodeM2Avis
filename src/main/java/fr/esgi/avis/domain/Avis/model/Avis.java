package fr.esgi.avis.domain.Avis.model;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Avis model
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avis {

    private Long id;

    @NonNull
    private String description;

    @NonNull
    private Long jeuId;

    @NonNull
    private Long joueurId;

    private Float note;

    private LocalDateTime dateDEnvoi;

    @NonNull
    private Long moderateurId;

    public Avis(Long id, @NonNull String description,@NonNull Long jeuId, @NonNull Long joueurId, Float note, LocalDateTime dateDEnvoi, @NonNull Long moderateurId) {
        this.id = id;
        this.description = description;
        this.jeuId = jeuId;
        this.joueurId = joueurId;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
        this.moderateurId = moderateurId;
    }
}
