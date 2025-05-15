package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.EditeurDTO;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EditeurControllerTest {

    @Mock
    private EditeurUseCases editeurUseCases;

    @InjectMocks
    private EditeurController editeurController;

    private Editeur fakeEditeur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fakeEditeur = new Editeur();
        fakeEditeur.setId(1L);
        fakeEditeur.setNom("Ubisoft");
        fakeEditeur.setJeux(new ArrayList<>());
    }

    @Test
    void shouldCreateEditeurSuccessfully() {
        EditeurDTO dto = new EditeurDTO();
        dto.setNom("Ubisoft");
        dto.setJeux(new ArrayList<>());

        when(editeurUseCases.createEditeur(any(Editeur.class))).thenReturn(fakeEditeur);

        EditeurDTO result = editeurController.createEditeur(dto);

        assertNotNull(result);
        assertEquals("Ubisoft", result.getNom());
        verify(editeurUseCases, times(1)).createEditeur(any());
    }

    @Test
    void shouldGetAllEditeurs() {
        when(editeurUseCases.getEditeurs()).thenReturn(List.of(fakeEditeur));

        List<EditeurDTO> result = editeurController.getEditeurs();

        assertEquals(1, result.size());
        verify(editeurUseCases, times(1)).getEditeurs();
    }

    @Test
    void shouldGetEditeurById() {
        when(editeurUseCases.getEditeurById(1L)).thenReturn(Optional.of(fakeEditeur));

        Optional<EditeurDTO> result = editeurController.getEditeurById(1L);

        assertTrue(result.isPresent());
        assertEquals("Ubisoft", result.get().getNom());
    }

    @Test
    void shouldReturnEmptyWhenEditeurNotFoundById() {
        when(editeurUseCases.getEditeurById(99L)).thenReturn(Optional.empty());

        Optional<EditeurDTO> result = editeurController.getEditeurById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetEditeurByNom() {
        when(editeurUseCases.getEditeurByNom("Ubisoft")).thenReturn(Optional.of(fakeEditeur));

        Optional<EditeurDTO> result = editeurController.getEditeurByNom("Ubisoft");

        assertTrue(result.isPresent());
        assertEquals("Ubisoft", result.get().getNom());
    }

    @Test
    void shouldReturnEmptyWhenEditeurNotFoundByNom() {
        when(editeurUseCases.getEditeurByNom("Unknown")).thenReturn(Optional.empty());

        Optional<EditeurDTO> result = editeurController.getEditeurByNom("Unknown");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetEditeursByNomContaining() {
        when(editeurUseCases.getEditeursByNomContaining("Ubi"))
                .thenReturn(List.of(fakeEditeur));

        List<EditeurDTO> result = editeurController.getEditeursByNomContaining("Ubi");

        assertEquals(1, result.size());
        verify(editeurUseCases).getEditeursByNomContaining("Ubi");
    }

    @Test
    void shouldDeleteEditeurSuccessfully() {
        doNothing().when(editeurUseCases).deleteEditeurById(1L);

        editeurController.deleteEditeurById(1L);

        verify(editeurUseCases).deleteEditeurById(1L);
    }
}