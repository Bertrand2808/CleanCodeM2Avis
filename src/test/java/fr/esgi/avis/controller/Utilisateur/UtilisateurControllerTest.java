package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.usecases.Utilisateur.UtilisateurUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurControllerTest {

    @Mock
    private UtilisateurUseCases utilisateurUseCases;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUtilisateurSuccessfully() {
        // GIVEN
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setPseudo("test");
        joueurDTO.setMotDePasse("password");
        joueurDTO.setEmail("test@exemple.com");
        joueurDTO.setDateDeNaissance(LocalDate.of(1990, 1, 1));


        Utilisateur createdUtilisateur = UtilisateurDtoMapper.toDomain(joueurDTO);

        when(utilisateurUseCases.createUtilisateur(any(Utilisateur.class))).thenReturn(createdUtilisateur);

        // WHEN
        UtilisateurDTO result = utilisateurController.createUtilisateur(joueurDTO);

        // THEN
        assertNotNull(result);
        assertEquals("test", result.getPseudo());
        assertEquals("password", result.getMotDePasse());
        assertEquals("test@exemple.com", result.getEmail());
        verify(utilisateurUseCases, times(1)).createUtilisateur(any(Utilisateur.class));
    }

    @Test
    void shouldGetUtilisateurByPseudoWhenExists() {
        // GIVEN
        Utilisateur utilisateur = createUtilisateur();
        String utilisateurPseudo = utilisateur.getPseudo();

        when(utilisateurUseCases.getUtilisateurByPseudo(utilisateurPseudo)).thenReturn(Optional.of(utilisateur));

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurByPseudo(utilisateurPseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals("test", result.get().getPseudo());
        assertEquals("test@example.com", result.get().getEmail());
        verify(utilisateurUseCases, times(1)).getUtilisateurByPseudo(utilisateurPseudo);
    }

    @Test
    void shouldReturnEmptyWhenGetUtilisateurByPseudoNotExists() {
        // GIVEN
        String pseudo = "unknownUser";
        when(utilisateurUseCases.getUtilisateurByPseudo(pseudo)).thenReturn(Optional.empty());

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurByPseudo(pseudo);

        // THEN
        assertTrue(result.isEmpty());
        verify(utilisateurUseCases, times(1)).getUtilisateurByPseudo(pseudo);
    }

    @Test
    void shouldGetUtilisateurByEmailWhenExists() {
        // GIVEN
        Utilisateur utilisateur = createUtilisateur();
        String utilisateurEmail = utilisateur.getEmail();

        when(utilisateurUseCases.getUtilisateurByEmail(utilisateurEmail)).thenReturn(Optional.of(utilisateur));

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurByEmail(utilisateurEmail);

        // THEN
        assertTrue(result.isPresent());
        assertEquals("test@exemple.com", result.get().getEmail());
        verify(utilisateurUseCases, times(1)).getUtilisateurByEmail(utilisateurEmail);
    }

    @Test
    void shouldReturnEmptyWhenGetUtilisateurByEmailNotExists() {
        // GIVEN
        String email = "unknown@example.com";
        when(utilisateurUseCases.getUtilisateurByEmail(email)).thenReturn(Optional.empty());

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurByEmail(email);

        // THEN
        assertTrue(result.isEmpty());
        verify(utilisateurUseCases, times(1)).getUtilisateurByEmail(email);
    }

    @Test
    void shouldGetUtilisateurByIdWhenExists() {
        // GIVEN
        Utilisateur utilisateur = createUtilisateur();
        Long id = utilisateur.getId();

        when(utilisateurUseCases.getUtilisateurById(id)).thenReturn(Optional.of(utilisateur));

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurById(id);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(utilisateur.getPseudo(), result.get().getPseudo());
        verify(utilisateurUseCases, times(1)).getUtilisateurById(id);
    }

    @Test
    void shouldReturnEmptyWhenGetUtilisateurByIdNotExists() {
        // GIVEN
        Long id = 999L;
        when(utilisateurUseCases.getUtilisateurById(id)).thenReturn(Optional.empty());

        // WHEN
        Optional<UtilisateurDTO> result = utilisateurController.getUtilisateurById(id);

        // THEN
        assertTrue(result.isEmpty());
        verify(utilisateurUseCases, times(1)).getUtilisateurById(id);
    }

    private Utilisateur createUtilisateur() {
        return Joueur.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
    }
}
