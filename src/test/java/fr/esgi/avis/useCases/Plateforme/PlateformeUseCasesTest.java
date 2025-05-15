package fr.esgi.avis.useCases.Plateforme;

import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
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

class PlateformeUseCasesTest {

    @Mock
    private PlateformeDataSourcePort plateformeDataSourcePort;

    @InjectMocks
    private PlateformeUseCases plateformeUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePlateformeSuccessfully() {
        Plateforme plateforme = createPlateforme();
        when(plateformeDataSourcePort.save(any(Plateforme.class))).thenReturn(plateforme);

        Plateforme created = plateformeUseCases.createPlateforme(plateforme);

        assertNotNull(created);
        verify(plateformeDataSourcePort, times(1)).save(any(Plateforme.class));
    }

    @Test
    void shouldGetAllPlateformes() {
        when(plateformeDataSourcePort.findAll()).thenReturn(List.of(createPlateforme()));

        List<Plateforme> all = plateformeUseCases.getlateformes();

        assertFalse(all.isEmpty());
        verify(plateformeDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetPlateformeByIdIfExists() {
        Plateforme plateforme = createPlateforme();
        when(plateformeDataSourcePort.findById(1L)).thenReturn(Optional.of(plateforme));

        Optional<Plateforme> found = plateformeUseCases.getPlateformeById(1L);

        assertTrue(found.isPresent());
        verify(plateformeDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyIfPlateformeNotFoundById() {
        when(plateformeDataSourcePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Plateforme> found = plateformeUseCases.getPlateformeById(1L);

        assertTrue(found.isEmpty());
        verify(plateformeDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldGetPlateformeByNomIfExists() {
        Plateforme plateforme = createPlateforme();
        when(plateformeDataSourcePort.findByNom("Test")).thenReturn(Optional.of(plateforme));

        Optional<Plateforme> found = plateformeUseCases.getPlateformeByNom("Test");

        assertTrue(found.isPresent());
        verify(plateformeDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldReturnEmptyIfPlateformeNotFoundByNom() {
        when(plateformeDataSourcePort.findByNom("Test")).thenReturn(Optional.empty());

        Optional<Plateforme> found = plateformeUseCases.getPlateformeByNom("Test");

        assertTrue(found.isEmpty());
        verify(plateformeDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldGetPlateformesByNomContaining() {
        when(plateformeDataSourcePort.findByNomContaining("Te")).thenReturn(List.of(createPlateforme()));

        List<Plateforme> list = plateformeUseCases.getPlateformesByNomContaining("Te");

        assertFalse(list.isEmpty());
        verify(plateformeDataSourcePort, times(1)).findByNomContaining("Te");
    }

    @Test
    void shouldDeletePlateformeById() {
        doNothing().when(plateformeDataSourcePort).deleteById(1L);

        plateformeUseCases.deletePlateforme(1L);

        verify(plateformeDataSourcePort, times(1)).deleteById(1L);
    }

    private Plateforme createPlateforme() {
        return Plateforme.builder()
                .id(1L)
                .nom("Test")
                .build();
    }
}
