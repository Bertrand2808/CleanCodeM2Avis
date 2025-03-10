package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Avatar.AvatarDtoMapper;
import fr.esgi.avis.controller.Avis.AvisDtoMapper;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;

import java.util.stream.Collectors;

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
        joueurDTO.setDateDeNaissance(joueur.getDateDeNaissance());
        joueurDTO.setAvatar(AvatarDtoMapper.toDto(joueur.getAvatar()));
        joueurDTO.setAvis(joueur.getAvis().stream()
                .map(AvisDtoMapper::toDto)
                .collect(Collectors.toList()));
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
        joueur.setAvis(joueurDTO.getAvis().stream()
                .map(AvisDtoMapper::toDomain)
                .collect(Collectors.toList()));
        return joueur;
    }
}
