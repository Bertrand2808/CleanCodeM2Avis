package fr.esgi.avis.application.Genre;

import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {

    @Test
    void shouldConvertToDomainSuccessfully() {
        EditeurEntity editeurEntity = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        PlateformeEntity plateformeEntity = PlateformeEntity.builder()
                .id(1L)
                .nom("PlayStation 5")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(List.of())
                .build();

        JeuEntity jeuEntity = JeuEntity.builder()
                .id(1L)
                .nom("Assassin's Creed Valhalla")
                .editeur(editeurEntity)
                .plateformes(List.of(plateformeEntity))
                .build();

        GenreEntity entity = GenreEntity.builder()
                .id(1L)
                .nom("Action-Adventure")
                .jeux(List.of(jeuEntity))
                .build();

        Genre genre = GenreMapper.toDomain(entity);

        assertNotNull(genre);
        assertEquals(entity.getId(), genre.getId());
        assertEquals(entity.getNom(), genre.getNom());
    }

    @Test
    void shouldConvertToEntitySuccessfully() {
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
                .nom("Assassin's Creed Valhalla")
                .editeur(editeur)
                .plateformes(List.of(plateforme))
                .description("Viking open-world RPG by Ubisoft")
                .build();

        Genre genre = Genre.builder()
                .id(1L)
                .nom("Action-Adventure")
                .jeux(List.of(jeu))
                .build();

        GenreEntity entity = GenreMapper.toEntity(genre);

        assertNotNull(entity);
        assertEquals(genre.getId(), entity.getId());
        assertEquals(genre.getNom(), entity.getNom());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        assertNull(GenreMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        assertNull(GenreMapper.toEntity(null));
    }
}
