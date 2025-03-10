package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;

public class AvatarMapper {

    /**
     * Convert an AvatarEntity to a domain Avatar object.
     * @param avatarEntity the entity to convert
     * @return Avatar domain object
     * @throws IllegalArgumentException if avatarEntity is null
     */
    public static Avatar toDomain(AvatarEntity avatarEntity) {
        if (avatarEntity == null) {
            throw new IllegalArgumentException("AvatarEntity cannot be null");
        }
        return new Avatar(avatarEntity.getId(), avatarEntity.getNom());
    }

    /**
     * Convert an Avatar domain object to an AvatarEntity for persistence.
     * @param avatar the domain object to convert
     * @return AvatarEntity persistence object
     * @throws IllegalArgumentException if avatar is null
     */
    public static AvatarEntity toEntity(Avatar avatar) {
        if (avatar == null) {
            throw new IllegalArgumentException("Avatar cannot be null");
        }
        return new AvatarEntity(avatar.getId(), avatar.getNom());
    }
}
