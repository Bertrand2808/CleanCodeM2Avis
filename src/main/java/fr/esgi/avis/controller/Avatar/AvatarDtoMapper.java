package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;

public class AvatarDtoMapper {

    /**
     * Convert Avatar to AvatarDTO
     * @param avatar Avatar
     * @return AvatarDTO
     */
    public static AvatarDTO toDto(Avatar avatar) {
        if (avatar == null) return null;
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(avatar.getId());
        avatarDTO.setNom(avatar.getNom());
        return avatarDTO;
    }

    /**
     * Convert AvatarDTO to Avatar
     * @param avatarDTO
     * @return Avatar
     */
    public static Avatar toDomain(AvatarDTO avatarDTO) {
        if (avatarDTO == null) return null;
        Avatar avatar = new Avatar();
        avatar.setId(avatarDTO.getId());
        avatar.setNom(avatarDTO.getNom());
        return avatar;
    }
}
