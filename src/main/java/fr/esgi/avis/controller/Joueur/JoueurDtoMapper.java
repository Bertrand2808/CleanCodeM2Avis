package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Avatar.AvatarDtoMapper;
import fr.esgi.avis.controller.Avis.AvisDtoMapper;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * JoueurDtoMapper :
 * - Convert Joueur to JoueurDTO
 * - Convert JoueurDTO to Joueur
 * Use : Both Joueur and Utilisateur fields because Joueur extends Utilisateur
 */
public class JoueurDtoMapper {

    /**
     * Convert Joueur to JoueurDTO
     * @param joueur Joueur
     * @return JoueurDTO
     */
    public static JoueurDTO toDto(Joueur joueur) {
        if (joueur == null) {
            return null;
        }
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setId(joueur.getId());
        joueurDTO.setDateDeNaissance(joueur.getDateDeNaissance());
        joueurDTO.setAvatar(AvatarDtoMapper.toDto(joueur.getAvatar()));
        joueurDTO.setAvis(joueur.getAvis() != null ?
                joueur.getAvis().stream().map(AvisDtoMapper::toDto).collect(Collectors.toList()) :
                new ArrayList<>());

        // Champs de Utilisateur
        joueurDTO.setPseudo(joueur.getPseudo());
        joueurDTO.setEmail(joueur.getEmail());
        joueurDTO.setMotDePasse(joueur.getMotDePasse());

        return joueurDTO;
    }

    /**
     * Convert JoueurDTO to Joueur
     * @param joueurDTO JoueurDTO
     * @return Joueur
     */
    public static Joueur toDomain(JoueurDTO joueurDTO) {
        if (joueurDTO == null) {
            return null;
        }
        Joueur joueur = new Joueur();
        joueur.setDateDeNaissance(joueurDTO.getDateDeNaissance());
        joueur.setAvatar(AvatarDtoMapper.toDomain(joueurDTO.getAvatar()));
        joueur.setAvis(joueurDTO.getAvis() != null ?
                joueurDTO.getAvis().stream().map(AvisDtoMapper::toDomain).collect(Collectors.toList()) :
                new ArrayList<>());

        // Champs de Utilisateur
        joueur.setPseudo(joueurDTO.getPseudo());
        joueur.setEmail(joueurDTO.getEmail());
        joueur.setMotDePasse(joueurDTO.getMotDePasse());
        return joueur;
    }
}
