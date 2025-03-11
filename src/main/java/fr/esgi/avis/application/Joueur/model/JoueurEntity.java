package fr.esgi.avis.application.Joueur.model;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Joueur Entity for Joueur model
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class JoueurEntity extends UtilisateurEntity {

    @NonNull
    private LocalDate dateDeNaissance;

    @Builder.Default
    @OneToMany(mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<AvisEntity> avis = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private AvatarEntity avatar;
}
