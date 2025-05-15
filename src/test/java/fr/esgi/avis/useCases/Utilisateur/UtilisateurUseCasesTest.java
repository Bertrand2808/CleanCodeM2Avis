package fr.esgi.avis.useCases.Utilisateur;

import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UtilisateurUseCasesTest {

    @Mock
    private UtilisateurDataSourcePort utilisateurDataSourcePort;

    @InjectMocks
    private UtilisateurUseCases utilisateurUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUtilisateurSuccessfully() {
        // GIVEN
        Utilisateur utilisateurToCreate = createUtilisateur();
        when(utilisateurDataSourcePort.save(any(Utilisateur.class))).thenReturn(utilisateurToCreate);

        // WHEN
        Utilisateur createdUtilisateur = utilisateurUseCases.createUtilisateur(utilisateurToCreate);

        // THEN
        assertNotNull(createdUtilisateur);
        assertEquals(utilisateurToCreate.getId(), createdUtilisateur.getId());
        assertEquals(utilisateurToCreate.getPseudo(), createdUtilisateur.getPseudo());
        assertEquals(utilisateurToCreate.getMotDePasse(), createdUtilisateur.getMotDePasse());
        assertEquals(utilisateurToCreate.getEmail(), createdUtilisateur.getEmail());
    }

    @Test
    void shouldGetUtilisateurByPseudoWhenExists() {
        Utilisateur utilisateur = createUtilisateur();
        String utilisateurPseudo = utilisateur.getPseudo();
        when(utilisateurDataSourcePort.findByPseudo(utilisateurPseudo)).thenReturn(Optional.of(utilisateur));

        // When
        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurByPseudo(utilisateurPseudo);

        // THEN
        assertNotNull(foundUtilisateur);
        assertEquals(utilisateurPseudo, foundUtilisateur.map(Utilisateur::getPseudo).orElse(null));
        assertEquals(utilisateur.getEmail(), foundUtilisateur.map(Utilisateur::getEmail).orElse(null));
        assertEquals(utilisateur.getMotDePasse(), foundUtilisateur.map(Utilisateur::getMotDePasse).orElse(null));
        assertEquals(utilisateur.getId(), foundUtilisateur.map(Utilisateur::getId).orElse(null));
    }

    @Test
    void shouldReturnEmptyWhenUtilisateurNotExistsByPseudo() {
        // GIVEN
        String pseudo = "unknownUser";
        when(utilisateurDataSourcePort.findByPseudo(pseudo)).thenReturn(Optional.empty());

        // WHEN
        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurByPseudo(pseudo);

        // THEN
        assertTrue(foundUtilisateur.isEmpty());
        verify(utilisateurDataSourcePort, times(1)).findByPseudo(pseudo);
    }


    @Test
    void shouldGetUtilisateurByIdWhenExists() {
        Utilisateur utilisateur = createUtilisateur();
        Long utilisateurId = utilisateur.getId();
        when(utilisateurDataSourcePort.findById(utilisateurId)).thenReturn(Optional.of(utilisateur));

        // When
        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurById(utilisateurId);

        // THEN
        assertNotNull(foundUtilisateur);
        assertEquals(utilisateurId, foundUtilisateur.map(Utilisateur::getId).orElse(null));
        assertEquals(utilisateur.getEmail(), foundUtilisateur.map(Utilisateur::getEmail).orElse(null));
        assertEquals(utilisateur.getMotDePasse(), foundUtilisateur.map(Utilisateur::getMotDePasse).orElse(null));
        assertEquals(utilisateur.getPseudo(), foundUtilisateur.map(Utilisateur::getPseudo).orElse(null));
    }

    @Test
    void shouldReturnEmptyWhenUtilisateurNotExistsById() {
        Long id = 999L;
        when(utilisateurDataSourcePort.findById(id)).thenReturn(Optional.empty());

        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurById(id);

        assertTrue(foundUtilisateur.isEmpty());
        verify(utilisateurDataSourcePort, times(1)).findById(id);
    }

    @Test
    void shouldGetUtilisateurByEmailWhenExists() {
        Utilisateur utilisateur = createUtilisateur();
        String utilisateurEmail = utilisateur.getEmail();
        when(utilisateurDataSourcePort.findByEmail(utilisateurEmail)).thenReturn(Optional.of(utilisateur));

        // When
        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurByEmail(utilisateurEmail);

        // THEN
        assertNotNull(foundUtilisateur);
        assertEquals(utilisateurEmail, foundUtilisateur.map(Utilisateur::getEmail).orElse(null));
        assertEquals(utilisateur.getId(), foundUtilisateur.map(Utilisateur::getId).orElse(null));
        assertEquals(utilisateur.getMotDePasse(), foundUtilisateur.map(Utilisateur::getMotDePasse).orElse(null));
        assertEquals(utilisateur.getPseudo(), foundUtilisateur.map(Utilisateur::getPseudo).orElse(null));
    }

    @Test
    void shouldReturnEmptyWhenUtilisateurNotExistsByEmail() {
        String email = "unknown@example.com";
        when(utilisateurDataSourcePort.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Utilisateur> foundUtilisateur = utilisateurUseCases.getUtilisateurByEmail(email);

        assertTrue(foundUtilisateur.isEmpty());
        verify(utilisateurDataSourcePort, times(1)).findByEmail(email);
    }

    @Test
    void shouldDeleteUtilisateurSuccessfully() {
        // GIVEN
        Long utilisateurToDeleteId = 1L;

        // WHEN
        utilisateurUseCases.deleteUtilisateur(utilisateurToDeleteId);

        // THEN
        verify(utilisateurDataSourcePort, times(1)).deleteById(utilisateurToDeleteId);
        assertDoesNotThrow(() -> utilisateurUseCases.deleteUtilisateur(utilisateurToDeleteId));
    }


    private Utilisateur createUtilisateur() {
        return Joueur.builder()
                .id(1L)
                .pseudo("test")
                .motDePasse("password")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
    }

}