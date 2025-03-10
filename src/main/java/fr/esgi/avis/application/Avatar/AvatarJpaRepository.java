package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarJpaRepository extends JpaRepository<AvatarEntity, Long> {
}
