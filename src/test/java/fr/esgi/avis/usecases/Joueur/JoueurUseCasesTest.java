package fr.esgi.avis.usecases.Joueur;

import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JoueurUseCasesTest {

    @Mock
    private JoueurDataSourcePort joueurDataSourcePort;

    @Mock
    private AvatarDataSourcePort avatarDataSourcePort;

    @InjectMocks
    private JoueurUseCases joueurUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateJoueurSuccessfully() {
        // GIVEN
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.save(any(Joueur.class))).thenReturn(joueur);

        // WHEN
        Joueur createdJoueur = joueurUseCases.createJoueur(joueur);

        // THEN
        assertNotNull(createdJoueur);
        assertEquals(joueur.getPseudo(), createdJoueur.getPseudo());
        verify(joueurDataSourcePort, times(1)).save(any(Joueur.class));
    }

    @Test
    void shouldGetAllJoueursSuccessfully() {
        // GIVEN
        List<Joueur> joueurs = List.of(createJoueur());
        when(joueurDataSourcePort.findAll()).thenReturn(joueurs);

        // WHEN
        List<Joueur> result = joueurUseCases.getAllJoueurs();

        // THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(joueurs.size(), result.size());
        verify(joueurDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetJoueurByIdSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findById(joueurId)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<Joueur> result = joueurUseCases.getJoueurById(joueurId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(joueur.getPseudo(), result.get().getPseudo());
        verify(joueurDataSourcePort, times(1)).findById(joueurId);
    }

    @Test
    void shouldReturnEmptyWhenJoueurNotFoundById() {
        // GIVEN
        Long joueurId = 1L;
        when(joueurDataSourcePort.findById(joueurId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Joueur> result = joueurUseCases.getJoueurById(joueurId);

        // THEN
        assertFalse(result.isPresent());
        verify(joueurDataSourcePort, times(1)).findById(joueurId);
    }

    @Test
    void shouldGetJoueurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "PlayerOne";
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findByPseudo(pseudo)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<Joueur> result = joueurUseCases.getJoueurByPseudo(pseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(pseudo, result.get().getPseudo());
        verify(joueurDataSourcePort, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldGetJoueurByBirthdateSuccessfully() {
        // GIVEN
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findByDateDeNaissance(birthdate)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<Joueur> result = joueurUseCases.getJoueurByBirthdate(birthdate);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(birthdate, result.get().getDateDeNaissance());
        verify(joueurDataSourcePort, times(1)).findByDateDeNaissance(birthdate);
    }

    @Test
    void shouldAssignAvatarToJoueurSuccessfully() {
        // GIVEN
        Joueur joueur = createJoueur();
        Avatar avatar = createAvatar();
        Long joueurId = joueur.getId();
        Long avatarId = avatar.getId();

        when(joueurDataSourcePort.findById(joueurId)).thenReturn(Optional.of(joueur));
        when(avatarDataSourcePort.findById(avatarId)).thenReturn(Optional.of(avatar));
        when(joueurDataSourcePort.save(any(Joueur.class))).thenReturn(joueur);
        when(avatarDataSourcePort.save(any(Avatar.class))).thenReturn(avatar);

        // WHEN
        Optional<Joueur> result = joueurUseCases.assignAvatarToJoueur(joueurId, avatarId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(avatarId, result.get().getAvatar().getId());
        verify(joueurDataSourcePort, times(1)).save(any(Joueur.class));
        verify(avatarDataSourcePort, times(1)).save(any(Avatar.class));
    }

    @Test
    void shouldReturnEmptyWhenAssigningAvatarToNonExistingJoueur() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        when(joueurDataSourcePort.findById(joueurId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Joueur> result = joueurUseCases.assignAvatarToJoueur(joueurId, avatarId);

        // THEN
        assertFalse(result.isPresent());
        verify(joueurDataSourcePort, times(1)).findById(joueurId);
        verify(avatarDataSourcePort, times(1)).findById(avatarId);
    }

    @Test
    void shouldReturnEmptyWhenAssigningNonExistingAvatarToJoueur() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findById(joueurId)).thenReturn(Optional.of(joueur));
        when(avatarDataSourcePort.findById(avatarId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Joueur> result = joueurUseCases.assignAvatarToJoueur(joueurId, avatarId);

        // THEN
        assertFalse(result.isPresent());
        verify(joueurDataSourcePort, times(1)).findById(joueurId);
        verify(avatarDataSourcePort, times(1)).findById(avatarId);
    }

    private Joueur createJoueur() {
        return Joueur.builder()
                .pseudo("PlayerOne")
                .motDePasse("password")
                .email("player@example.com")
                .dateDeNaissance(LocalDate.of(1995, 6, 15))
                .build();
    }

    private Avatar createAvatar() {
        return new Avatar(1L, "Warrior", null);
    }
}
