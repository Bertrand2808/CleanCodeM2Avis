package fr.esgi.avis.useCases.Genre;

import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Genre.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GenreUseCasesTest {

    @Mock
    private GenreDataSourcePort genreDataSourcePort;

    @InjectMocks
    private GenreUseCases genreUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateGenreSuccessfully() {
        Genre genre = createGenre();
        when(genreDataSourcePort.save(any(Genre.class))).thenReturn(genre);

        Genre created = genreUseCases.createGenre(genre);

        assertNotNull(created);
        verify(genreDataSourcePort, times(1)).save(any(Genre.class));
    }

    @Test
    void shouldGetAllGenres() {
        when(genreDataSourcePort.findAll()).thenReturn(List.of(createGenre()));

        List<Genre> all = genreUseCases.getGenres();

        assertFalse(all.isEmpty());
        verify(genreDataSourcePort, times(1)).findAll();
    }

    @Test
    void shouldGetGenreByIdIfExists() {
        Genre genre = createGenre();
        when(genreDataSourcePort.findById(1L)).thenReturn(Optional.of(genre));

        Optional<Genre> found = genreUseCases.getGenreById(1L);

        assertTrue(found.isPresent());
        verify(genreDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyIfGenreNotFoundById() {
        when(genreDataSourcePort.findById(1L)).thenReturn(Optional.empty());

        Optional<Genre> found = genreUseCases.getGenreById(1L);

        assertTrue(found.isEmpty());
        verify(genreDataSourcePort, times(1)).findById(1L);
    }

    @Test
    void shouldGetGenreByNomIfExists() {
        Genre genre = createGenre();
        when(genreDataSourcePort.findByNom("Test")).thenReturn(Optional.of(genre));

        Optional<Genre> found = genreUseCases.getGenreByNom("Test");

        assertTrue(found.isPresent());
        verify(genreDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldReturnEmptyIfGenreNotFoundByNom() {
        when(genreDataSourcePort.findByNom("Test")).thenReturn(Optional.empty());

        Optional<Genre> found = genreUseCases.getGenreByNom("Test");

        assertTrue(found.isEmpty());
        verify(genreDataSourcePort, times(1)).findByNom("Test");
    }

    @Test
    void shouldGetGenresByNomContaining() {
        when(genreDataSourcePort.findByNomContaining("Te")).thenReturn(List.of(createGenre()));

        List<Genre> list = genreUseCases.getGenresByNomContaining("Te");

        assertFalse(list.isEmpty());
        verify(genreDataSourcePort, times(1)).findByNomContaining("Te");
    }

    @Test
    void shouldDeleteGenreById() {
        doNothing().when(genreDataSourcePort).deleteById(1L);

        genreUseCases.deleteGenreById(1L);

        verify(genreDataSourcePort, times(1)).deleteById(1L);
    }

    private Genre createGenre() {
        return Genre.builder()
                .id(1L)
                .nom("Test")
                .build();
    }
}
