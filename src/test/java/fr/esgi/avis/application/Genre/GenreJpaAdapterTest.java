package fr.esgi.avis.application.Genre;

import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.domain.Genre.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreJpaAdapterTest {

    @Mock
    private GenreJpaRepository genreJpaRepository;

    @InjectMocks
    private GenreJpaAdapter genreJpaAdapter;

    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveGenreSuccessfully() {
        Genre genre = Genre.builder().id(1L).nom("Action").jeux(new ArrayList<>()).build();
        GenreEntity genreEntity = GenreMapper.toEntity(genre);

        when(genreJpaRepository.save(any(GenreEntity.class))).thenReturn(genreEntity);

        Genre savedGenre = genreJpaAdapter.save(genre);

        assertNotNull(savedGenre);
        assertEquals("Action", savedGenre.getNom());
        verify(genreJpaRepository, times(1)).save(any(GenreEntity.class));
    }

    @Test
    void shouldFindGenreByIdSuccessfully() {
        Genre genre = Genre.builder().id(2L).nom("Adventure").jeux(new ArrayList<>()).build();
        GenreEntity entity = GenreMapper.toEntity(genre);

        when(genreJpaRepository.findById(2L)).thenReturn(Optional.of(entity));

        Optional<Genre> found = genreJpaAdapter.findById(2L);

        assertTrue(found.isPresent());
        assertEquals("Adventure", found.get().getNom());
        verify(genreJpaRepository, times(1)).findById(2L);
    }

    @Test
    void shouldReturnEmptyWhenGenreNotFoundById() {
        when(genreJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Genre> found = genreJpaAdapter.findById(999L);

        assertFalse(found.isPresent());
        verify(genreJpaRepository, times(1)).findById(999L);
    }

    @Test
    void shouldFindGenreByNomSuccessfully() {
        Genre genre = Genre.builder().id(3L).nom("RPG").jeux(new ArrayList<>()).build();
        GenreEntity entity = GenreMapper.toEntity(genre);

        when(genreJpaRepository.findByNom("RPG")).thenReturn(Optional.of(entity));

        Optional<Genre> found = genreJpaAdapter.findByNom("RPG");

        assertTrue(found.isPresent());
        assertEquals("RPG", found.get().getNom());
        verify(genreJpaRepository, times(1)).findByNom("RPG");
    }

    @Test
    void shouldReturnAllGenres() {
        List<GenreEntity> entities = List.of(
                GenreMapper.toEntity(Genre.builder().id(4L).nom("Puzzle").jeux(new ArrayList<>()).build())
        );

        when(genreJpaRepository.findAll()).thenReturn(entities);

        List<Genre> genres = genreJpaAdapter.findAll();

        assertEquals(1, genres.size());
        assertEquals("Puzzle", genres.get(0).getNom());
    }

    @Test
    void shouldDeleteGenreById() {
        doNothing().when(genreJpaRepository).deleteById(1L);

        genreJpaAdapter.deleteById(1L);

        verify(genreJpaRepository, times(1)).deleteById(1L);
    }
}