package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.ModerateurDTO;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.usecases.Moderateur.ModerateurUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModerateurControllerTest {

    @Mock
    private ModerateurUseCases moderateurUseCases;

    @InjectMocks
    private ModerateurController moderateurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateModerateurSuccessfully() {
        // GIVEN
        ModerateurDTO moderateurDTO = new ModerateurDTO();
        moderateurDTO.setPseudo("modAdmin");
        moderateurDTO.setMotDePasse("password123");
        moderateurDTO.setEmail("admin@example.com");
        moderateurDTO.setNumeroDeTelephone("+33123456789");

        Moderateur createdModerateur = ModerateurDtoMapper.toDomain(moderateurDTO);

        when(moderateurUseCases.createModerateur(any(Moderateur.class))).thenReturn(createdModerateur);

        // WHEN
        ModerateurDTO result = moderateurController.createModerateur(moderateurDTO);

        // THEN
        assertNotNull(result);
        assertEquals(moderateurDTO.getPseudo(), result.getPseudo());
        verify(moderateurUseCases, times(1)).createModerateur(any(Moderateur.class));
    }

    @Test
    void shouldGetModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        Moderateur moderateur = createModerateur();
        when(moderateurUseCases.getModerateurByPseudo(pseudo)).thenReturn(Optional.of(moderateur));

        // WHEN
        Optional<ModerateurDTO> result = moderateurController.getModerateurByPseudo(pseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(pseudo, result.get().getPseudo());
        verify(moderateurUseCases, times(1)).getModerateurByPseudo(pseudo);
    }

    @Test
    void shouldReturnEmptyWhenModerateurNotFoundByPseudo() {
        // GIVEN
        String pseudo = "unknown";
        when(moderateurUseCases.getModerateurByPseudo(pseudo)).thenReturn(Optional.empty());

        // WHEN
        Optional<ModerateurDTO> result = moderateurController.getModerateurByPseudo(pseudo);

        // THEN
        assertFalse(result.isPresent());
        verify(moderateurUseCases, times(1)).getModerateurByPseudo(pseudo);
    }

    @Test
    void shouldDeleteModerateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        doNothing().when(moderateurUseCases).deleteModerateurById(id);

        // WHEN
        moderateurController.deleteModerateurById(id);

        // THEN
        verify(moderateurUseCases, times(1)).deleteModerateurById(id);
    }

    @Test
    void shouldDeleteModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        doNothing().when(moderateurUseCases).deleteModerateurByPseudo(pseudo);

        // WHEN
        moderateurController.deleteModerateurByPseudo(pseudo);

        // THEN
        verify(moderateurUseCases, times(1)).deleteModerateurByPseudo(pseudo);
    }

    @Test
    void shouldGetAllModerateursSuccessfully() {
        // GIVEN
        List<Moderateur> moderateurs = List.of(createModerateur());
        when(moderateurUseCases.getAllModerateurs()).thenReturn(moderateurs);

        // WHEN
        List<ModerateurDTO> result = moderateurController.getAllModerateurs();

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(moderateurs.size(), result.size());
        verify(moderateurUseCases, times(1)).getAllModerateurs();
    }

    private Moderateur createModerateur() {
        return Moderateur.builder()
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();
    }
}
