package fr.esgi.avis.application.Avatar;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AvatarJpaAdapter implements AvatarDataSourcePort {

    private final AvatarJpaRepository avatarJpaRepository;

    /**
     * Save an Avatar
     * @param avatar
     * @return Avatar
     */
    @Override
    public Avatar save(Avatar avatar) {
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);
        return AvatarMapper.toDomain(avatarJpaRepository.save(avatarEntity));
    }

    /**
     * Find an Avatar by its id
     * @param id
     * @return Optional<Avatar>
     */
    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarJpaRepository.findById(id).map(AvatarMapper::toDomain);
    }

    /**
     * Delete an Avatar by its id
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        avatarJpaRepository.deleteById(id);
    }

    /**
     * Count the number of Avatars
     * @return long
     */
    @Override
    public long count() {
        return avatarJpaRepository.count();
    }
}
