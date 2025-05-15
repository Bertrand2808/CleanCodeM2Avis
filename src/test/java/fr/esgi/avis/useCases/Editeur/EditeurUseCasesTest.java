package fr.esgi.avis.useCases.Editeur;

import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
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

class EditeurUseCasesTest {

    @Mock
    private EditeurDataSourcePort editeurDataSourcePort;

    @InjectMocks
    private EditeurUseCases editeurUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateEditeurSuccessfully() {
        Editeur editeur = createEditeur();
        when(editeurDataSourcePort.save(any(Editeur.class))).thenReturn(editeur);

        Editeur created = editeurUseCases.createEditeur(editeur);

        assertNotNull(created);
        verify(editeurDataSourcePort, times(1)).save(any(Editeur.class));
    }

    @Test
    void shouldGetAllEditeurs() {
        when(editeurDataSourcePort.findAll()).thenReturn(List.of(createEditeur()));

        List<Editeur> all = editeurUseCases.getEditeurs();

        assertFalse(all.isEmpty());
        verify(editeurDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetEditeurByIdIfExists() {
        Editeur editeur = createEditeur();
        when(editeurDataSourcePort.findById(1L)).thenReturn(Optional.of(editeur));

        Optional<Editeur> found = editeurUseCases.getEditeurById(1L);

        assertTrue(found.isPresent());
        verify(editeurDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyIfEditeurNotFoundById() {
        when(editeurDataSourcePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Editeur> found = editeurUseCases.getEditeurById(1L);

        assertTrue(found.isEmpty());
        verify(editeurDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldGetEditeurByNomIfExists() {
        Editeur editeur = createEditeur();
        when(editeurDataSourcePort.findByNom("Test")).thenReturn(Optional.of(editeur));

        Optional<Editeur> found = editeurUseCases.getEditeurByNom("Test");

        assertTrue(found.isPresent());
        verify(editeurDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldReturnEmptyIfEditeurNotFoundByNom() {
        when(editeurDataSourcePort.findByNom("Test")).thenReturn(Optional.empty());

        Optional<Editeur> found = editeurUseCases.getEditeurByNom("Test");

        assertTrue(found.isEmpty());
        verify(editeurDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldGetEditeursByNomContaining() {
        when(editeurDataSourcePort.findByNomContaining("Te")).thenReturn(List.of(createEditeur()));

        List<Editeur> list = editeurUseCases.getEditeursByNomContaining("Te");

        assertFalse(list.isEmpty());
        verify(editeurDataSourcePort, times(1)).findByNomContaining("Te");
    }

    @Test
    void shouldDeleteEditeurById() {
        doNothing().when(editeurDataSourcePort).deleteById(1L);

        editeurUseCases.deleteEditeurById(1L);

        verify(editeurDataSourcePort, times(1)).deleteById(1L);
    }

    private Editeur createEditeur() {
        return Editeur.builder()
                .id(1L)
                .nom("Test")
                .build();
    }
}
