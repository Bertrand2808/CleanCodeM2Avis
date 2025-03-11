package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Joueur.JoueurRepository;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JoueurJpaAdapter
 */
@Repository
@RequiredArgsConstructor
public class JoueurJpaAdapter implements JoueurRepository {
    private final JoueurJpaRepository joueurRepository;

    /**
     * Save a Joueur
     * @param joueur
     * @return Joueur
     */
    @Override
    public Joueur save(Joueur joueur) {
        JoueurEntity joueurEntity = JoueurMapper.toEntity(joueur);
        return JoueurMapper.toDomain(joueurRepository.save(joueurEntity));
    }

    @Override
    public List<Joueur> findAll() {
        return joueurRepository.findAll()
                .stream()
                .map(JoueurMapper::toDomain)
                .collect(Collectors.toList());
    }


    /**
     * Find a Joueur by its pseudo
     * @param pseudo
     * @return Joueur
     */
    @Override
    public Optional<Joueur> findByPseudo(String pseudo) {
        return Optional.ofNullable(JoueurMapper.toDomain(joueurRepository.findByPseudo(pseudo)));
    }

    /**
     * Find a Joueur by its date of birth
     * @param dateDeNaissance
     * @return Joueur
     */
    @Override
    public Optional<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance) {
        return joueurRepository.findByDateDeNaissance(dateDeNaissance)
                .map(JoueurMapper::toDomain);
    }

    /**
     * Delete a Joueur by its pseudo
     * @param pseudo
     * @return void
     */
    public void deleteByPseudo(String pseudo) {
        joueurRepository.deleteByPseudo(pseudo);
    }

    @Override
    public long count() {
        return joueurRepository.count();
    }

}

