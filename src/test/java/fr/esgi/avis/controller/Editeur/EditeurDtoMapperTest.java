package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.EditeurDTO;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditeurDtoMapperTest {

    @Test
    void shouldMapDomainToDtoCorrectly() {
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");
        editeur.setJeux(new ArrayList<>());

        EditeurDTO dto = EditeurDtoMapper.toDto(editeur);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Ubisoft", dto.getNom());
        assertNotNull(dto.getJeux());
    }

    @Test
    void shouldMapDtoToDomainCorrectly() {
        EditeurDTO dto = new EditeurDTO();
        dto.setId(1L);
        dto.setNom("Ubisoft");
        dto.setJeux(new ArrayList<>());

        Editeur editeur = EditeurDtoMapper.toDomain(dto);

        assertNotNull(editeur);
        assertEquals(1L, editeur.getId());
        assertEquals("Ubisoft", editeur.getNom());
        assertNotNull(editeur.getJeux());
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        assertNull(EditeurDtoMapper.toDto(null));
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {
        assertNull(EditeurDtoMapper.toDomain(null));
    }
}
