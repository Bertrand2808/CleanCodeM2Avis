package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.domain.Classification.model.Classification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassificationJpaAdapterTest {

    @Mock
    private ClassificationJpaRepository classificationJpaRepository;

    @InjectMocks
    private ClassificationJpaAdapter classificationJpaAdapter;

    private Classification classification;
    private ClassificationEntity entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        classification = Classification.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(Collections.emptyList())
                .build();

        entity = ClassificationEntity.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(Collections.emptyList())
                .build();
    }

    @Test
    void shouldSaveClassificationSuccessfully() {
        when(classificationJpaRepository.save(any())).thenReturn(entity);

        Classification result = classificationJpaAdapter.save(classification);

        assertNotNull(result);
        assertEquals("PEGI 18", result.getNom());
        verify(classificationJpaRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAllClassifications() {
        when(classificationJpaRepository.findAll()).thenReturn(List.of(entity));

        List<Classification> result = classificationJpaAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals("PEGI 18", result.get(0).getNom());
    }

    @Test
    void shouldFindByIdSuccessfully() {
        when(classificationJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Classification> result = classificationJpaAdapter.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("PEGI 18", result.get().getNom());
    }

    @Test
    void shouldFindByNomSuccessfully() {
        when(classificationJpaRepository.findByNom("PEGI 18")).thenReturn(Optional.of(entity));

        Optional<Classification> result = classificationJpaAdapter.findByNom("PEGI 18");

        assertTrue(result.isPresent());
        assertEquals("PEGI 18", result.get().getNom());
    }

    @Test
    void shouldFindByNomContainingSuccessfully() {
        when(classificationJpaRepository.findByNomContaining("PEGI")).thenReturn(List.of(entity));

        List<Classification> result = classificationJpaAdapter.findByNomContaining("PEGI");

        assertEquals(1, result.size());
        assertEquals("PEGI 18", result.get(0).getNom());
    }

    @Test
    void shouldDeleteByIdSuccessfully() {
        doNothing().when(classificationJpaRepository).deleteById(1L);

        classificationJpaAdapter.deleteById(1L);

        verify(classificationJpaRepository, times(1)).deleteById(1L);
    }
}