package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlateformeJpaAdapterTest {

    @Mock
    private PlateformeJpaRepository plateformeJpaRepository;

    @InjectMocks
    private PlateformeJpaAdapter plateformeJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSavePlateformeSuccessfully() {
        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("PlayStation 5")
                .dateDeSortie(LocalDate.of(2020, 11, 12))
                .jeux(List.of())
                .build();

        when(plateformeJpaRepository.save(any())).thenReturn(PlateformeMapper.toEntity(plateforme));

        Plateforme saved = plateformeJpaAdapter.save(plateforme);

        assertNotNull(saved);
        assertEquals("PlayStation 5", saved.getNom());
        verify(plateformeJpaRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAllPlateformes() {
        Plateforme plateforme = Plateforme.builder()
                .id(1L)
                .nom("Xbox Series X")
                .dateDeSortie(LocalDate.of(2020, 11, 10))
                .jeux(List.of())
                .build();

        when(plateformeJpaRepository.findAll()).thenReturn(List.of(PlateformeMapper.toEntity(plateforme)));

        List<Plateforme> result = plateformeJpaAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals("Xbox Series X", result.get(0).getNom());
    }

    @Test
    void shouldFindByIdSuccessfully() {
        Plateforme plateforme = Plateforme.builder()
                .id(2L)
                .nom("Switch")
                .dateDeSortie(LocalDate.of(2017, 3, 3))
                .jeux(List.of())
                .build();

        when(plateformeJpaRepository.findById(2L)).thenReturn(Optional.of(PlateformeMapper.toEntity(plateforme)));

        Optional<Plateforme> found = plateformeJpaAdapter.findById(2L);

        assertTrue(found.isPresent());
        assertEquals("Switch", found.get().getNom());
    }

    @Test
    void shouldFindByNomSuccessfully() {
        Plateforme plateforme = Plateforme.builder()
                .id(3L)
                .nom("Steam Deck")
                .dateDeSortie(LocalDate.of(2022, 2, 25))
                .jeux(List.of())
                .build();

        when(plateformeJpaRepository.findByNom("Steam Deck")).thenReturn(Optional.of(PlateformeMapper.toEntity(plateforme)));

        Optional<Plateforme> found = plateformeJpaAdapter.findByNom("Steam Deck");

        assertTrue(found.isPresent());
        assertEquals("Steam Deck", found.get().getNom());
    }

    @Test
    void shouldFindByNomContainingSuccessfully() {
        Plateforme plateforme = Plateforme.builder()
                .id(4L)
                .nom("PlayStation 4")
                .dateDeSortie(LocalDate.of(2013, 11, 15))
                .jeux(List.of())
                .build();

        when(plateformeJpaRepository.findByNomContaining("PlayStation"))
                .thenReturn(List.of(PlateformeMapper.toEntity(plateforme)));

        List<Plateforme> result = plateformeJpaAdapter.findByNomContaining("PlayStation");

        assertEquals(1, result.size());
        assertTrue(result.get(0).getNom().contains("PlayStation"));
    }

    @Test
    void shouldDeleteByIdSuccessfully() {
        doNothing().when(plateformeJpaRepository).deleteById(5L);

        plateformeJpaAdapter.deleteById(5L);

        verify(plateformeJpaRepository, times(1)).deleteById(5L);
    }
}
