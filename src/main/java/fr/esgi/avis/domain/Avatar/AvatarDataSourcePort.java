package fr.esgi.avis.domain.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;

import java.util.Optional;

public interface AvatarDataSourcePort {
    Avatar save(Avatar avatar);
    Optional<Avatar> findById(Long id);
    void deleteById(Long id);
}
