package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
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

class ClassificationDtoMapperTest {

    private List<Plateforme> createFakePlateformes() {
        Plateforme plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("Switch");
        plateforme.setDateDeSortie(LocalDate.of(2017, 3, 3));
        plateforme.setJeux(new ArrayList<>()); // prevent recursive issues
        return List.of(plateforme);
    }

    private List<PlateformeDTO> createFakePlateformeDTOs() {
        PlateformeDTO dto = new PlateformeDTO();
        dto.setNom("Switch");
        dto.setDateDeSortie(LocalDate.of(2017, 3, 3));
        dto.setJeux(new ArrayList<>());
        return List.of(dto);
    }

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        // GIVEN
        Jeu jeu = new Jeu();
        jeu.setId(1L);
        jeu.setNom("Zelda");

        Editeur editeur = new Editeur();
        editeur.setId(10L);
        editeur.setNom("Nintendo");

        Genre genre = new Genre();
        genre.setId(20L);
        genre.setNom("Action");

        Classification classificationJeu = new Classification();
        classificationJeu.setId(30L);
        classificationJeu.setNom("PEGI 12");
        classificationJeu.setCouleurRGB("#CCCCCC");

        jeu.setEditeur(editeur);
        jeu.setGenre(genre);
        jeu.setClassification(classificationJeu);
        jeu.setPlateformes(createFakePlateformes());

        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 12");
        classification.setCouleurRGB("#FFFF00");
        classification.setJeux(List.of(jeu));

        // WHEN
        ClassificationDTO dto = ClassificationDtoMapper.toDto(classification);

        // THEN
        assertNotNull(dto);
        assertEquals(classification.getId(), dto.getId());
        assertEquals(classification.getNom(), dto.getNom());
        assertEquals(classification.getCouleurRGB(), dto.getCouleurRGB());
        assertNotNull(dto.getJeux());
        assertEquals(1, dto.getJeux().size());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        // GIVEN
        JeuDTO jeuDTO = new JeuDTO();
        jeuDTO.setId(1L);
        jeuDTO.setNom("Zelda");

        Editeur editeur = new Editeur();
        editeur.setId(10L);
        editeur.setNom("Nintendo");

        Genre genre = new Genre();
        genre.setId(20L);
        genre.setNom("Action");

        Classification classification = new Classification();
        classification.setId(30L);
        classification.setNom("PEGI 12");
        classification.setCouleurRGB("#CCCCCC");

        jeuDTO.setEditeur(editeur);
        jeuDTO.setGenre(genre);
        jeuDTO.setClassification(classification);
        jeuDTO.setPlateformes(createFakePlateformeDTOs());

        ClassificationDTO dto = new ClassificationDTO();
        dto.setId(2L);
        dto.setNom("PEGI 18");
        dto.setCouleurRGB("#FF0000");
        dto.setJeux(List.of(jeuDTO));

        // WHEN
        Classification result = ClassificationDtoMapper.toDomain(dto);

        // THEN
        assertNotNull(result);
        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getNom(), result.getNom());
        assertEquals(dto.getCouleurRGB(), result.getCouleurRGB());
        assertNotNull(result.getJeux());
        assertEquals(1, result.getJeux().size());
    }

    @Test
    void shouldReturnNullWhenClassificationIsNull() {
        assertNull(ClassificationDtoMapper.toDto(null));
    }

    @Test
    void shouldReturnNullWhenClassificationDtoIsNull() {
        assertNull(ClassificationDtoMapper.toDomain(null));
    }
}
