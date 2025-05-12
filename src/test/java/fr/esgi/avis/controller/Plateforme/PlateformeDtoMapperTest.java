package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateformeDtoMapperTest {

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        // GIVEN
        Plateforme plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("Xbox Series X");
        plateforme.setDateDeSortie(LocalDate.of(2020, 11, 10));
        plateforme.setJeux(Collections.emptyList());

        // WHEN
        PlateformeDTO dto = PlateformeDtoMapper.toDto(plateforme);

        // THEN
        assertNotNull(dto);
        assertEquals("Xbox Series X", dto.getNom());
        assertEquals(LocalDate.of(2020, 11, 10), dto.getDateDeSortie());
        assertNotNull(dto.getJeux());
        assertTrue(dto.getJeux().isEmpty());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        // GIVEN
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        JeuDTO jeuDTO = new JeuDTO();
        jeuDTO.setNom("Assassin's Creed");
        jeuDTO.setEditeur(editeur);
        jeuDTO.setDescription("Action adventure");
        jeuDTO.setPrix(69.99f);
        jeuDTO.setPlateformes(Collections.emptyList());

        PlateformeDTO plateformeDTO = new PlateformeDTO();
        plateformeDTO.setNom("Xbox Series X");
        plateformeDTO.setDateDeSortie(LocalDate.of(2020, 11, 10));
        plateformeDTO.setJeux(List.of(jeuDTO));

        // WHEN
        Plateforme plateforme = PlateformeDtoMapper.toDomain(plateformeDTO);

        // THEN
        assertNotNull(plateforme);
        assertEquals("Xbox Series X", plateforme.getNom());
        assertEquals(1, plateforme.getJeux().size());
        assertEquals("Assassin's Creed", plateforme.getJeux().get(0).getNom());
        assertEquals("Ubisoft", plateforme.getJeux().get(0).getEditeur().getNom());
    }

    @Test
    void shouldReturnNullWhenPlateformeIsNull() {
        assertNull(PlateformeDtoMapper.toDto(null));
    }

    @Test
    void shouldReturnNullWhenPlateformeDtoIsNull() {
        assertNull(PlateformeDtoMapper.toDomain(null));
    }
}
