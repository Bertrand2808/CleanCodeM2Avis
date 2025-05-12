package fr.esgi.avis.usecases.Joueur;

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

    @InjectMocks
    private fr.esgi.avis.usecases.Joueur.JoueurUseCases joueurUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateJoueurSuccessfully() {
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.save(any(Joueur.class))).thenReturn(joueur);

        Joueur created = joueurUseCases.createJoueur(joueur);

        assertNotNull(created);
        verify(joueurDataSourcePort, times(1)).save(any(Joueur.class));
    }

    @Test
    void shouldGetAllJoueurs() {
        when(joueurDataSourcePort.findAll()).thenReturn(List.of(createJoueur()));

        List<Joueur> all = joueurUseCases.getAllJoueurs();

        assertFalse(all.isEmpty());
        verify(joueurDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetJoueurByPseudoIfExists() {
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findByPseudo("player123")).thenReturn(Optional.of(joueur));

        Optional<Joueur> found = joueurUseCases.getJoueurByPseudo("player123");

        assertTrue(found.isPresent());
        verify(joueurDataSourcePort, times(1)).findByPseudo("player123");
    }

    @Test
    void shouldReturnEmptyIfJoueurNotFoundByPseudo() {
        when(joueurDataSourcePort.findByPseudo("unknown")).thenReturn(Optional.empty());

        Optional<Joueur> found = joueurUseCases.getJoueurByPseudo("unknown");

        assertTrue(found.isEmpty());
        verify(joueurDataSourcePort, times(1)).findByPseudo("unknown");
    }

    @Test
    void shouldGetJoueurByBirthdateIfExists() {
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        Joueur joueur = createJoueur();
        when(joueurDataSourcePort.findByDateDeNaissance(birthdate)).thenReturn(Optional.of(joueur));

        Optional<Joueur> found = joueurUseCases.getJoueurByBirthdate(birthdate);

        assertTrue(found.isPresent());
        verify(joueurDataSourcePort, times(1)).findByDateDeNaissance(birthdate);
    }

    @Test
    void shouldReturnEmptyIfJoueurNotFoundByBirthdate() {
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        when(joueurDataSourcePort.findByDateDeNaissance(birthdate)).thenReturn(Optional.empty());

        Optional<Joueur> found = joueurUseCases.getJoueurByBirthdate(birthdate);

        assertTrue(found.isEmpty());
        verify(joueurDataSourcePort, times(1)).findByDateDeNaissance(birthdate);
    }

    private Joueur createJoueur() {
        return Joueur.builder()
                .id(1L)
                .pseudo("player123")
                .motDePasse("password")
                .email("player@example.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
    }
}
