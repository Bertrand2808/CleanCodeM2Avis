package fr.esgi.avis.usecases.Avis;

import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Avis use cases for business logic
 */
@RequiredArgsConstructor
@Service
public class AvisUseCases {

    private final AvisDataSourcePort avisDataSourcePort;

    /**
     * Create an avis
     * @param avis
     * @return
     */
    public Avis createAvis(Avis avis) {
        return avisDataSourcePort.save(avis);
    }

    /**
     * Get an avis by id
     * @param id of the avis to get
     * @return
     */
    public Optional<Avis> getAvisById(Long id) {
        return avisDataSourcePort.findById(id);
    }

    /**
     * Delete by id
     * @param id : id of the avis to delete
     */
    public void deleteAvis(Long id) {
        avisDataSourcePort.deleteById(id);
    }

}
