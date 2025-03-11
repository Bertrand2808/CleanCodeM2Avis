package fr.esgi.avis.domain.Plateforme;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.List;
import java.util.Optional;

public interface PlateformeDataSourcePort {
    List<Plateforme> findAll();
    Optional<Plateforme> findById(Long id); // ✅ Added findById
    Optional<Plateforme> findByName(String Name);
    Plateforme save(Plateforme plateforme);
    void delete(Plateforme plateforme);
}
