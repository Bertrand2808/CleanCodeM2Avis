package fr.esgi.avis.domain.Joueur.model;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joueur extends Utilisateur {

    private LocalDate dateDeNaissance;

    @Builder.Default
    private List<Avis> avis = new ArrayList<>();

    private Avatar avatar;
}
