package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JeuMapperTest {

    @Test
    void shouldMapEntityToDomainSuccessfully() {
        List<JeuEntity> emptyJeuxList = List.of();

        EditeurEntity editeur = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(emptyJeuxList)
                .build();

        GenreEntity genre = GenreEntity.builder()
                .id(1L)
                .nom("Action")
                .jeux(emptyJeuxList)
                .build();

        ClassificationEntity classification = ClassificationEntity.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(emptyJeuxList)
                .build();

        PlateformeEntity plateforme = PlateformeEntity.builder()
                .id(1L)
                .nom("PlayStation")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(emptyJeuxList)
                .build();

        JeuEntity fakeJeu = new JeuEntity();
        fakeJeu.setId(1L);
        fakeJeu.setNom("Far Cry");
        fakeJeu.setEditeur(editeur);
        fakeJeu.setGenre(genre);
        fakeJeu.setClassification(classification);
        fakeJeu.setPlateformes(List.of(plateforme));
        fakeJeu.setDescription("Open-world action game");
        fakeJeu.setDateDeSortie(LocalDate.of(2020, 11, 12));
        fakeJeu.setImage("farcry.jpg");
        fakeJeu.setPrix(59.99f);

        Jeu jeu = JeuMapper.toDomain(fakeJeu);

        assertNotNull(jeu);
        assertEquals("Far Cry", jeu.getNom());
        assertEquals("Ubisoft", jeu.getEditeur().getNom());
        assertEquals("Action", jeu.getGenre().getNom());
        assertEquals("PEGI 18", jeu.getClassification().getNom());
        assertEquals(1, jeu.getPlateformes().size());
    }

    @Test
    void shouldMapDomainToEntitySuccessfully() {
        List<Jeu> emptyJeuxList = List.of();

        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(emptyJeuxList)
                .build();

        Genre genre = Genre.builder()
                .id(1L)
                .nom("Action")
                .jeux(emptyJeuxList)
                .build();

        Classification classification = Classification.builder()
                .id(1L)
                .nom("PEGI 18")
                .couleurRGB("#FF0000")
                .jeux(emptyJeuxList)
                .build();

        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("PlayStation")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(emptyJeuxList)
                .build();

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Far Cry")
                .editeur(editeur)
                .genre(genre)
                .classification(classification)
                .plateformes(List.of(plateforme))
                .description("Open-world action game")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .image("farcry.jpg")
                .prix(59.99f)
                .build();

        JeuEntity entity = JeuMapper.toEntity(jeu);

        assertNotNull(entity);
        assertEquals("Far Cry", entity.getNom());
        assertEquals("Ubisoft", entity.getEditeur().getNom());
        assertEquals("Action", entity.getGenre().getNom());
        assertEquals("PEGI 18", entity.getClassification().getNom());
        assertEquals(1, entity.getPlateformes().size());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        assertNull(JeuMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        assertNull(JeuMapper.toEntity(null));
    }
}
