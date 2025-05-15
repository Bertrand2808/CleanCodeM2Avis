package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.useCases.Joueur.JoueurUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JoueurControllerTest {

    @Mock
    private JoueurUseCases joueurUseCases;

    @InjectMocks
    private JoueurController joueurController;

    private JoueurDTO joueurDTO;
    private Joueur joueur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        joueurDTO = new JoueurDTO();
        joueurDTO.setPseudo("PlayerOne");
        joueurDTO.setEmail("player@esgi.fr");
        joueurDTO.setMotDePasse("secure123");
        joueurDTO.setDateDeNaissance(LocalDate.of(2000, 1, 1));

        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setNom("Warrior");
        joueurDTO.setAvatar(avatarDTO);

        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Great game");
        avisDTO.setJoueurId(1L);
        avisDTO.setNote(4.5f);
        avisDTO.setDateDEnvoi(LocalDate.now().atStartOfDay());
        avisDTO.setJeuId(1L);
        avisDTO.setModerateurId(1L);

        joueurDTO.setAvis(Collections.singletonList(avisDTO));

        joueur = JoueurDtoMapper.toDomain(joueurDTO);
    }

    @Test
    void shouldCreateJoueurSuccessfully() {
        when(joueurUseCases.createJoueur(any())).thenReturn(joueur);

        JoueurDTO result = joueurController.createJoueur(joueurDTO);

        assertNotNull(result);
        assertEquals(joueurDTO.getPseudo(), result.getPseudo());
        verify(joueurUseCases, times(1)).createJoueur(any());
    }

    @Test
    void shouldGetAllJoueurs() {
        when(joueurUseCases.getAllJoueurs()).thenReturn(Collections.singletonList(joueur));

        var result = joueurController.getAllJoueurs();

        assertEquals(1, result.size());
        verify(joueurUseCases, times(1)).getAllJoueurs();
    }

    @Test
    void shouldGetJoueurByPseudo() {
        when(joueurUseCases.getJoueurByPseudo("PlayerOne")).thenReturn(Optional.of(joueur));

        var result = joueurController.getJoueurByPseudo("PlayerOne");

        assertTrue(result.isPresent());
        assertEquals("PlayerOne", result.get().getPseudo());
    }

    @Test
    void shouldGetJoueurByBirthdate() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        when(joueurUseCases.getJoueurByBirthdate(date)).thenReturn(Optional.of(joueur));

        var result = joueurController.getJoueurByBirthdate(date);

        assertTrue(result.isPresent());
        assertEquals(date, result.get().getDateDeNaissance());
    }
}
