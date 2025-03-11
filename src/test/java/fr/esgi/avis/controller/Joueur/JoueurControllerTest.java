package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.usecases.Joueur.JoueurUseCases;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JoueurControllerTest {

    @Mock
    private JoueurUseCases joueurUseCases;

    @InjectMocks
    private JoueurController joueurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateJoueurSuccessfully() {
        // GIVEN
        JoueurDTO joueurDTO = createJoueurDTO();
        Joueur joueur = JoueurDtoMapper.toDomain(joueurDTO);
        when(joueurUseCases.createJoueur(any(Joueur.class))).thenReturn(joueur);

        // WHEN
        JoueurDTO createdJoueur = joueurController.createJoueur(joueurDTO);

        // THEN
        assertNotNull(createdJoueur);
        assertEquals(joueurDTO.getPseudo(), createdJoueur.getPseudo());
        verify(joueurUseCases, times(1)).createJoueur(any(Joueur.class));
    }

    @Test
    void shouldGetAllJoueursSuccessfully() {
        // GIVEN
        List<Joueur> joueurs = List.of(createJoueur());
        when(joueurUseCases.getAllJoueurs()).thenReturn(joueurs);

        // WHEN
        List<JoueurDTO> result = joueurController.getAllJoueurs();

        // THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(joueurs.size(), result.size());
        verify(joueurUseCases, times(1)).getAllJoueurs();
    }

    @Test
    void shouldGetJoueurByIdSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        Joueur joueur = createJoueur();
        when(joueurUseCases.getJoueurById(joueurId)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<JoueurDTO> result = joueurController.getJoueurById(joueurId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(joueur.getPseudo(), result.get().getPseudo());
        verify(joueurUseCases, times(1)).getJoueurById(joueurId);
    }

    @Test
    void shouldReturnEmptyWhenJoueurNotFoundById() {
        // GIVEN
        Long joueurId = 1L;
        when(joueurUseCases.getJoueurById(joueurId)).thenReturn(Optional.empty());

        // WHEN
        Optional<JoueurDTO> result = joueurController.getJoueurById(joueurId);

        // THEN
        assertFalse(result.isPresent());
        verify(joueurUseCases, times(1)).getJoueurById(joueurId);
    }

    @Test
    void shouldGetJoueurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "PlayerOne";
        Joueur joueur = createJoueur();
        when(joueurUseCases.getJoueurByPseudo(pseudo)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<JoueurDTO> result = joueurController.getJoueurByPseudo(pseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(pseudo, result.get().getPseudo());
        verify(joueurUseCases, times(1)).getJoueurByPseudo(pseudo);
    }

    @Test
    void shouldGetJoueurByBirthdateSuccessfully() {
        // GIVEN
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        Joueur joueur = createJoueur();
        when(joueurUseCases.getJoueurByBirthdate(birthdate)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<JoueurDTO> result = joueurController.getJoueurByBirthdate(birthdate);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(birthdate, result.get().getDateDeNaissance());
        verify(joueurUseCases, times(1)).getJoueurByBirthdate(birthdate);
    }

    @Test
    void shouldAssignAvatarToJoueurSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        Joueur joueur = createJoueur();
        when(joueurUseCases.assignAvatarToJoueur(joueurId, avatarId)).thenReturn(Optional.of(joueur));

        // WHEN
        Optional<JoueurDTO> result = joueurController.assignAvatarToJoueur(joueurId, avatarId);

        // THEN
        assertTrue(result.isPresent());
        verify(joueurUseCases, times(1)).assignAvatarToJoueur(joueurId, avatarId);
    }

    @Test
    void shouldReturnEmptyWhenAssigningAvatarToNonExistingJoueur() {
        // GIVEN
        Long joueurId = 1L;
        Long avatarId = 2L;
        when(joueurUseCases.assignAvatarToJoueur(joueurId, avatarId)).thenReturn(Optional.empty());

        // WHEN
        Optional<JoueurDTO> result = joueurController.assignAvatarToJoueur(joueurId, avatarId);

        // THEN
        assertFalse(result.isPresent());
        verify(joueurUseCases, times(1)).assignAvatarToJoueur(joueurId, avatarId);
    }

    private Joueur createJoueur() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        return Joueur.builder()
                .pseudo("PlayerOne")
                .motDePasse("password")
                .email("player@example.com")
                .dateDeNaissance(birthdate)
                .build();
    }

    private JoueurDTO createJoueurDTO() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setPseudo("PlayerOne");
        joueurDTO.setMotDePasse("password");
        joueurDTO.setEmail("player@example.com");
        joueurDTO.setDateDeNaissance(birthdate);
        joueurDTO.setAvis(new ArrayList<>());
        return joueurDTO;
    }
}
