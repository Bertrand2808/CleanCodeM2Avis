package fr.esgi.avis.domain.Jeu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Jeu {

    private Long id;
    @NonNull private String nom;

    @NonNull private Editeur editeur;

    private Genre genre;

    private Classification classification;

    private String description;

    private LocalDate dateDeSortie;

    private List<Plateforme> plateformes;

    private String image;

    private float prix;

    public Jeu(Long id, String nom, Editeur editeur, Genre genre, Classification classification, String description, LocalDate dateDeSortie, List<Plateforme> plateformes, String image, float prix) {
        this.id = id;
        this.nom = nom;
        this.editeur = editeur;
        this.genre = genre;
        this.classification = classification;
        this.description = description;
        this.dateDeSortie = dateDeSortie;
        this.plateformes = plateformes;
        this.image = image;
        this.prix = prix;
    }
}