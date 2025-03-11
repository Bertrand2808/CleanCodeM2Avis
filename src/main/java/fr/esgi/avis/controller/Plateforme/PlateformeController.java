package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.controller.Plateforme.PlateformeDtoMapper;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class PlateformeController {

    private final PlateformeUseCases plateformeUseCases;

    public List<PlateformeDTO> getPlateformes() {
        return plateformeUseCases.recupererPlateformes().stream()
                .map(PlateformeDtoMapper::toDto)
                .toList();
    }

    public Optional<PlateformeDTO> getPlateformeById(Long id) {
        return plateformeUseCases.getPlateformeById(id)
                .map(PlateformeDtoMapper::toDto);
    }

    public Optional<PlateformeDTO> getPlateformeByNom(String nom) {
        return plateformeUseCases.getPlateformeByNom(nom)
                .map(PlateformeDtoMapper::toDto);
    }

    public PlateformeDTO createPlateforme(PlateformeDTO plateformeDTO) {
        return PlateformeDtoMapper.toDto(
                plateformeUseCases.createPlateforme(PlateformeDtoMapper.toDomain(plateformeDTO)) // ✅ Fixed method name
        );
    }

    public void deletePlateformeById(Long id) {
        plateformeUseCases.deletePlateforme(id);
    }
}