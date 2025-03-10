package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avatar.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AvatarJpaAdapter implements AvatarRepository {

    private final AvatarJpaRepository avatarRepository;

    @Override
    public Avatar save(Avatar avatar) {
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);
        return AvatarMapper.toDomain(avatarRepository.save(avatarEntity));
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarRepository.findById(id).map(AvatarMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        avatarRepository.deleteById(id);
    }
}
