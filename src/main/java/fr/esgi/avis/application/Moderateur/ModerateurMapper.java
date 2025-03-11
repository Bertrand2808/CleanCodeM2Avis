package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;

/**
 * Mapper pour convertir entre ModerateurEntity et Moderateur (domaine).
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
public class ModerateurMapper {

    /**
     * Convertit une entité `ModerateurEntity` en domaine `Moderateur`.
     *
     * @param moderateurEntity L'entité à convertir.
     * @return L'objet domaine `Moderateur` correspondant.
     */
    public static Moderateur toDomain(ModerateurEntity moderateurEntity) {
        if (moderateurEntity == null) {
            return null;
        }

        return Moderateur.builder()
                .id(moderateurEntity.getId())  // Hérité de UtilisateurEntity
                .pseudo(moderateurEntity.getPseudo())
                .motDePasse(moderateurEntity.getMotDePasse())
                .email(moderateurEntity.getEmail())
                .numeroDeTelephone(moderateurEntity.getNumeroDeTelephone())
                .build();
    }

    /**
     * Convertit un objet `Moderateur` en entité `ModerateurEntity` pour la persistance.
     *
     * @param moderateur L'objet domaine à convertir.
     * @return L'entité `ModerateurEntity` correspondante.
     */
    public static ModerateurEntity toEntity(Moderateur moderateur) {
        if (moderateur == null) {
            return null;
        }

        return ModerateurEntity.builder()
                .id(moderateur.getId())  // Hérité de Utilisateur
                .pseudo(moderateur.getPseudo())
                .motDePasse(moderateur.getMotDePasse())
                .email(moderateur.getEmail())
                .numeroDeTelephone(moderateur.getNumeroDeTelephone())
                .build();
    }
}
