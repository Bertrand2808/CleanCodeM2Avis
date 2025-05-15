package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;

/**
 * Mapper class to convert AvatarEntity objects to Avatar domain.
 */
public class AvatarMapper {

    private AvatarMapper() {}

    /**
     * Convert an AvatarEntity to a domain Avatar object.
     * @param avatarEntity the entity to convert
     * @return Avatar domain object
     * @throws IllegalArgumentException if avatarEntity is null
     */
    public static Avatar toDomain(AvatarEntity avatarEntity) {
        if (avatarEntity == null) return null;
        return new Avatar(
                avatarEntity.getId(),
                avatarEntity.getNom(),
                avatarEntity.getJoueurId()
        );
    }

    /**
     * Convert an Avatar domain object to an AvatarEntity for persistence.
     * @param avatar the domain object to convert
     * @return AvatarEntity persistence object
     * @throws IllegalArgumentException if avatar is null
     */
    public static AvatarEntity toEntity(Avatar avatar) {
        if (avatar == null) return null;
        AvatarEntity avatarEntity = new AvatarEntity();
        avatarEntity.setId(avatar.getId());
        avatarEntity.setNom(avatar.getNom());
        avatarEntity.setJoueurId(avatar.getJoueurId());

        return avatarEntity;
    }
}
