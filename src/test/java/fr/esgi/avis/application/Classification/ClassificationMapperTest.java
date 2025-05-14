package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassificationMapperTest {

    @Test
    void shouldMapToDomainCorrectly() {
        EditeurEntity editeur = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        PlateformeEntity plateforme = PlateformeEntity.builder()
                .id(1L)
                .nom("PlayStation 5")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(List.of())
                .build();

        JeuEntity jeu = JeuEntity.builder()
                .id(1L)
                .nom("Far Cry 6")
                .editeur(editeur)
                .plateformes(List.of(plateforme))
                .build();

        ClassificationEntity entity = ClassificationEntity.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(List.of(jeu))
                .build();

        Classification domain = ClassificationMapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals("PEGI 18", domain.getNom());
        assertEquals("#FF0000", domain.getCouleurRGB());
        assertEquals(1, domain.getJeux().size());
    }

    @Test
    void shouldMapToEntityCorrectly() {
        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("PlayStation 5")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(List.of())
                .build();

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Far Cry 6")
                .editeur(editeur)
                .plateformes(List.of(plateforme))
                .build();

        Classification domain = Classification.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(List.of(jeu))
                .build();

        ClassificationEntity entity = ClassificationMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals("PEGI 18", entity.getNom());
        assertEquals("#FF0000", entity.getCouleurRGB());
        assertEquals(1, entity.getJeux().size());
    }

    @Test
    void shouldReturnNullOnNullEntity() {
        assertNull(ClassificationMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullOnNullDomain() {
        assertNull(ClassificationMapper.toEntity(null));
    }
}
