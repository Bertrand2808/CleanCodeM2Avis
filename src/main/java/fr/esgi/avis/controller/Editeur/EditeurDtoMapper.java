package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.EditeurDTO;
import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.domain.Editeur.model.Editeur;

import java.util.stream.Collectors;

public class EditeurDtoMapper {
    public static EditeurDTO toDto(Editeur editeur) {
        if (editeur == null) return null;
        EditeurDTO editeurDTO = new EditeurDTO();
        editeurDTO.setId(editeur.getId());
        editeurDTO.setNom(editeur.getNom());
        editeurDTO.setJeux(
                editeur.getJeux().stream()
                        .map(JeuDtoMapper::toDto)
                        .collect(Collectors.toList())
        );
        return editeurDTO;
    }

    public static Editeur toDomain(EditeurDTO editeurDTO) {
        if (editeurDTO == null) return null;
        Editeur editeur = new Editeur();
        editeur.setId(editeurDTO.getId());
        editeur.setNom(editeurDTO.getNom());
        editeur.setJeux(
                editeurDTO.getJeux().stream()
                        .map(JeuDtoMapper::toDomain)
                        .collect(Collectors.toList())
        );
        return editeur;
    }
}
