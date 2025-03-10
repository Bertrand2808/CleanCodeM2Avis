package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;

/**
 * Mapper pour les utilisateurs (persistance <-> domaine)
 */
public class UtilisateurMapper {

    /**
     * Convert UtilisateurEntity to Utilisateur (domaine)
     * @param utilisateurEntity UtilisateurEntity
     * @return UtilisateurEntity
     */
    public static Utilisateur toDomain(UtilisateurEntity utilisateurEntity) {
        if (utilisateurEntity == null) {
            return null;
        }
        if (utilisateurEntity instanceof JoueurEntity joueurEntity) {
            return Joueur.builder()
                    .id(joueurEntity.getId())
                    .pseudo(joueurEntity.getPseudo())
                    .motDePasse(joueurEntity.getMotDePasse())
                    .email(joueurEntity.getEmail())
                    .dateDeNaissance(joueurEntity.getDateDeNaissance())
                    .build();
        }
        throw new IllegalArgumentException(
                "Type d'utilisateur non géré : " + utilisateurEntity.getClass().getSimpleName()
        );
    }


    /**
     * Convert Utilisateur to UtilisateurEntity (persistance)
     * @param utilisateur Utilisateur
     * @return UtilisateurEntity
     */
    public static UtilisateurEntity toEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        if (utilisateur instanceof Joueur joueur) {
            return JoueurEntity.builder()
                    .id(joueur.getId())
                    .pseudo(joueur.getPseudo())
                    .motDePasse(joueur.getMotDePasse())
                    .email(joueur.getEmail())
                    .dateDeNaissance(joueur.getDateDeNaissance())
                    .build();
        }
        throw new IllegalArgumentException(
                "Type d'utilisateur non géré : " + utilisateur.getClass().getSimpleName()
        );
    }
}
