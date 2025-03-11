package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.AvatarMapper;
import fr.esgi.avis.application.Avis.AvisMapper;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Joueur.model.Joueur;

import java.util.stream.Collectors;

public class JoueurMapper {

    /**
     * Convert JoueurEntity to Joueur (domaine)
     * @param joueurEntity JoueurEntity
     * @return Joueur
     */
    public static Joueur toDomain(JoueurEntity joueurEntity) {
        if (joueurEntity == null) {
            return null;
        }

        return Joueur.builder()
                .id(joueurEntity.getId())
                .dateDeNaissance(joueurEntity.getDateDeNaissance())
                .avatar(AvatarMapper.toDomain(joueurEntity.getAvatar()))
                .avis(joueurEntity.getAvis().stream()
                        .map(AvisMapper::toDomain)
                        .collect(Collectors.toList()))
                .pseudo(joueurEntity.getPseudo())
                .email(joueurEntity.getEmail())
                .motDePasse(joueurEntity.getMotDePasse())
                .build();
    }

    /**
     * Convert Joueur to JoueurEntity (persistance)
     * @param joueur Joueur
     * @return JoueurEntity
     */
    public static JoueurEntity toEntity(Joueur joueur) {
        if (joueur == null) {
            return null;
        }

        return JoueurEntity.builder()
                .id(joueur.getId())
                .dateDeNaissance(joueur.getDateDeNaissance())
                .avatar(AvatarMapper.toEntity(joueur.getAvatar()))
                .avis(joueur.getAvis().stream()
                        .map(AvisMapper::toEntity)
                        .collect(Collectors.toList()))
                .pseudo(joueur.getPseudo())
                .email(joueur.getEmail())
                .motDePasse(joueur.getMotDePasse())
                .build();
    }
}
