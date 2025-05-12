package fr.esgi.avis.usecases.Jeu;

import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JeuUseCasesTest {

    @Mock
    private JeuDataSourcePort jeuDataSourcePort;

    @InjectMocks
    private JeuUseCases jeuUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateJeuSuccessfully() {
        Jeu jeu = createJeu();
        when(jeuDataSourcePort.save(any(Jeu.class))).thenReturn(jeu);

        Jeu created = jeuUseCases.createJeu(jeu);

        assertNotNull(created);
        verify(jeuDataSourcePort, times(1)).save(any(Jeu.class));
    }

    @Test
    void shouldGetAllJeux() {
        when(jeuDataSourcePort.findAll()).thenReturn(List.of(createJeu()));

        List<Jeu> all = jeuUseCases.getJeux();

        assertFalse(all.isEmpty());
        verify(jeuDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetJeuByIdIfExists() {
        Jeu jeu = createJeu();
        when(jeuDataSourcePort.findById(1L)).thenReturn(Optional.of(jeu));

        Optional<Jeu> found = jeuUseCases.getJeuById(1L);

        assertTrue(found.isPresent());
        verify(jeuDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyIfJeuNotFoundById() {
        when(jeuDataSourcePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Jeu> found = jeuUseCases.getJeuById(1L);

        assertTrue(found.isEmpty());
        verify(jeuDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldGetJeuByNomIfExists() {
        Jeu jeu = createJeu();
        when(jeuDataSourcePort.findByNom("Test")).thenReturn(Optional.of(jeu));

        Optional<Jeu> found = jeuUseCases.getJeuByNom("Test");

        assertTrue(found.isPresent());
        verify(jeuDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldReturnEmptyIfJeuNotFoundByNom() {
        when(jeuDataSourcePort.findByNom("Test")).thenReturn(Optional.empty());

        Optional<Jeu> found = jeuUseCases.getJeuByNom("Test");

        assertTrue(found.isEmpty());
        verify(jeuDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldGetJeuxByNomContaining() {
        when(jeuDataSourcePort.findByNomContaining("Te")).thenReturn(List.of(createJeu()));

        List<Jeu> list = jeuUseCases.getJeuxByNomContaining("Te");

        assertFalse(list.isEmpty());
        verify(jeuDataSourcePort, times(1)).findByNomContaining("Te");
    }

    @Test
    void shouldDeleteJeuById() {
        doNothing().when(jeuDataSourcePort).deleteById(1L);

        jeuUseCases.deleteJeuById(1L);

        verify(jeuDataSourcePort, times(1)).deleteById(1L);
    }

    private Jeu createJeu() {
        return Jeu.builder()
                .id(1L)
                .nom("Test")
                .build();
    }
}
