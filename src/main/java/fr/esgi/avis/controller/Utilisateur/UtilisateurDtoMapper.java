package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.domain.Joueur.model.Joueur;

public class UtilisateurDtoMapper {

    /**
     * Convert Utilisateur to UtilisateurDTO (handles Joueur specifically)
     * @param utilisateur Utilisateur : Utilisateur to convert
     * @return UtilisateurDTO : Converted DTO
     */
    public static UtilisateurDTO toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDTO utilisateurDTO;

        if (utilisateur instanceof Joueur joueur) {
            // Si c'est un Joueur, renvoyer un JoueurDTO
            utilisateurDTO = new JoueurDTO();
            ((JoueurDTO) utilisateurDTO).setDateDeNaissance(joueur.getDateDeNaissance());
        } else {
            utilisateurDTO = new UtilisateurDTO();
        }

        utilisateurDTO.setPseudo(utilisateur.getPseudo());
        utilisateurDTO.setMotDePasse(utilisateur.getMotDePasse());
        utilisateurDTO.setEmail(utilisateur.getEmail());

        return utilisateurDTO;
    }

    /**
     * Convert UtilisateurDTO to Utilisateur (creates Joueur if needed)
     * @param utilisateurDTO UtilisateurDTO : UtilisateurDTO to convert
     * @return Utilisateur : Converted Utilisateur
     */
    public static Utilisateur toDomain(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO == null) {
            return null;
        }

        if (utilisateurDTO instanceof JoueurDTO joueurDTO) {
            // Si c'est un JoueurDTO, on renvoie un Joueur
            return Joueur.builder()
                    .pseudo(joueurDTO.getPseudo())
                    .motDePasse(joueurDTO.getMotDePasse())
                    .email(joueurDTO.getEmail())
                    .dateDeNaissance(joueurDTO.getDateDeNaissance())
                    .build();
        }

        throw new IllegalArgumentException("UtilisateurDTO doit être une sous-classe valide (ex: JoueurDTO)");
    }
}
