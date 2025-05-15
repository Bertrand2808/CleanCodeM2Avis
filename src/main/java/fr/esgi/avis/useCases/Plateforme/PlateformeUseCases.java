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

    private final PlateformeDataSourcePort plateformeDataSourcePort;

    public Plateforme createPlateforme(Plateforme plateforme) {
        return plateformeDataSourcePort.save(plateforme);
    }

    public List<Plateforme> getlateformes() {
        return plateformeDataSourcePort.findAll();
    }

    public Optional<Plateforme> getPlateformeById(Long id) {
        return plateformeDataSourcePort.findById(id);
    }

    public Optional<Plateforme> getPlateformeByNom(String nom) {
        return plateformeDataSourcePort.findByNom(nom);
    }

    public List<Plateforme> getPlateformesByNomContaining(String keyword) {
        return plateformeDataSourcePort.findByNomContaining(keyword);
    }

    public void deletePlateforme(Long id) {
        plateformeDataSourcePort.deleteById(id);
    }
}
