package fr.esgi.avis.domain.Avatar;

import java.util.Optional;

import fr.esgi.avis.domain.Avatar.model.Avatar;

public interface AvatarDataSourcePort {
    Avatar save(Avatar avatar);
    Optional<Avatar> findById(Long id);
    void deleteById(Long id);
    long count();
}
