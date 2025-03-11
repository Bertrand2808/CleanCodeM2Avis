package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JeuJpaAdapter implements JeuDataSourcePort {
    private final JeuJpaRepository jeuRepository;

    @Override
    public Jeu save(Jeu jeu) {
        JeuEntity jeuEntity = JeuMapper.toEntity(jeu);
        return JeuMapper.toDomain(jeuRepository.save(jeuEntity));
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        return jeuRepository.findById(id).map(JeuMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jeuRepository.deleteById(id);
    }
}