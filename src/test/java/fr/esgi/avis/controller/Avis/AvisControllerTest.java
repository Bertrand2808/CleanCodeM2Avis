package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.usecases.Avis.AvisUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvisControllerTest {
    
    @Mock
    private AvisUseCases avisUseCases;
    
    @InjectMocks
    private AvisController avisController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAvisSuccessfully() {
        // GIVEN
        Long joueurId = 1L;
        Long jeuId = 2L;
        Long moderateurId = 3L;

        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Super jeu");
        avisDTO.setJoueurId(joueurId);
        avisDTO.setJeuId(jeuId);
        avisDTO.setModerateurId(moderateurId);
        avisDTO.setNote(5.0f);
        avisDTO.setDateDEnvoi(LocalDateTime.now());

        Avis createdAvis = AvisDtoMapper.toDomain(avisDTO);

        when(avisUseCases.createAvis(any(Avis.class))).thenReturn(createdAvis);

        // WHEN
        AvisDTO result = avisController.createAvis(avisDTO);

        // THEN
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Super jeu", result.getDescription());
        verify(avisUseCases, times(1)).createAvis(any(Avis.class));
    }

    @Test
    void shouldGetAvisByIdWhenExists() {
        // GIVEN
        Long avisId = 1L;
        Avis avis = new Avis();
        avis.setId(avisId);
        avis.setDescription("Super jeu");
        avis.setJoueurId(1L);
        avis.setNote(5.0f);
        avis.setDateDEnvoi(LocalDateTime.now());

        when(avisUseCases.getAvisById(avisId)).thenReturn(java.util.Optional.of(avis));

        // WHEN
        Optional<AvisDTO> result = avisController.getAvisById(avisId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Super jeu", result.get().getDescription());
        verify(avisUseCases, times(1)).getAvisById(avisId);
    }

    @Test
    void shouldReturnEmptyWhenGetAvisByIdNotExists() {
        // GIVEN
        Long avisId = 1L;
        when(avisUseCases.getAvisById(avisId)).thenReturn(Optional.empty());

        // WHEN
        Optional<AvisDTO> result = avisController.getAvisById(avisId);

        // THEN
        assertTrue(result.isEmpty());
        verify(avisUseCases, times(1)).getAvisById(avisId);
    }

    @Test
    void shouldDeleteAvisSuccessfully() {
        // GIVEN
        Long avisId = 1L;

        // WHEN
        avisController.deleteAvis(avisId);

        // THEN
        verify(avisUseCases, times(1)).deleteAvis(avisId);
    }
}