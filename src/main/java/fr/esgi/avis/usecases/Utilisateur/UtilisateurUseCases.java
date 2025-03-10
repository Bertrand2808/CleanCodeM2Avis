package fr.esgi.avis.usecases.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.UtilisateurRepository;
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

    private final UtilisateurRepository utilisateurRepository;

    /**
     * Create an utilisateur
     *
     * @param utilisateur
     * @return
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Get an utilisateur by id
     *
     * @param id of the utilisateur to get
     * @return
     */
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    /**
     * Delete by id
     *
     * @param id : id of the utilisateur to delete
     */
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    /**
     * Find an utilisateur by its pseudo
     *
     * @param pseudo of the utilisateur to find
     * @return the utilisateur
     */
    public Optional<Utilisateur> getUtilisateurByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    /**
     * Find an utilisateur by its email
     *
     * @param email of the utilisateur to find
     * @return the utilisateur
     */
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
