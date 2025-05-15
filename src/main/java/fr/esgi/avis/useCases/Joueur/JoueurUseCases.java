package fr.esgi.avis.useCases.Joueur;

import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
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
    private final AvatarDataSourcePort avatarDataSourcePort;

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

    public Optional<Joueur> getJoueurById(Long id) {
        return joueurDataSourcePort.findById(id);
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

    public Optional<Joueur> assignAvatarToJoueur(Long joueurId, Long avatarId) {
        Optional<Joueur> joueurOpt = joueurDataSourcePort.findById(joueurId);
        Optional<Avatar> avatarOpt = avatarDataSourcePort.findById(avatarId);

        if (joueurOpt.isEmpty() || avatarOpt.isEmpty()) {
            return Optional.empty();
        }

        Joueur joueur = joueurOpt.get();
        Avatar avatar = avatarOpt.get();

        // Suppression de l'ancien avatar si nécessaire
        if (joueur.getAvatar() != null) {
            Avatar oldAvatar = joueur.getAvatar();
            oldAvatar.setJoueurId(null);  // Retirer l'ancien lien
            avatarDataSourcePort.save(oldAvatar);
        }

        // Assigner le nouvel avatar
        joueur.setAvatar(avatar);
        avatar.setJoueurId(joueurId);

        joueurDataSourcePort.save(joueur);
        avatarDataSourcePort.save(avatar);

        return Optional.of(joueur);
    }
}
