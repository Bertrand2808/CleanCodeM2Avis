package fr.esgi.avis.controller.Utilisateur.rest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import fr.esgi.avis.controller.Utilisateur.UtilisateurController;
import fr.esgi.avis.controller.Utilisateur.dto.ConnexionDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;

class UtilisateurRestControllerTest {

    @Mock
    private UtilisateurController utilisateurController;

    @InjectMocks
    private UtilisateurRestController utilisateurRestController;

    private UtilisateurDTO utilisateurDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setPseudo("testUser");
        utilisateurDTO.setEmail("test@example.com");
    }

    @Test
    void shouldCreateUtilisateurSuccessfully() {
        when(utilisateurController.createUtilisateur(utilisateurDTO)).thenReturn(utilisateurDTO);

        ResponseEntity<?> response = utilisateurRestController.createUtilisateur(utilisateurDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(utilisateurDTO, response.getBody());
        verify(utilisateurController, times(1)).createUtilisateur(utilisateurDTO);
    }

    @Test
    void shouldReturnBadRequestWhenCreateUtilisateurThrowsException() {
        when(utilisateurController.createUtilisateur(utilisateurDTO))
                .thenThrow(new IllegalArgumentException("Email déjà utilisé"));

        ResponseEntity<?> response = utilisateurRestController.createUtilisateur(utilisateurDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Email déjà utilisé", response.getBody());
    }

    @Test
    void shouldGetUtilisateurByPseudoWhenFound() {
        when(utilisateurController.getUtilisateurByPseudo("testUser")).thenReturn(Optional.of(utilisateurDTO));

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurByPseudo("testUser");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(utilisateurDTO, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenUtilisateurByPseudoNotExists() {
        when(utilisateurController.getUtilisateurByPseudo("unknown")).thenReturn(Optional.empty());

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurByPseudo("unknown");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldGetUtilisateurByEmailWhenFound() {
        when(utilisateurController.getUtilisateurByEmail("test@example.com")).thenReturn(Optional.of(utilisateurDTO));

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurByEmail("test@example.com");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(utilisateurDTO, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenUtilisateurByEmailNotExists() {
        when(utilisateurController.getUtilisateurByEmail("unknown@example.com")).thenReturn(Optional.empty());

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurByEmail("unknown@example.com");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldGetUtilisateurByIdWhenFound() {
        when(utilisateurController.getUtilisateurById(1L)).thenReturn(Optional.of(utilisateurDTO));

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(utilisateurDTO, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenUtilisateurByIdNotExists() {
        when(utilisateurController.getUtilisateurById(99L)).thenReturn(Optional.empty());

        ResponseEntity<UtilisateurDTO> response = utilisateurRestController.getUtilisateurById(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldConnexionSuccessfully() {
        ConnexionDTO connexionDTO = new ConnexionDTO();
        connexionDTO.setPseudo("test@example.com");
        connexionDTO.setMotDePasse("password");

        when(utilisateurController.connexion(connexionDTO)).thenReturn(utilisateurDTO);

        ResponseEntity<?> response = utilisateurRestController.connexion(connexionDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(utilisateurDTO, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenConnexionFails() {
        ConnexionDTO connexionDTO = new ConnexionDTO();
        connexionDTO.setPseudo("fail@example.com");
        connexionDTO.setMotDePasse("badpass");

        when(utilisateurController.connexion(connexionDTO))
                .thenThrow(new IllegalArgumentException("Identifiants invalides"));

        ResponseEntity<?> response = utilisateurRestController.connexion(connexionDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Identifiants invalides", response.getBody());
    }

    @Test
    void shouldDeconnexionSuccessfully() {
        doNothing().when(utilisateurController).deconnexion();

        ResponseEntity<Void> response = utilisateurRestController.deconnexion();

        assertEquals(200, response.getStatusCodeValue());
        verify(utilisateurController, times(1)).deconnexion();
    }
}
