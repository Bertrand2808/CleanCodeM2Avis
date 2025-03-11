package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.usecases.Joueur.JoueurUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JoueurController
 * Controller for Joueur : manage the requests with the use cases
 */
@Repository
@RequiredArgsConstructor
public class JoueurController {
    private final JoueurUseCases joueurUseCases;

    /**
     * Create a Joueur
     * @param joueurDTO : Joueur to create
     * @return the created Joueur
     */
    public JoueurDTO createJoueur(JoueurDTO joueurDTO) {
        Joueur joueur = JoueurDtoMapper.toDomain(joueurDTO);
        Joueur createdJoueur = joueurUseCases.createJoueur(joueur);
        return JoueurDtoMapper.toDto(createdJoueur);
    }

    /**
     * Get all joueurs
     * @return List of all joueurs
     */
    public List<JoueurDTO> getAllJoueurs() {
        return joueurUseCases.getAllJoueurs()
                .stream()
                .map(JoueurDtoMapper::toDto)
                .toList(); // ou collect(Collectors.toList())
    }

    /**
     * Get a Joueur by its pseudo
     * @param pseudo : pseudo of the joueur to find
     * @return the joueur found
     */
    public Optional<JoueurDTO> getJoueurByPseudo(String pseudo) {
        return joueurUseCases.getJoueurByPseudo(pseudo)
                .map(JoueurDtoMapper::toDto);
    }

    /**
     * Get a Joueur by its birthdate
     * @param birthdate : birthdate of the joueur to find
     * @return Joueur found
     */
    public Optional<JoueurDTO> getJoueurByBirthdate(LocalDate birthdate) {
        return joueurUseCases.getJoueurByBirthdate(birthdate)
                .map(JoueurDtoMapper::toDto);
    }
}
