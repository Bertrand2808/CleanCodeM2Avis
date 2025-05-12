package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AvisDtoMapperTest {

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        // GIVEN
        Avis avis = new Avis();
        avis.setId(1L);
        avis.setDescription("Super jeu");
        avis.setNote(5.0f);
        avis.setJoueurId(1L);
        avis.setDateDEnvoi(LocalDateTime.now());
        avis.setJeuId(1L);
        avis.setModerateurId(1L);

        // WHEN
        AvisDTO avisDTO = AvisDtoMapper.toDto(avis);

        // THEN
        assertNotNull(avisDTO);
        assertEquals(avis.getId(), avisDTO.getId());
        assertEquals(avis.getDescription(), avisDTO.getDescription());
        assertEquals(avis.getNote(), avisDTO.getNote());
        assertEquals(avis.getJoueurId(), avisDTO.getJoueurId());
        assertEquals(avis.getDateDEnvoi(), avisDTO.getDateDEnvoi());
        assertEquals(avis.getJeuId(), avisDTO.getJeuId());
        assertEquals(avis.getModerateurId(), avisDTO.getModerateurId());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        // GIVEN
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(2L);
        avisDTO.setDescription("Mauvais jeu");
        avisDTO.setNote(1.0f);
        avisDTO.setJoueurId(2L);
        avisDTO.setJeuId(2L);
        avisDTO.setModerateurId(3L);
        avisDTO.setDateDEnvoi(LocalDateTime.now());

        // WHEN
        Avis avis = AvisDtoMapper.toDomain(avisDTO);

        // THEN
        assertNotNull(avis);
        assertEquals(avisDTO.getId(), avis.getId());
        assertEquals(avisDTO.getDescription(), avis.getDescription());
        assertEquals(avisDTO.getNote(), avis.getNote());
        assertEquals(avisDTO.getJoueurId(), avis.getJoueurId());
        assertEquals(avisDTO.getDateDEnvoi(), avis.getDateDEnvoi());
        assertEquals(avisDTO.getJeuId(), avis.getJeuId());
        assertEquals(avisDTO.getModerateurId(), avis.getModerateurId());
    }

    @Test
    void shouldReturnNullWhenAvisIsNull() {
        // WHEN
        AvisDTO avisDTO = AvisDtoMapper.toDto(null);

        // THEN
        assertNull(avisDTO);
    }

    @Test
    void shouldReturnNullWhenAvisDtoIsNull() {
        // WHEN
        Avis avis = AvisDtoMapper.toDomain(null);

        // THEN
        assertNull(avis);
    }

}