package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateformeMapperTest {

    @Test
    void shouldMapEntityToDomainSuccessfully() {
        EditeurEntity editeurEntity = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        JeuEntity jeuEntity = JeuEntity.builder()
                .id(1L)
                .nom("Assassin's Creed Valhalla")
                .editeur(editeurEntity)
                .plateformes(List.of())
                .build();

        PlateformeEntity entity = PlateformeEntity.builder()
                .id(1L)
                .nom("PlayStation 5")
                .jeux(List.of(jeuEntity))
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .build();

        Plateforme plateforme = PlateformeMapper.toDomain(entity);

        assertNotNull(plateforme);
        assertEquals(entity.getId(), plateforme.getId());
        assertEquals(entity.getNom(), plateforme.getNom());
        assertEquals(entity.getDateDeSortie(), plateforme.getDateDeSortie());
        assertEquals(1, plateforme.getJeux().size());
    }

    @Test
    void shouldMapDomainToEntitySuccessfully() {
        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Assassin's Creed Valhalla")
                .editeur(editeur)
                .plateformes(List.of())
                .build();

        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("PlayStation 5")
                .jeux(List.of(jeu))
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .build();

        PlateformeEntity entity = PlateformeMapper.toEntity(plateforme);

        assertNotNull(entity);
        assertEquals(plateforme.getId(), entity.getId());
        assertEquals(plateforme.getNom(), entity.getNom());
        assertEquals(plateforme.getDateDeSortie(), entity.getDateDeSortie());
        assertEquals(1, entity.getJeux().size());
    }

    @Test
    void shouldReturnNullWhenMappingNullEntityToDomain() {
        assertNull(PlateformeMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenMappingNullDomainToEntity() {
        assertNull(PlateformeMapper.toEntity(null));
    }
}
