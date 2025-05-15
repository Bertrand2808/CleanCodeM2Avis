package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvatarJpaAdapterTest {

    @Mock
    private AvatarJpaRepository avatarRepository;

    @InjectMocks
    private AvatarJpaAdapter avatarJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAvatarSuccessfully() {
        // GIVEN
        Avatar avatar = new Avatar("Warrior");
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);
        when(avatarRepository.save(any(AvatarEntity.class))).thenReturn(avatarEntity);

        // WHEN
        Avatar savedAvatar = avatarJpaAdapter.save(avatar);

        // THEN
        assertNotNull(savedAvatar);
        assertEquals(avatar.getNom(), savedAvatar.getNom());
        verify(avatarRepository, times(1)).save(any(AvatarEntity.class));
    }

    @Test
    void shouldFindAvatarByIdWhenExists() {
        // GIVEN
        Long avatarId = 1L;
        Avatar avatar = new Avatar("Mage");
        avatar.setId(avatarId);
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);
        when(avatarRepository.findById(avatarId)).thenReturn(Optional.of(avatarEntity));

        // WHEN
        Optional<Avatar> foundAvatar = avatarJpaAdapter.findById(avatarId);

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
        Optional<Avatar> foundAvatar = avatarJpaAdapter.findById(avatarId);

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
        avatarJpaAdapter.deleteById(avatarId);

        // THEN
        verify(avatarRepository, times(1)).deleteById(avatarId);
    }

    @Test
    void shouldNotFailWhenDeletingNonExistentAvatar() {
        // GIVEN
        Long avatarId = 999L;
        doNothing().when(avatarRepository).deleteById(avatarId);

        // WHEN
        avatarJpaAdapter.deleteById(avatarId);

        // THEN
        verify(avatarRepository, times(1)).deleteById(avatarId);
    }

    @Test
    void shouldCountAvatarsSuccessfully() {
        // GIVEN
        long expectedCount = 5L;
        when(avatarRepository.count()).thenReturn(expectedCount);

        // WHEN
        long actualCount = avatarJpaAdapter.count();

        // THEN
        assertEquals(expectedCount, actualCount);
        verify(avatarRepository, times(1)).count();
    }

    @Test
    void shouldReturnZeroWhenNoAvatarsExist() {
        // GIVEN
        long expectedCount = 0L;
        when(avatarRepository.count()).thenReturn(expectedCount);

        // WHEN
        long actualCount = avatarJpaAdapter.count();

        // THEN
        assertEquals(expectedCount, actualCount);
        verify(avatarRepository, times(1)).count();
    }
}
