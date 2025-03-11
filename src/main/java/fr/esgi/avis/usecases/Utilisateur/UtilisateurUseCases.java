package fr.esgi.avis.usecases.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Utilisateur use cases for business logic
 */
@RequiredArgsConstructor
@Service
public class UtilisateurUseCases {

    private final UtilisateurDataSourcePort utilisateurDataSourcePort;

    /**
     * Create an utilisateur
     *
     * @param utilisateur
     * @return
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurDataSourcePort.save(utilisateur);
    }

    /**
     * Get an utilisateur by id
     *
     * @param id of the utilisateur to get
     * @return
     */
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurDataSourcePort.findById(id);
    }

    /**
     * Delete by id
     *
     * @param id : id of the utilisateur to delete
     */
    public void deleteUtilisateur(Long id) {
        utilisateurDataSourcePort.deleteById(id);
    }

    /**
     * Find an utilisateur by its pseudo
     *
     * @param pseudo of the utilisateur to find
     * @return the utilisateur
     */
    public Optional<Utilisateur> getUtilisateurByPseudo(String pseudo) {
        return utilisateurDataSourcePort.findByPseudo(pseudo);
    }

    /**
     * Find an utilisateur by its email
     *
     * @param email of the utilisateur to find
     * @return the utilisateur
     */
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurDataSourcePort.findByEmail(email);
    }
}
