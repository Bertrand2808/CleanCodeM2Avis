package fr.esgi.avis.usecases.Classification;

import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.useCases.Classification.ClassificationUseCases;
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

class ClassificationUseCasesTest {

    @Mock
    private ClassificationDataSourcePort classificationDataSourcePort;

    @InjectMocks
    private ClassificationUseCases classificationUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateClassificationSuccessfully() {
        Classification classification = createClassification();
        when(classificationDataSourcePort.save(any(Classification.class))).thenReturn(classification);

        Classification created = classificationUseCases.createClassification(classification);

        assertNotNull(created);
        verify(classificationDataSourcePort, times(1)).save(any(Classification.class));
    }

    @Test
    void shouldGetAllClassifications() {
        when(classificationDataSourcePort.findAll()).thenReturn(List.of(createClassification()));

        List<Classification> all = classificationUseCases.getClassifications();

        assertFalse(all.isEmpty());
        verify(classificationDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetClassificationByIdIfExists() {
        Classification classification = createClassification();
        when(classificationDataSourcePort.findById(1L)).thenReturn(Optional.of(classification));

        Optional<Classification> found = classificationUseCases.getClassificationById(1L);

        assertTrue(found.isPresent());
        verify(classificationDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyIfClassificationNotFoundById() {
        when(classificationDataSourcePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Classification> found = classificationUseCases.getClassificationById(1L);

        assertTrue(found.isEmpty());
        verify(classificationDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldGetClassificationByNomIfExists() {
        Classification classification = createClassification();
        when(classificationDataSourcePort.findByNom("Test")).thenReturn(Optional.of(classification));

        Optional<Classification> found = classificationUseCases.getClassificationByNom("Test");

        assertTrue(found.isPresent());
        verify(classificationDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldReturnEmptyIfClassificationNotFoundByNom() {
        when(classificationDataSourcePort.findByNom("Test")).thenReturn(Optional.empty());

        Optional<Classification> found = classificationUseCases.getClassificationByNom("Test");

        assertTrue(found.isEmpty());
        verify(classificationDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldGetClassificationsByNomContaining() {
        when(classificationDataSourcePort.findByNomContaining("Te")).thenReturn(List.of(createClassification()));

        List<Classification> list = classificationUseCases.getClassificationsByNomContaining("Te");

        assertFalse(list.isEmpty());
        verify(classificationDataSourcePort, times(1)).findByNomContaining("Te");
    }

    @Test
    void shouldDeleteClassificationById() {
        doNothing().when(classificationDataSourcePort).deleteById(1L);

        classificationUseCases.deleteClassificationById(1L);

        verify(classificationDataSourcePort, times(1)).deleteById(1L);
    }

    private Classification createClassification() {
        return Classification.builder()
                .id(1L)
                .nom("Test")
                .couleurRGB("#FFFFFF")
                .build();
    }
}
