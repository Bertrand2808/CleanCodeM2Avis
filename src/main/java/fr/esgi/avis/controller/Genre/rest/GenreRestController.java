package fr.esgi.avis.controller.Genre.rest;

import fr.esgi.avis.controller.Genre.GenreController;
import fr.esgi.avis.controller.Genre.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreController genreController;

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenreDto = genreController.createGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenreDto);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres() {
        List<GenreDTO> genres = genreController.getGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return genreController.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GenreDTO> getGenresByNom(@PathVariable String nom) {
        return genreController.getGenreByNom(nom)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<GenreDTO>> getGenresByNomContaining(@PathVariable String keyword) {
        List<GenreDTO> genres = genreController.getGenresByNomContaining(keyword);
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        if (genreController.getGenreById(id).isPresent()) {
            genreController.deleteGenreById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}