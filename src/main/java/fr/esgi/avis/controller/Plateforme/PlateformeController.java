package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.PlateformeDtoMapper;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.controller.Plateforme.PlateformeDtoMapper;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
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

    public PlateformeDTO createPlateforme(PlateformeDTO plateformeDTO) {
        Plateforme plateforme = PlateformeDtoMapper.toDomain(plateformeDTO);
        Plateforme createdPlateforme = plateformeUseCases.createPlateforme(plateforme);
        return PlateformeDtoMapper.toDto(createdPlateforme);
    }

    public List<PlateformeDTO> getPlateformes() {
        return plateformeUseCases.getlateformes().stream()
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

    public List<PlateformeDTO> getJeuxByNomContaining(String keyword) {
        return plateformeUseCases.getPlateformesByNomContaining(keyword).stream()
                .map(PlateformeDtoMapper::toDto)
                .toList();
    }

    public void deletePlateformeById(Long id) {
        plateformeUseCases.deletePlateforme(id);
    }
}