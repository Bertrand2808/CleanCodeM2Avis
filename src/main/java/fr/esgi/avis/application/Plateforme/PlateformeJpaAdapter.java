package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlateformeJpaAdapter implements PlateformeDataSourcePort {

    private final PlateformeJpaRepository plateformeJpaRepository;

    @Override
    public List<Plateforme> findAll() {
        return PlateformeMapper.toDomainList(plateformeJpaRepository.findAll());
    }

    @Override
    public Optional<Plateforme> findByName(String name) {
        return plateformeJpaRepository.findByName(name).map(PlateformeMapper::toDomain);
    }

    @Override
    public Plateforme save(Plateforme plateforme) {
        return PlateformeMapper.toDomain(plateformeJpaRepository.save(PlateformeMapper.toEntity(plateforme)));
    }

    @Override
    public void delete(Plateforme plateforme) {
        plateformeJpaRepository.delete(PlateformeMapper.toEntity(plateforme));
    }
}
