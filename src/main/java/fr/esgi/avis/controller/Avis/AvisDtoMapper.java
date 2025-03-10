package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;

public class AvisDtoMapper {

    /**
     * Convert Avis to AvisDTO
     * @param avis Avis
     * @return AvisDTO
     */
    public static AvisDTO toDto(Avis avis) {
        if (avis == null) {
            return null;
        }
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setDescription(avis.getDescription());
        avisDTO.setNote(avis.getNote());
        avisDTO.setId(avis.getId());
        avisDTO.setJoueurId(avis.getJoueurId());
        avisDTO.setDateDEnvoi(avis.getDateDEnvoi());
        //avisDTO.setJeuId(avis.getJeuId());
        return avisDTO;
    }

    /**
     * Convert AvisDTO to Avis
     * @param avisDTO AvisDTO
     * @return Avis
     */
    public static Avis toDomain(AvisDTO avisDTO) {
        if (avisDTO == null) {
            return null;
        }
        Avis avis = new Avis();
        avis.setDescription(avisDTO.getDescription());
        avis.setNote(avisDTO.getNote());
        avis.setId(avisDTO.getId());
        avis.setJoueurId(avisDTO.getJoueurId());
        avis.setDateDEnvoi(avisDTO.getDateDEnvoi());
        //avis.setJeuId(avisDTO.getJeuId());
        return avis;
    }
}
