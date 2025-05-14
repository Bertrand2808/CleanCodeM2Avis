package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import fr.esgi.avis.domain.Genre.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenreDtoMapperTest {

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setNom("Adventure");
        genre.setJeux(new ArrayList<>());

        GenreDTO dto = GenreDtoMapper.toDto(genre);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Adventure", dto.getNom());
        assertNotNull(dto.getJeux());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        GenreDTO dto = new GenreDTO();
        dto.setId(2L);
        dto.setNom("RPG");
        dto.setJeux(new ArrayList<>());

        Genre genre = GenreDtoMapper.toDomain(dto);

        assertNotNull(genre);
        assertEquals(2L, genre.getId());
        assertEquals("RPG", genre.getNom());
        assertNotNull(genre.getJeux());
    }

    @Test
    void shouldReturnNullWhenGenreIsNull() {
        assertNull(GenreDtoMapper.toDto(null));
    }

    @Test
    void shouldReturnNullWhenGenreDtoIsNull() {
        assertNull(GenreDtoMapper.toDomain(null));
    }
}
