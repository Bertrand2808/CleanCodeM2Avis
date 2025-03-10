package fr.esgi.avis.domain.Avatar.model;

import fr.esgi.avis.domain.Joueur.model.Joueur;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Avatar model
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avatar {

    private Long id;

    @NonNull
    private String nom;

    private Long joueurId;

    public Avatar(Long id, String nom, Long joueurId) {
        this.id = id;
        this.nom = nom;
        this.joueurId = joueurId;
    }
}
