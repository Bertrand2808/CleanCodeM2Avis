package fr.esgi.avis.controller.Classification.dto;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClassificationDTO {
    private Long id;
    private String nom;
    private String couleurRGB;
    private List<JeuDTO> jeux;
}
