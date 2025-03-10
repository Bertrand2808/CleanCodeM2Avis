package fr.esgi.avis.domain.Avatar.model;

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

    public Avatar(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    //private Joueur joueur;
}
