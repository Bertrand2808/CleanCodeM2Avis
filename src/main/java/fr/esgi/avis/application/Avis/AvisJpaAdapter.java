package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AvisJpaAdapter implements AvisDataSourcePort {

    private final AvisJpaRepository avisJpaRepository;

    /**
     * Save an Avis
     * @param avis
     * @return Avis
     */
    @Override
    public Avis save(Avis avis) {
        AvisEntity avisEntity = AvisMapper.toEntity(avis);
        return AvisMapper.toDomain(avisJpaRepository.save(avisEntity));
    }

    /**
     * Find an Avis by its id
     * @param id
     * @return Optional<Avis>
     */
    @Override
    public Optional<Avis> findById(Long id) {
        return avisJpaRepository.findById(id).map(AvisMapper::toDomain);
    }

    /**
     * Delete an Avis by its id
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        avisJpaRepository.deleteById(id);
    }

    /**
     * Count the number of Avis
     * @return long
     */
    @Override
    public long count() {
        return avisJpaRepository.count();
    }

    /**
     * Find all Avis by jeuId
     * @param jeuId
     * @return List<Avis>
     */
    @Override
    public List<Avis> findByJeuId(Long jeuId) {
        return avisJpaRepository.findByJeu_Id(jeuId).stream()
                .map(AvisMapper::toDomain)
                .collect(Collectors.toList());
    }
}
