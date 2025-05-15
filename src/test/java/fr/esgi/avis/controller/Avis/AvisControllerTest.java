package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.List;
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
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Super jeu");
        avisDTO.setJoueurId(1L);
        avisDTO.setJeuId(2L);
        avisDTO.setModerateurId(3L);
        avisDTO.setNote(5.0f);
        avisDTO.setDateDEnvoi(LocalDateTime.now());

        Avis avis = AvisDtoMapper.toDomain(avisDTO);

        when(avisUseCases.getAvisById(avisDTO.getId())).thenReturn(Optional.of(avis));
        // WHEN
        Optional<AvisDTO> result = avisController.getAvisById(avisDTO.getId());

        // THEN
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Super jeu", result.get().getDescription());
        verify(avisUseCases, times(1)).getAvisById(1L);
    }

    @Test
    void shouldReturnEmptyWhenGetAvisByIdNotExists() {
        // GIVEN
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Super jeu");
        avisDTO.setJoueurId(1L);
        avisDTO.setJeuId(2L);
        avisDTO.setModerateurId(3L);
        avisDTO.setNote(5.0f);
        avisDTO.setDateDEnvoi(LocalDateTime.now());

        when(avisUseCases.getAvisById(avisDTO.getId())).thenReturn(Optional.empty());

        // WHEN
        Optional<AvisDTO> result = avisController.getAvisById(avisDTO.getId());

        // THEN
        assertTrue(result.isEmpty());
        verify(avisUseCases, times(1)).getAvisById(1L);
    }

    @Test
    void shouldDeleteAvisSuccessfully() {
        // GIVEN
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Super jeu");
        avisDTO.setJoueurId(1L);
        avisDTO.setJeuId(2L);
        avisDTO.setModerateurId(3L);
        avisDTO.setNote(5.0f);
        avisDTO.setDateDEnvoi(LocalDateTime.now());

        // WHEN
        avisController.deleteAvis(avisDTO.getId());

        // THEN
        verify(avisUseCases, times(1)).deleteAvis(avisDTO.getId());
    }

    @Test
    void shouldGetAvisByJeuIdSuccessfully() {
        // GIVEN
        Long jeuId = 42L;
        AvisDTO avisDTO1 = new AvisDTO();
        avisDTO1.setId(1L);
        avisDTO1.setDescription("Avis 1");
        avisDTO1.setJeuId(jeuId);
        avisDTO1.setJoueurId(1L);
        avisDTO1.setNote(4.0f);
        avisDTO1.setDateDEnvoi(LocalDateTime.now());
        avisDTO1.setModerateurId(3L);

        AvisDTO avisDTO2 = new AvisDTO();
        avisDTO2.setId(2L);
        avisDTO2.setDescription("Avis 2");
        avisDTO2.setJeuId(jeuId);
        avisDTO2.setJoueurId(2L);
        avisDTO2.setNote(3.5f);
        avisDTO2.setDateDEnvoi(LocalDateTime.now());
        avisDTO2.setModerateurId(4L);

        Avis avis1 = AvisDtoMapper.toDomain(avisDTO1); 
        Avis avis2 = AvisDtoMapper.toDomain(avisDTO2);

        when(avisUseCases.getAvisByJeuId(42L)).thenReturn(List.of(avis1, avis2));

        // WHEN
        var result = avisController.getAvisByJeuId(42L);

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Avis 1", result.get(0).getDescription());
        assertEquals("Avis 2", result.get(1).getDescription());
        verify(avisUseCases, times(1)).getAvisByJeuId(42L);
    }

}
