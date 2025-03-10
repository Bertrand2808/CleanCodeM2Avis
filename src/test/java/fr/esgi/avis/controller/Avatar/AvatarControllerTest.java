package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.usecases.Avatar.AvatarUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvatarControllerTest {

    @Mock
    private AvatarUseCases avatarUseCases;

    @InjectMocks
    private AvatarController avatarController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAvatarSuccessfully() {
        // GIVEN
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setNom("Warrior");

        Avatar createdAvatar = new Avatar();
        createdAvatar.setId(1L);
        createdAvatar.setNom("Warrior");

        when(avatarUseCases.createAvatar("Warrior")).thenReturn(createdAvatar);

        // WHEN
        AvatarDTO result = avatarController.createAvatar(avatarDTO);

        // THEN
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Warrior", result.getNom());
        verify(avatarUseCases, times(1)).createAvatar("Warrior");
    }

    @Test
    void shouldGetAvatarByIdWhenExists() {
        // GIVEN
        Long avatarId = 1L;
        Avatar avatar = new Avatar();
        avatar.setId(avatarId);
        avatar.setNom("Mage");

        when(avatarUseCases.getAvatarById(avatarId)).thenReturn(Optional.of(avatar));

        // WHEN
        Optional<AvatarDTO> result = avatarController.getAvatarById(avatarId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(avatarId, result.get().getId());
        assertEquals("Mage", result.get().getNom());
        verify(avatarUseCases, times(1)).getAvatarById(avatarId);
    }

    @Test
    void shouldReturnEmptyWhenAvatarNotFound() {
        // GIVEN
        Long avatarId = 2L;
        when(avatarUseCases.getAvatarById(avatarId)).thenReturn(Optional.empty());

        // WHEN
        Optional<AvatarDTO> result = avatarController.getAvatarById(avatarId);

        // THEN
        assertFalse(result.isPresent());
        verify(avatarUseCases, times(1)).getAvatarById(avatarId);
    }

    @Test
    void shouldDeleteAvatarSuccessfully() {
        // GIVEN
        Long avatarId = 1L;
        doNothing().when(avatarUseCases).deleteAvatar(avatarId);

        // WHEN
        avatarController.deleteAvatarById(avatarId);

        // THEN
        verify(avatarUseCases, times(1)).deleteAvatar(avatarId);
    }
}
