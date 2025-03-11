package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

public class PlateformeDtoMapper {

    /**
     * Convert Plateforme to PlateformeDto
     * @param plateforme Plateforme
     * @return PlateformeDto
     */
    public static PlateformeDTO toDto(Plateforme plateforme) {
        if (plateforme == null) {
            return null;
        }
        PlateformeDTO dto = new PlateformeDTO();
        dto.setNom(plateforme.getNom());
        dto.setDateDeSortie(plateforme.getDateDeSortie());
        dto.setJeux(plateforme.getJeux()); // Adjust if necessary
        return dto;
    }

    /**
     * Convert PlateformeDto to Plateforme
     * @param dto PlateformeDto
     * @return Plateforme
     */
    public static Plateforme toDomain(PlateformeDTO dto) {
        if (dto == null) {
            return null;
        }
        Plateforme plateforme = new Plateforme();
        plateforme.setNom(dto.getNom());
        plateforme.setDateDeSortie(dto.getDateDeSortie());
        plateforme.setJeux(dto.getJeux()); // Adjust if necessary
        return plateforme;
    }
}
