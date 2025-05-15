package fr.esgi.avis.domain.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;

import java.util.List;
import java.util.Optional;

public interface AvisDataSourcePort {

    Avis save(Avis avis);
    Optional<Avis> findById(Long id);
    void deleteById(Long id);
    long count();
    List<Avis> findByJeuId(Long jeuId);
}
