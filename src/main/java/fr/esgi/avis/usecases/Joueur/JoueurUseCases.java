package fr.esgi.avis.usecases.Joueur;

import fr.esgi.avis.domain.Joueur.JoueurRepository;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * JoueurUseCases
 */
@RequiredArgsConstructor
@Service
public class JoueurUseCases {

    private final JoueurRepository joueurRepository;

    /**
     * Create a Joueur
     * @param joueur : Joueur to create
     * @return the created Joueur
     */
    public Joueur createJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    /**
     * Get a Joueur by its pseudo
     * @param pseudo : pseudo of the joueur to find
     * @return the joueur found
     */
    public Optional<Joueur> getJoueurByPseudo(String pseudo) {
        return joueurRepository.findByPseudo(pseudo);
    }

    /**
     * Get a Joueur by its birthdate
     * @param birthdate : birthdate of the joueur to find
     * @return Joueur found
     */
    public Optional<Joueur> getJoueurByBirthdate(LocalDate birthdate) {
        return joueurRepository.findByDateDeNaissance(birthdate);
    }
}
