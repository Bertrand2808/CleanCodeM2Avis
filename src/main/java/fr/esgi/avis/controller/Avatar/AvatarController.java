package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.usecases.Avatar.AvatarUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Controller for avatar : manage the requests with the use cases
 */
@Controller
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarUseCases avatarUseCases;

    /**
     * Create an avatar
     * @param avatarDTO
     * @return
     */
    public AvatarDTO createAvatar(AvatarDTO avatarDTO) {
        Avatar avatar = AvatarDtoMapper.toDomain(avatarDTO);
        Avatar createdAvatar = avatarUseCases.createAvatar(avatar.getNom());
        return AvatarDtoMapper.toDto(createdAvatar);
    }

    /**
     * Get an avatar by id
     * @param id
     * @return
     */
    public Optional<AvatarDTO> getAvatarById(Long id) {
        return avatarUseCases.getAvatarById(id)
                .map(AvatarDtoMapper::toDto);
    }

    /**
     * Delete an avatar by id
     * @param id
     */
    public void deleteAvatarById(Long id) {
        avatarUseCases.deleteAvatar(id);
    }
}
