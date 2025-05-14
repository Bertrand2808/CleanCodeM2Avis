package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.AvatarJpaRepository;
import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JoueurJpaAdapterTest {

    @Mock
    private JoueurJpaRepository joueurJpaRepository;

    @Mock
    private AvatarJpaRepository avatarJpaRepository;

    @InjectMocks
    private JoueurJpaAdapter joueurJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveJoueurSuccessfully() {
        // GIVEN
        Joueur joueur = createJoueur();
        JoueurEntity joueurEntity = JoueurMapper.toEntity(joueur);
        when(joueurJpaRepository.save(any(JoueurEntity.class))).thenReturn(joueurEntity);

        // WHEN
        Joueur savedJoueur = joueurJpaAdapter.save(joueur);

        // THEN
        assertNotNull(savedJoueur);
        assertEquals(joueur.getPseudo(), savedJoueur.getPseudo());
        verify(joueurJpaRepository, times(1)).save(any(JoueurEntity.class));
    }

    @Test
    void shouldFindAllJoueursSuccessfully() {
        // GIVEN
        JoueurEntity joueurEntity1 = createJoueurEntity();
        JoueurEntity joueurEntity2 = createJoueurEntity();
        List<JoueurEntity> listJoueurs = new ArrayList<>();
        listJoueurs.add(joueurEntity1);
        listJoueurs.add(joueurEntity2);
        when(joueurJpaRepository.findAll()).thenReturn(listJoueurs);

        // WHEN
        List<Joueur> foundJoueurs = joueurJpaAdapter.findAll();

        // THEN
        assertFalse(foundJoueurs.isEmpty());
        assertEquals(2, foundJoueurs.size());
        verify(joueurJpaRepository, times(1)).findAll();
    }

    @Test
    void shouldFindJoueurByIdSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        JoueurEntity joueurEntity = createJoueurEntity();
        when(joueurJpaRepository.findById(joueurId)).thenReturn(Optional.of(joueurEntity));

        // WHEN
        Optional<Joueur> foundJoueur = joueurJpaAdapter.findById(joueurId);

        // THEN
        assertTrue(foundJoueur.isPresent());
        assertEquals(joueurEntity.getPseudo(), foundJoueur.get().getPseudo());
        verify(joueurJpaRepository, times(1)).findById(joueurId);
    }

    @Test
    void shouldReturnEmptyWhenJoueurNotFoundById() {
        // GIVEN
        Long joueurId = 1L;
        when(joueurJpaRepository.findById(joueurId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Joueur> foundJoueur = joueurJpaAdapter.findById(joueurId);

        // THEN
        assertFalse(foundJoueur.isPresent());
        verify(joueurJpaRepository, times(1)).findById(joueurId);
    }

    @Test
    void shouldFindJoueurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "JoueurTest";
        JoueurEntity joueurEntity = createJoueurEntity();
        when(joueurJpaRepository.findByPseudo(pseudo)).thenReturn(joueurEntity);

        // WHEN
        Optional<Joueur> foundJoueur = joueurJpaAdapter.findByPseudo(pseudo);

        // THEN
        assertTrue(foundJoueur.isPresent());
        assertEquals(pseudo, foundJoueur.get().getPseudo());
        verify(joueurJpaRepository, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldFindJoueurByDateDeNaissanceSuccessfully() {
        // GIVEN
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        JoueurEntity joueurEntity = createJoueurEntity();
        when(joueurJpaRepository.findByDateDeNaissance(birthdate)).thenReturn(Optional.of(joueurEntity));

        // WHEN
        Optional<Joueur> foundJoueur = joueurJpaAdapter.findByDateDeNaissance(birthdate);

        // THEN
        assertTrue(foundJoueur.isPresent());
        assertEquals(birthdate, foundJoueur.get().getDateDeNaissance());
        verify(joueurJpaRepository, times(1)).findByDateDeNaissance(birthdate);
    }

    @Test
    void shouldDeleteJoueurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "JoueurTest";
        doNothing().when(joueurJpaRepository).deleteByPseudo(pseudo);

        // WHEN
        joueurJpaAdapter.deleteByPseudo(pseudo);

        // THEN
        verify(joueurJpaRepository, times(1)).deleteByPseudo(pseudo);
    }

    @Test
    void shouldUpdateAvatarSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        JoueurEntity joueurEntity = createJoueurEntity();
        AvatarEntity avatarEntity = new AvatarEntity(avatarId, "Guerrier", null);

        when(joueurJpaRepository.findById(joueurId)).thenReturn(Optional.of(joueurEntity));
        when(avatarJpaRepository.findById(avatarId)).thenReturn(Optional.of(avatarEntity));
        when(joueurJpaRepository.save(any(JoueurEntity.class))).thenReturn(joueurEntity);

        // WHEN
        joueurJpaAdapter.updateAvatar(joueurId, avatarId);

        // THEN
        assertEquals(avatarId, joueurEntity.getAvatar().getId());
        verify(joueurJpaRepository, times(1)).save(any(JoueurEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingAvatarForNonExistingJoueurOrAvatar() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        when(joueurJpaRepository.findById(joueurId)).thenReturn(Optional.empty());
        when(avatarJpaRepository.findById(avatarId)).thenReturn(Optional.empty());

        // WHEN & THEN
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> joueurJpaAdapter.updateAvatar(joueurId, avatarId));

        assertEquals("Joueur ou Avatar introuvable.", exception.getMessage());
        verify(joueurJpaRepository, times(1)).findById(joueurId);
        verify(avatarJpaRepository, times(1)).findById(avatarId);
        verify(joueurJpaRepository, never()).save(any(JoueurEntity.class));
    }

    @Test
    void shouldCountJoueursSuccessfully() {
        // GIVEN
        when(joueurJpaRepository.count()).thenReturn(10L);

        // WHEN
        long count = joueurJpaAdapter.count();

        // THEN
        assertEquals(10L, count);
        verify(joueurJpaRepository, times(1)).count();
    }

    private JoueurEntity createJoueurEntity() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        AvatarEntity avatarEntity = new AvatarEntity(1L, "Guerrier", null);

        return JoueurEntity.builder()
                .id(10L)
                .pseudo("JoueurTest")
                .motDePasse("password")
                .email("joueur@example.com")
                .dateDeNaissance(birthdate)
                .avatar(avatarEntity)
                .build();
    }

    private Joueur createJoueur() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        return Joueur.builder()
                .id(10L)
                .pseudo("JoueurTest")
                .motDePasse("password")
                .email("joueur@example.com")
                .dateDeNaissance(birthdate)
                .build();
    }
}
