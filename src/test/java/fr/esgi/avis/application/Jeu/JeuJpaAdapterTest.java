package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JeuJpaAdapterTest {

    @Mock
    private JeuJpaRepository jeuJpaRepository;

    @InjectMocks
    private JeuJpaAdapter jeuJpaAdapter;

    private Jeu jeu;
    private JeuEntity jeuEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        EditeurEntity editeur = EditeurEntity.builder().id(1L).nom("Ubisoft").jeux(List.of()).build();
        GenreEntity genre = GenreEntity.builder().id(1L).nom("Action").jeux(List.of()).build();
        ClassificationEntity classification = ClassificationEntity.builder().id(1L).nom("PEGI 18").couleurRGB("#FF0000").jeux(List.of()).build();
        PlateformeEntity plateforme = PlateformeEntity.builder().id(1L).nom("PS5").dateDeSortie(LocalDate.of(2020, 11, 12)).jeux(List.of()).build();

        jeuEntity = JeuEntity.builder()
                .id(1L)
                .nom("Far Cry 6")
                .editeur(editeur)
                .genre(genre)
                .classification(classification)
                .plateformes(List.of(plateforme))
                .description("Open world FPS")
                .dateDeSortie(LocalDate.of(2021, 10, 7))
                .image("farcry6.jpg")
                .prix(59.99f)
                .build();

        jeu = JeuMapper.toDomain(jeuEntity);
    }

    @Test
    void shouldSaveJeuSuccessfully() {
        when(jeuJpaRepository.save(any(JeuEntity.class))).thenReturn(jeuEntity);

        Jeu saved = jeuJpaAdapter.save(jeu);

        assertNotNull(saved);
        assertEquals(jeu.getNom(), saved.getNom());
        verify(jeuJpaRepository, times(1)).save(any(JeuEntity.class));
    }

    @Test
    void shouldFindAllJeuxSuccessfully() {
        when(jeuJpaRepository.findAll()).thenReturn(List.of(jeuEntity));

        List<Jeu> all = jeuJpaAdapter.findAll();

        assertEquals(1, all.size());
        verify(jeuJpaRepository, times(1)).findAll();
    }

    @Test
    void shouldFindJeuByIdSuccessfully() {
        when(jeuJpaRepository.findById(1L)).thenReturn(Optional.of(jeuEntity));

        Optional<Jeu> found = jeuJpaAdapter.findById(1L);

        assertTrue(found.isPresent());
        assertEquals("Far Cry 6", found.get().getNom());
        verify(jeuJpaRepository, times(1)).findById(1L);
    }

    @Test
    void shouldFindJeuByNomSuccessfully() {
        when(jeuJpaRepository.findByNom("Far Cry 6")).thenReturn(Optional.of(jeuEntity));

        Optional<Jeu> found = jeuJpaAdapter.findByNom("Far Cry 6");

        assertTrue(found.isPresent());
        assertEquals("Far Cry 6", found.get().getNom());
        verify(jeuJpaRepository, times(1)).findByNom("Far Cry 6");
    }

    @Test
    void shouldFindJeuxByNomContainingSuccessfully() {
        when(jeuJpaRepository.findByNomContaining("Far")).thenReturn(List.of(jeuEntity));

        List<Jeu> results = jeuJpaAdapter.findByNomContaining("Far");

        assertEquals(1, results.size());
        verify(jeuJpaRepository, times(1)).findByNomContaining("Far");
    }

    @Test
    void shouldDeleteJeuByIdSuccessfully() {
        doNothing().when(jeuJpaRepository).deleteById(1L);

        jeuJpaAdapter.deleteById(1L);

        verify(jeuJpaRepository, times(1)).deleteById(1L);
    }
}
