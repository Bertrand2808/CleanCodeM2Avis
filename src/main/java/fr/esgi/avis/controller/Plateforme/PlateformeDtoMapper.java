package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.stream.Collectors;

public class PlateformeDtoMapper {

    public static PlateformeDTO toDto(Plateforme plateforme) {
        if (plateforme == null) return null;
        PlateformeDTO dto = new PlateformeDTO();
        dto.setNom(plateforme.getNom());
        dto.setDateDeSortie(plateforme.getDateDeSortie());
        dto.setJeux(plateforme.getJeux().stream()
                        .map(JeuDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    public static Plateforme toDomain(PlateformeDTO dto) {
        if (dto == null) return null;
        Plateforme plateforme = new Plateforme();
        plateforme.setNom(dto.getNom());
        plateforme.setDateDeSortie(dto.getDateDeSortie());
        plateforme.setJeux(dto.getJeux().stream()
                .map(JeuDtoMapper::toDomain)
                .collect(Collectors.toList())
        );
        return plateforme;
    }
}
