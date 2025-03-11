package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModerateurJpaAdapterTest {

    @Mock
    private ModerateurJpaRepository moderateurJpaRepository;

    @InjectMocks
    private ModerateurJpaAdapter moderateurJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveModerateurSuccessfully() {
        // GIVEN
        Moderateur moderateur = createModerateur();
        ModerateurEntity moderateurEntity = ModerateurMapper.toEntity(moderateur);

        when(moderateurJpaRepository.save(any(ModerateurEntity.class))).thenReturn(moderateurEntity);

        // WHEN
        Moderateur savedModerateur = moderateurJpaAdapter.save(moderateur);

        // THEN
        assertNotNull(savedModerateur);
        assertEquals(moderateur.getPseudo(), savedModerateur.getPseudo());
        verify(moderateurJpaRepository, times(1)).save(any(ModerateurEntity.class));
    }

    @Test
    void shouldFindModerateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        ModerateurEntity moderateurEntity = createModerateurEntity();
        when(moderateurJpaRepository.findById(id)).thenReturn(Optional.of(moderateurEntity));

        // WHEN
        Optional<Moderateur> result = moderateurJpaAdapter.findById(id);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(moderateurEntity.getPseudo(), result.get().getPseudo());
        verify(moderateurJpaRepository, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmptyWhenModerateurNotFoundById() {
        // GIVEN
        Long id = 999L;
        when(moderateurJpaRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Optional<Moderateur> result = moderateurJpaAdapter.findById(id);

        // THEN
        assertFalse(result.isPresent());
        verify(moderateurJpaRepository, times(1)).findById(id);
    }

    @Test
    void shouldFindModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        ModerateurEntity moderateurEntity = createModerateurEntity();
        when(moderateurJpaRepository.findByPseudo(pseudo)).thenReturn(Optional.of(moderateurEntity));

        // WHEN
        Optional<Moderateur> result = moderateurJpaAdapter.findByPseudo(pseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(pseudo, result.get().getPseudo());
        verify(moderateurJpaRepository, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldFindModerateurByEmailSuccessfully() {
        // GIVEN
        String email = "admin@example.com";
        ModerateurEntity moderateurEntity = createModerateurEntity();
        when(moderateurJpaRepository.findByEmail(email)).thenReturn(Optional.of(moderateurEntity));

        // WHEN
        Optional<Moderateur> result = moderateurJpaAdapter.findByEmail(email);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(moderateurJpaRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldFindModerateurByNumeroDeTelephoneSuccessfully() {
        // GIVEN
        String numero = "+33123456789";
        ModerateurEntity moderateurEntity = createModerateurEntity();
        when(moderateurJpaRepository.findByNumeroDeTelephone(numero)).thenReturn(Optional.of(moderateurEntity));

        // WHEN
        Optional<Moderateur> result = moderateurJpaAdapter.findByNumeroDeTelephone(numero);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(numero, result.get().getNumeroDeTelephone());
        verify(moderateurJpaRepository, times(1)).findByNumeroDeTelephone(numero);
    }

    @Test
    void shouldDeleteModerateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        doNothing().when(moderateurJpaRepository).deleteById(id);

        // WHEN
        moderateurJpaAdapter.deleteById(id);

        // THEN
        verify(moderateurJpaRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldDeleteModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        doNothing().when(moderateurJpaRepository).deleteByPseudo(pseudo);

        // WHEN
        moderateurJpaAdapter.deleteByPseudo(pseudo);

        // THEN
        verify(moderateurJpaRepository, times(1)).deleteByPseudo(pseudo);
    }

    @Test
    void shouldFindAllModerateursSuccessfully() {
        // GIVEN
        List<ModerateurEntity> moderateurEntities = List.of(createModerateurEntity());
        when(moderateurJpaRepository.findAll()).thenReturn(moderateurEntities);

        // WHEN
        List<Moderateur> result = moderateurJpaAdapter.findAll();

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(moderateurEntities.size(), result.size());
        verify(moderateurJpaRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnModerateurCountSuccessfully() {
        // GIVEN
        when(moderateurJpaRepository.count()).thenReturn(5L);

        // WHEN
        long count = moderateurJpaAdapter.count();

        // THEN
        assertEquals(5L, count);
        verify(moderateurJpaRepository, times(1)).count();
    }

    private Moderateur createModerateur() {
        return Moderateur.builder()
                .id(1L)
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();
    }

    private ModerateurEntity createModerateurEntity() {
        return ModerateurEntity.builder()
                .id(1L)
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();
    }
}
