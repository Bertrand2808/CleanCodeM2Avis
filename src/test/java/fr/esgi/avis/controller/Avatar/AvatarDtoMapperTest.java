package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvatarDtoMapperTest {

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        // GIVEN
        Avatar avatar = new Avatar();
        avatar.setId(1L);
        avatar.setNom("Warrior");

        // WHEN
        AvatarDTO avatarDTO = AvatarDtoMapper.toDto(avatar);

        // THEN
        assertNotNull(avatarDTO);
        assertEquals(avatar.getId(), avatarDTO.getId());
        assertEquals(avatar.getNom(), avatarDTO.getNom());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        // GIVEN
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(2L);
        avatarDTO.setNom("Mage");

        // WHEN
        Avatar avatar = AvatarDtoMapper.toDomain(avatarDTO);

        // THEN
        assertNotNull(avatar);
        assertEquals(avatarDTO.getId(), avatar.getId());
        assertEquals(avatarDTO.getNom(), avatar.getNom());
    }

    @Test
    void shouldReturnNullWhenAvatarIsNull() {
        // WHEN
        AvatarDTO avatarDTO = AvatarDtoMapper.toDto(null);

        // THEN
        assertNull(avatarDTO);
    }

    @Test
    void shouldReturnNullWhenAvatarDtoIsNull() {
        // WHEN
        Avatar avatar = AvatarDtoMapper.toDomain(null);

        // THEN
        assertNull(avatar);
    }
}
