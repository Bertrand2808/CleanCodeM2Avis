package fr.esgi.avis.controller.Jeu.dto;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
public class JeuDTO {

    private Long id;
    @NonNull
    private String nom;

    @NonNull private Editeur editeur;

    private Genre genre;

    private Classification classification;

    private String description;

    private LocalDate dateDeSortie;

    private List<Plateforme> plateformes;

    private String image;

    private float prix;
}