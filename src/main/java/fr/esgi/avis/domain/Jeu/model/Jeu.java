package fr.esgi.avis.domain.Jeu.model;

import java.time.LocalDate;
import java.util.List;

import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Jeu(String nom, Editeur editeur, Genre genre, Classification classification, String description, LocalDate dateDeSortie, List<Plateforme> plateformes, String image, float prix) {
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
