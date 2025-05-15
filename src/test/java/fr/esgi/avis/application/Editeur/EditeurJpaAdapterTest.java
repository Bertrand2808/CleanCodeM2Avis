package fr.esgi.avis.application.Editeur;

import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EditeurJpaAdapterTest {

    @Mock
    private EditeurJpaRepository editeurJpaRepository;

    @InjectMocks
    private EditeurJpaAdapter editeurJpaAdapter;

    private Random random = new Random();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveEditeurSuccessfully() {
        Editeur editeur = createFakeEditeur();
        EditeurEntity entity = EditeurMapper.toEntity(editeur);

        when(editeurJpaRepository.save(any(EditeurEntity.class))).thenReturn(entity);

        Editeur saved = editeurJpaAdapter.save(editeur);

        assertNotNull(saved);
        assertEquals(editeur.getNom(), saved.getNom());
        verify(editeurJpaRepository, times(1)).save(any(EditeurEntity.class));
    }

    @Test
    void shouldFindByIdSuccessfully() {
        Editeur editeur = createFakeEditeur();
        EditeurEntity entity = EditeurMapper.toEntity(editeur);

        when(editeurJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Editeur> result = editeurJpaAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(editeur.getNom(), result.get().getNom());
    }

    @Test
    void shouldFindByNomSuccessfully() {
        Editeur editeur = createFakeEditeur();
        EditeurEntity entity = EditeurMapper.toEntity(editeur);

        when(editeurJpaRepository.findByNom("Ubisoft")).thenReturn(Optional.of(entity));

        Optional<Editeur> result = editeurJpaAdapter.findByNom("Ubisoft");

        assertTrue(result.isPresent());
        assertEquals("Ubisoft", result.get().getNom());
    }

    @Test
    void shouldFindAllSuccessfully() {
        Editeur editeur = createFakeEditeur();
        List<EditeurEntity> entities = List.of(EditeurMapper.toEntity(editeur));

        when(editeurJpaRepository.findAll()).thenReturn(entities);

        List<Editeur> result = editeurJpaAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals(editeur.getNom(), result.get(0).getNom());
    }

    @Test
    void shouldDeleteByIdSuccessfully() {
        doNothing().when(editeurJpaRepository).deleteById(1L);

        editeurJpaAdapter.deleteById(1L);

        verify(editeurJpaRepository, times(1)).deleteById(1L);
    }

    private Editeur createFakeEditeur() {
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");
        editeur.setJeux(new ArrayList<>());
        return editeur;
    }

    @Test
    void shouldFindEditeurByNomContainingSuccessfully() {
        // GIVEN
        String keyword = "Ubisoft";
        List<Editeur> editeurs = new ArrayList<>();
        Editeur editeur = createFakeEditeur();
        EditeurEntity entity = EditeurMapper.toEntity(editeur);
        when(editeurJpaRepository.findByNomContaining(keyword)).thenReturn(List.of(entity));

        // WHEN
        List<Editeur> result = editeurJpaAdapter.findByNomContaining(keyword);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(editeur.getNom(), result.get(0).getNom());
        verify(editeurJpaRepository, times(1)).findByNomContaining(keyword);
    }

    @Test
    void shouldReturnEmptyListWhenNoEditeurFound() {
        // GIVEN
        String keyword = "Ubisoft";
        when(editeurJpaRepository.findByNomContaining(keyword)).thenReturn(Collections.emptyList());

        // WHEN
        List<Editeur> result = editeurJpaAdapter.findByNomContaining(keyword);

        // THEN
        assertTrue(result.isEmpty());
        verify(editeurJpaRepository, times(1)).findByNomContaining(keyword);
    }
}
