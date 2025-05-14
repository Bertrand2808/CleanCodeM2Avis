package fr.esgi.avis.controller.Editeur.dto;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import lombok.Data;

import java.util.List;

@Data
public class EditeurDTO {
    private Long id;
    private String nom;
    private List<JeuDTO> jeux;
}
