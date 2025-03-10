package fr.esgi.avis.usecases.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avatar.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Avatar use cases for business logic
 */
@RequiredArgsConstructor
@Service
public class AvatarUseCases {

    private final AvatarRepository avatarRepository;

    /**
     * Create an avatar
     * @param nom de l'avatar
     * @return
     */
    public Avatar createAvatar(String nom) {
        return avatarRepository.save(new Avatar(nom));
    }

    /**
     * Get an avatar by id
     * @param id de l'avatar
     * @return
     */
    public Optional<Avatar> getAvatarById(Long id) {
        return avatarRepository.findById(id);
    }

    /**
     * Update an avatar
     * @param id de l'avatar
     * @return
     */
    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }
}
