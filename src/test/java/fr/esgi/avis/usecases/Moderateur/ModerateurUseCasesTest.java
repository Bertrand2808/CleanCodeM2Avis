package fr.esgi.avis.usecases.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.model.ModerateurDataSourcePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModerateurUseCasesTest {

    @Mock
    private ModerateurDataSourcePort moderateurDataSourcePort;

    @InjectMocks
    private ModerateurUseCases moderateurUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateModerateurSuccessfully() {
        // GIVEN
        Moderateur moderateur = createModerateur();
        when(moderateurDataSourcePort.save(any(Moderateur.class))).thenReturn(moderateur);

        // WHEN
        Moderateur result = moderateurUseCases.createModerateur(moderateur);

        // THEN
        assertNotNull(result);
        assertEquals(moderateur.getPseudo(), result.getPseudo());
        verify(moderateurDataSourcePort, times(1)).save(any(Moderateur.class));
    }

    @Test
    void shouldGetModerateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        Moderateur moderateur = createModerateur();
        when(moderateurDataSourcePort.findById(id)).thenReturn(Optional.of(moderateur));

        // WHEN
        Optional<Moderateur> result = moderateurUseCases.getModerateurById(id);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(moderateur.getPseudo(), result.get().getPseudo());
        verify(moderateurDataSourcePort, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmptyWhenModerateurNotFoundById() {
        // GIVEN
        Long id = 999L;
        when(moderateurDataSourcePort.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Optional<Moderateur> result = moderateurUseCases.getModerateurById(id);

        // THEN
        assertFalse(result.isPresent());
        verify(moderateurDataSourcePort, times(1)).findById(id);
    }

    @Test
    void shouldGetModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        Moderateur moderateur = createModerateur();
        when(moderateurDataSourcePort.findByPseudo(pseudo)).thenReturn(Optional.of(moderateur));

        // WHEN
        Optional<Moderateur> result = moderateurUseCases.getModerateurByPseudo(pseudo);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(pseudo, result.get().getPseudo());
        verify(moderateurDataSourcePort, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldGetModerateurByEmailSuccessfully() {
        // GIVEN
        String email = "admin@example.com";
        Moderateur moderateur = createModerateur();
        when(moderateurDataSourcePort.findByEmail(email)).thenReturn(Optional.of(moderateur));

        // WHEN
        Optional<Moderateur> result = moderateurUseCases.getModerateurByEmail(email);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        verify(moderateurDataSourcePort, times(1)).findByEmail(email);
    }

    @Test
    void shouldGetModerateurByNumeroDeTelephoneSuccessfully() {
        // GIVEN
        String numero = "+33123456789";
        Moderateur moderateur = createModerateur();
        when(moderateurDataSourcePort.findByNumeroDeTelephone(numero)).thenReturn(Optional.of(moderateur));

        // WHEN
        Optional<Moderateur> result = moderateurUseCases.getByNumeroDeTelephone(numero);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(numero, result.get().getNumeroDeTelephone());
        verify(moderateurDataSourcePort, times(1)).findByNumeroDeTelephone(numero);
    }

    @Test
    void shouldDeleteModerateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        doNothing().when(moderateurDataSourcePort).deleteById(id);

        // WHEN
        moderateurUseCases.deleteModerateurById(id);

        // THEN
        verify(moderateurDataSourcePort, times(1)).deleteById(id);
    }

    @Test
    void shouldDeleteModerateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "modAdmin";
        doNothing().when(moderateurDataSourcePort).deleteByPseudo(pseudo);

        // WHEN
        moderateurUseCases.deleteModerateurByPseudo(pseudo);

        // THEN
        verify(moderateurDataSourcePort, times(1)).deleteByPseudo(pseudo);
    }

    @Test
    void shouldGetAllModerateursSuccessfully() {
        // GIVEN
        List<Moderateur> moderateurs = List.of(createModerateur());
        when(moderateurDataSourcePort.findAll()).thenReturn(moderateurs);

        // WHEN
        List<Moderateur> result = moderateurUseCases.getAllModerateurs();

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(moderateurs.size(), result.size());
        verify(moderateurDataSourcePort, times(1)).findAll();
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
