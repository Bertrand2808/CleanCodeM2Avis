package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeuDtoMapperTest {

    @Test
    void shouldMapDomainToDtoSuccessfully() {
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setNom("Action");

        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 18");
        classification.setCouleurRGB("#FF0000");

        Plateforme plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("PlayStation");
        plateforme.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateforme.setJeux(new ArrayList<>());

        Jeu jeu = new Jeu();
        jeu.setId(1L);
        jeu.setNom("Far Cry");
        jeu.setEditeur(editeur);
        jeu.setGenre(genre);
        jeu.setClassification(classification);
        jeu.setPlateformes(List.of(plateforme));
        jeu.setDescription("FPS open world");
        jeu.setDateDeSortie(LocalDate.of(2021, 10, 7));
        jeu.setImage("farcry.jpg");
        jeu.setPrix(59.99f);

        JeuDTO dto = JeuDtoMapper.toDto(jeu);

        assertNotNull(dto);
        assertEquals(jeu.getNom(), dto.getNom());
        assertEquals(jeu.getEditeur().getNom(), dto.getEditeur().getNom());
        assertEquals(1, dto.getPlateformes().size());
    }

    @Test
    void shouldMapDtoToDomainSuccessfully() {
        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setNom("Action");

        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 18");
        classification.setCouleurRGB("#FF0000");

        PlateformeDTO plateformeDTO = new PlateformeDTO();
        plateformeDTO.setNom("PlayStation");
        plateformeDTO.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateformeDTO.setJeux(new ArrayList<>());

        JeuDTO dto = new JeuDTO();
        dto.setId(1L);
        dto.setNom("Far Cry");
        dto.setEditeur(editeur);
        dto.setGenre(genre);
        dto.setClassification(classification);
        dto.setPlateformes(List.of(plateformeDTO));
        dto.setDescription("FPS open world");
        dto.setDateDeSortie(LocalDate.of(2021, 10, 7));
        dto.setImage("farcry.jpg");
        dto.setPrix(59.99f);

        Jeu jeu = JeuDtoMapper.toDomain(dto);

        assertNotNull(jeu);
        assertEquals(dto.getNom(), jeu.getNom());
        assertEquals(dto.getEditeur().getNom(), jeu.getEditeur().getNom());
        assertEquals(1, jeu.getPlateformes().size());
    }

    @Test
    void shouldReturnNullWhenMappingNullDtoToDomain() {
        assertNull(JeuDtoMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenMappingNullDomainToDto() {
        assertNull(JeuDtoMapper.toDto(null));
    }
}
