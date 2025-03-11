package fr.esgi.avis.usecases.Joueur;

import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * JoueurUseCases
 */
@RequiredArgsConstructor
@Service
public class JoueurUseCases {

    private final JoueurDataSourcePort joueurDataSourcePort;

    /**
     * Create a Joueur
     * @param joueur : Joueur to create
     * @return the created Joueur
     */
    public Joueur createJoueur(Joueur joueur) {
        return joueurDataSourcePort.save(joueur);
    }

    /**
     * Get all joueurs
     * @return all joueurs
     */
    public List<Joueur> getAllJoueurs() {
        return joueurDataSourcePort.findAll();
    }


    /**
     * Get a Joueur by its pseudo
     * @param pseudo : pseudo of the joueur to find
     * @return the joueur found
     */
    public Optional<Joueur> getJoueurByPseudo(String pseudo) {
        return joueurDataSourcePort.findByPseudo(pseudo);
    }

    /**
     * Get a Joueur by its birthdate
     * @param birthdate : birthdate of the joueur to find
     * @return Joueur found
     */
    public Optional<Joueur> getJoueurByBirthdate(LocalDate birthdate) {
        return joueurDataSourcePort.findByDateDeNaissance(birthdate);
    }
}
