package fr.esgi.avis.application.Editeur;

import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditeurMapperTest {

    @Test
    void shouldConvertEntityToDomainSuccessfully() {

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

        EditeurEntity entity = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of(jeuEntity))
                .build();

        Editeur editeur = EditeurMapper.toDomain(entity);

        assertNotNull(editeur);
        assertEquals(entity.getId(), editeur.getId());
        assertEquals(entity.getNom(), editeur.getNom());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("PlayStation 5")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(List.of())
                .build();

        Editeur jeuEditeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Assassin's Creed Valhalla")
                .editeur(jeuEditeur)
                .plateformes(List.of(plateforme))
                .build();

        Editeur fullEditeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of(jeu))
                .build();

        EditeurEntity entity = EditeurMapper.toEntity(fullEditeur);

        assertNotNull(entity);
        assertEquals(fullEditeur.getId(), entity.getId());
        assertEquals(fullEditeur.getNom(), entity.getNom());
        assertEquals(1, entity.getJeux().size());
    }

    @Test
    void shouldReturnNullIfEntityIsNull() {
        assertNull(EditeurMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullIfDomainIsNull() {
        assertNull(EditeurMapper.toEntity(null));
    }
}
