package fr.esgi.avis.controller.Jeu.dto;

import java.time.LocalDate;
import java.util.List;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class JeuDTO {

    private Long id;
    @NonNull
    private String nom;

    @NonNull private Editeur editeur;

    private Genre genre;

    private Classification classification;

    private String description;

    private LocalDate dateDeSortie;

    private List<PlateformeDTO> plateformes;

    private String image;

    private float prix;
}
