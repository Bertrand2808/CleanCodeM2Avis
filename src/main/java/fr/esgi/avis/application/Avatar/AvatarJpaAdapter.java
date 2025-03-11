package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AvatarJpaAdapter implements AvatarDataSourcePort {

    private final AvatarJpaRepository avatarJpaRepository;

    @Override
    public Avatar save(Avatar avatar) {
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);
        return AvatarMapper.toDomain(avatarJpaRepository.save(avatarEntity));
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarJpaRepository.findById(id).map(AvatarMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        avatarJpaRepository.deleteById(id);
    }
}
