package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlateformeControllerTest {

    @Mock
    private PlateformeUseCases plateformeUseCases;

    @InjectMocks
    private PlateformeController plateformeController;

    private Plateforme plateforme;
    private PlateformeDTO plateformeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("PlayStation 5");
        plateforme.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateforme.setJeux(Collections.emptyList());

        plateformeDTO = new PlateformeDTO();
        plateformeDTO.setNom("PlayStation 5");
        plateformeDTO.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateformeDTO.setJeux(Collections.emptyList());
    }

    @Test
    void shouldCreatePlateformeSuccessfully() {
        when(plateformeUseCases.createPlateforme(any())).thenReturn(plateforme);

        PlateformeDTO result = plateformeController.createPlateforme(plateformeDTO);

        assertNotNull(result);
        assertEquals("PlayStation 5", result.getNom());
        verify(plateformeUseCases, times(1)).createPlateforme(any());
    }

    @Test
    void shouldGetAllPlateformes() {
        when(plateformeUseCases.getlateformes()).thenReturn(Collections.singletonList(plateforme));

        var result = plateformeController.getPlateformes();

        assertEquals(1, result.size());
        assertEquals("PlayStation 5", result.get(0).getNom());
        verify(plateformeUseCases, times(1)).getlateformes();
    }

    @Test
    void shouldGetPlateformeById() {
        when(plateformeUseCases.getPlateformeById(1L)).thenReturn(Optional.of(plateforme));

        var result = plateformeController.getPlateformeById(1L);

        assertTrue(result.isPresent());
        assertEquals("PlayStation 5", result.get().getNom());
        verify(plateformeUseCases, times(1)).getPlateformeById(1L);
    }

    @Test
    void shouldDeletePlateformeById() {
        doNothing().when(plateformeUseCases).deletePlateforme(1L);

        plateformeController.deletePlateformeById(1L);

        verify(plateformeUseCases, times(1)).deletePlateforme(1L);
    }
}
