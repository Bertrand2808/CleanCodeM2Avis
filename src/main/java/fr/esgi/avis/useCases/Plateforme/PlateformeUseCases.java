package fr.esgi.avis.useCases.Plateforme;

import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlateformeUseCases {

    private final PlateformeDataSourcePort plateformeRepository;

    public List<Plateforme> recupererPlateformes() {
        return plateformeRepository.findAll();
    }

    public Optional<Plateforme> getPlateformeById(Long id) { // ✅ Added method
        return plateformeRepository.findById(id);
    }

    public Optional<Plateforme> getPlateformeByNom(String nom) { // ✅ Handle null cases
        return plateformeRepository.findByNom(name);
    }

    public Plateforme createPlateforme(Plateforme plateforme) { // ✅ Centralized save logic
        return plateformeRepository.save(plateforme);
    }

    public void deletePlateforme(Long id) { // ✅ Avoids deleting a non-existent record
        plateformeRepository.findById(id).ifPresent(plateformeRepository::delete);
    }
}
