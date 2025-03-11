package fr.esgi.avis.usecases.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avatar.AvatarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvatarUseCasesTest {

    @Mock
    private AvatarRepository avatarRepository;

    @InjectMocks
    private AvatarUseCases avatarUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAvatarSuccessfully() {
        // GIVEN
        String avatarName = "Warrior";
        Avatar avatar = new Avatar(avatarName);
        when(avatarRepository.save(any(Avatar.class))).thenReturn(avatar);

        // WHEN
        Avatar createdAvatar = avatarUseCases.createAvatar(avatarName);

        // THEN
        assertNotNull(createdAvatar);
        assertEquals(avatarName, createdAvatar.getNom());
        verify(avatarRepository, times(1)).save(any(Avatar.class));
    }

    @Test
    void shouldGetAvatarByIdWhenExists() {
        // GIVEN
        Long avatarId = 1L;
        Avatar avatar = new Avatar("Mage");
        avatar.setId(avatarId);
        when(avatarRepository.findById(avatarId)).thenReturn(Optional.of(avatar));

        // WHEN
        Optional<Avatar> foundAvatar = avatarUseCases.getAvatarById(avatarId);

        // THEN
        assertTrue(foundAvatar.isPresent());
        assertEquals(avatarId, foundAvatar.get().getId());
        verify(avatarRepository, times(1)).findById(avatarId);
    }

    @Test
    void shouldReturnEmptyWhenAvatarNotFound() {
        // GIVEN
        Long avatarId = 1L;
        when(avatarRepository.findById(avatarId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Avatar> foundAvatar = avatarUseCases.getAvatarById(avatarId);

        // THEN
        assertFalse(foundAvatar.isPresent());
        verify(avatarRepository, times(1)).findById(avatarId);
    }

    @Test
    void shouldDeleteAvatarSuccessfully() {
        // GIVEN
        Long avatarId = 1L;
        doNothing().when(avatarRepository).deleteById(avatarId);

        // WHEN
        avatarUseCases.deleteAvatar(avatarId);

        // THEN
        verify(avatarRepository, times(1)).deleteById(avatarId);
    }
}
