package fr.esgi.avis.usecases.Avatar;

import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Avatar use cases for business logic
 */
@RequiredArgsConstructor
@Service
public class AvatarUseCases {

    private final AvatarDataSourcePort avatarDataSourcePort;

    /**
     * Create an avatar
     * @param nom de l'avatar
     * @return
     */
    public Avatar createAvatar(String nom) {
        return avatarDataSourcePort.save(new Avatar(nom));
    }

    /**
     * Get an avatar by id
     * @param id de l'avatar
     * @return
     */
    public Optional<Avatar> getAvatarById(Long id) {
        return avatarDataSourcePort.findById(id);
    }

    /**
     * Update an avatar
     * @param id de l'avatar
     * @return
     */
    public void deleteAvatar(Long id) {
        avatarDataSourcePort.deleteById(id);
    }
}
