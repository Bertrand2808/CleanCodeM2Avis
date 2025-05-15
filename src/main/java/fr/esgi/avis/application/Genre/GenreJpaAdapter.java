package fr.esgi.avis.application.Genre;

import fr.esgi.avis.application.Genre.GenreJpaRepository;
import fr.esgi.avis.application.Genre.GenreMapper;
import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Genre.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GenreJpaAdapter implements GenreDataSourcePort {
    private final GenreJpaRepository genreJpaRepository;

    @Override
    public Genre save(Genre genre) {
        GenreEntity genreEntity = GenreMapper.toEntity(genre);
        return GenreMapper.toDomain(genreJpaRepository.save(genreEntity));
    }

    @Override
    public List<Genre> findAll() {
        return genreJpaRepository.findAll()
                .stream()
                .map(GenreMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreJpaRepository.findById(id).map(GenreMapper::toDomain);
    }

    @Override
    public Optional<Genre> findByNom(String nom) {
        return genreJpaRepository.findByNom(nom).map(GenreMapper::toDomain);
    }

    @Override
    public List<Genre> findByNomContaining(String keyword) {
        return genreJpaRepository.findByNomContaining(keyword)
                .stream()
                .map(GenreMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        genreJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return genreJpaRepository.count();
    }
}
