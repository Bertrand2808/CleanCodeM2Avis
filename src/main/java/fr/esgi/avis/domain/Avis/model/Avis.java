package fr.esgi.avis.domain.Avis.model;

import fr.esgi.avis.domain.Joueur.model.Joueur;
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

    //@NonNull
    //private Jeu jeu;

    @NonNull
    private Long joueurId;

    private Float note;

    private LocalDateTime dateDEnvoi;

    //private Moderateur moderateur;

    public Avis(Long id, @NonNull String description, @NonNull Long joueurId, Float note, LocalDateTime dateDEnvoi) {
        this.id = id;
        this.description = description;
        this.joueurId = joueurId;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
    }
}
