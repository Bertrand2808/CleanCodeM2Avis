package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Utilisateur.dto.ConnexionDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.useCases.Utilisateur.UtilisateurUseCases;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Utilisateur Controller
 * Controller for Utilisateur : manage all requests for Utilisateur
 */
@Repository
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurUseCases utilisateurUseCases;
    private final HttpSession session;

    /**
     * Create an utilisateur
     * @param utilisateurDTO : utilisateur to create
     * @return the created utilisateur
     */
    public UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = UtilisateurDtoMapper.toDomain(utilisateurDTO);
        Utilisateur createdUtilisateur = utilisateurUseCases.createUtilisateur(utilisateur);
        return UtilisateurDtoMapper.toDto(createdUtilisateur);
    }

    /**
     * Get an utilisateur by its pseudo
     * @param pseudo : pseudo of the utilisateur to find
     * @return the utilisateur found
     */
    public Optional<UtilisateurDTO> getUtilisateurByPseudo(String pseudo) {
        return utilisateurUseCases.getUtilisateurByPseudo(pseudo)
                .map(UtilisateurDtoMapper::toDto);
    }

    /**
     * Get an utilisateur by its email
     * @param email : email of the utilisateur to find
     * @return the utilisateur found
     */
    public Optional<UtilisateurDTO> getUtilisateurByEmail(String email) {
        return utilisateurUseCases.getUtilisateurByEmail(email)
                .map(UtilisateurDtoMapper::toDto);
    }

    /**
     * Get an utilisateur by its id
     * @param id : id of the utilisateur to find
     * @return the utilisateur found
     */
    public Optional<UtilisateurDTO> getUtilisateurById(Long id) {
        return utilisateurUseCases.getUtilisateurById(id)
                .map(UtilisateurDtoMapper::toDto);
    }

    /**
     * Connexion d'un utilisateur
     * @param connexionDTO : données de connexion
     * @return l'utilisateur connecté
     */
    public UtilisateurDTO connexion(ConnexionDTO connexionDTO) {
        Utilisateur utilisateur = utilisateurUseCases.connexion(connexionDTO.getPseudo(), connexionDTO.getMotDePasse());
        UtilisateurDTO utilisateurDTO = UtilisateurDtoMapper.toDto(utilisateur);
        session.setAttribute("utilisateur", utilisateurDTO);
        return utilisateurDTO;
    }

    /**
     * Déconnexion d'un utilisateur
     */
    public void deconnexion() {
        session.removeAttribute("utilisateur");
    }

    // TODO : Add other methods

}
