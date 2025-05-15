package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AvisController
 */
@Repository
@RequiredArgsConstructor
public class AvisController {

    private final AvisUseCases avisUseCases;

    /**
     * Create an Avis
     * @param avisDTO : Avis to create
     * @return the created Avis
     */
    public AvisDTO createAvis(AvisDTO avisDTO) {
        Avis avis = AvisDtoMapper.toDomain(avisDTO);
        Avis createdAvis = avisUseCases.createAvis(avis);
        return AvisDtoMapper.toDto(createdAvis);
    }

    /**
     * Get an Avis by its id
     * @param id : id of the avis to find
     * @return the avis found
     */
    public Optional<AvisDTO> getAvisById(Long id) {
        return avisUseCases.getAvisById(id)
                .map(AvisDtoMapper::toDto);
    }

    /**
     * Delete an Avis by its id
     * @param id : id of the avis to delete
     */
    public void deleteAvis(Long id) {
        avisUseCases.deleteAvis(id);
    }

    /**
     * Get all avis for a jeu
     * @param jeuId : id of the jeu
     * @return list of avis for the jeu
     */
    public List<AvisDTO> getAvisByJeuId(Long jeuId) {
        return avisUseCases.getAvisByJeuId(jeuId)
                .stream()
                .map(AvisDtoMapper::toDto)
                .toList();
    }
}
