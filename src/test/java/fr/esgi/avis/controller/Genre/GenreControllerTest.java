package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreControllerTest {

    @Mock
    private GenreUseCases genreUseCases;

    @InjectMocks
    private GenreController genreController;

    private Genre fakeGenre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeGenre = new Genre();
        fakeGenre.setId(1L);
        fakeGenre.setNom("Action");
        fakeGenre.setJeux(new ArrayList<>());
    }

    @Test
    void shouldCreateGenreSuccessfully() {
        GenreDTO dto = new GenreDTO();
        dto.setNom("Action");
        dto.setJeux(new ArrayList<>());

        when(genreUseCases.createGenre(any())).thenReturn(fakeGenre);

        GenreDTO result = genreController.createGenre(dto);

        assertNotNull(result);
        assertEquals("Action", result.getNom());
    }

    @Test
    void shouldGetAllGenres() {
        when(genreUseCases.getGenres()).thenReturn(List.of(fakeGenre));

        List<GenreDTO> result = genreController.getGenres();

        assertEquals(1, result.size());
        assertEquals("Action", result.get(0).getNom());
    }

    @Test
    void shouldGetGenreById() {
        when(genreUseCases.getGenreById(1L)).thenReturn(Optional.of(fakeGenre));

        Optional<GenreDTO> result = genreController.getGenreById(1L);

        assertTrue(result.isPresent());
        assertEquals("Action", result.get().getNom());
    }

    @Test
    void shouldReturnEmptyWhenGenreNotFoundById() {
        when(genreUseCases.getGenreById(999L)).thenReturn(Optional.empty());

        Optional<GenreDTO> result = genreController.getGenreById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetGenreByNom() {
        when(genreUseCases.getGenreByNom("Action")).thenReturn(Optional.of(fakeGenre));

        Optional<GenreDTO> result = genreController.getGenreByNom("Action");

        assertTrue(result.isPresent());
        assertEquals("Action", result.get().getNom());
    }

    @Test
    void shouldGetGenresByNomContaining() {
        when(genreUseCases.getGenresByNomContaining("Act")).thenReturn(List.of(fakeGenre));

        List<GenreDTO> result = genreController.getGenresByNomContaining("Act");

        assertEquals(1, result.size());
        assertEquals("Action", result.get(0).getNom());
    }

    @Test
    void shouldDeleteGenreById() {
        doNothing().when(genreUseCases).deleteGenreById(1L);

        genreController.deleteGenreById(1L);

        verify(genreUseCases, times(1)).deleteGenreById(1L);
    }
}
