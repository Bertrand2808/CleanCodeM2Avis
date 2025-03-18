package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.domain.Jeu.model.Jeu;

public class JeuDtoMapper {

    public static JeuDTO toDto(Jeu jeu) {
        if (jeu == null) {
            return null;
        }
        JeuDTO jeuDTO = new JeuDTO();
        jeuDTO.setId(jeu.getId());
        jeuDTO.setNom(jeu.getNom());
        jeuDTO.setEditeur(jeu.getEditeur());
        jeuDTO.setGenre(jeu.getGenre());
        jeuDTO.setClassification(jeu.getClassification());
        jeuDTO.setDescription(jeu.getDescription());
        jeuDTO.setDateDeSortie(jeu.getDateDeSortie());
        jeuDTO.setPlateformes(jeu.getPlateformes());
        jeuDTO.setImage(jeu.getImage());
        jeuDTO.setPrix(jeu.getPrix());
        return jeuDTO;
    }

    public static Jeu toDomain(JeuDTO jeuDTO) {
        if (jeuDTO == null) {
            return null;
        }

        Jeu jeu = new Jeu();
        jeu.setId(jeuDTO.getId());
        jeu.setNom(jeuDTO.getNom());
        jeu.setEditeur(jeuDTO.getEditeur());
        jeu.setGenre(jeuDTO.getGenre());
        jeu.setClassification(jeuDTO.getClassification());
        jeu.setDescription(jeuDTO.getDescription());
        jeu.setDateDeSortie(jeuDTO.getDateDeSortie());
        jeu.setPlateformes(jeuDTO.getPlateformes());
        jeu.setImage(jeuDTO.getImage());
        jeu.setPrix(jeuDTO.getPrix());
        return jeu;
    }
}
