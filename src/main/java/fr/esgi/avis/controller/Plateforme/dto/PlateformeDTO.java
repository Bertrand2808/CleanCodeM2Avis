package fr.esgi.avis.controller.Plateforme.dto;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class PlateformeDTO implements Serializable {
    private Long id;
    private String nom;
    private List<JeuDTO> jeux;
    private LocalDate dateDeSortie;
}
