package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvatarMapperTest {

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        // GIVEN
        AvatarEntity avatarEntity = new AvatarEntity(1L, "Warrior");

        // WHEN
        Avatar avatar = AvatarMapper.toDomain(avatarEntity);

        // THEN
        assertNotNull(avatar);
        assertEquals(avatarEntity.getId(), avatar.getId());
        assertEquals(avatarEntity.getNom(), avatar.getNom());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        // GIVEN
        Avatar avatar = new Avatar(1L, "Mage");

        // WHEN
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);

        // THEN
        assertNotNull(avatarEntity);
        assertEquals(avatar.getId(), avatarEntity.getId());
        assertEquals(avatar.getNom(), avatarEntity.getNom());
    }

    @Test
    void shouldThrowExceptionWhenEntityIsNull() {
        // WHEN & THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AvatarMapper.toDomain(null));
        assertEquals("AvatarEntity cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAvatarIsNull() {
        // WHEN & THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> AvatarMapper.toEntity(null));
        assertEquals("Avatar cannot be null", exception.getMessage());
    }
}
