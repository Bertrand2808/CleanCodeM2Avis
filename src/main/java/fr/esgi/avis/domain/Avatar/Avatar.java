package fr.esgi.avis.domain.Avatar;

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

    //private Joueur joueur;
}
