package fr.esgi.avis.application.Jeu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(indexes = @Index(name="Jeu_Nom_Index", columnList = "nom"))
public class JeuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "jeu_seq")
    @SequenceGenerator(name = "jeu_seq")
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String nom;

    @NonNull
    @ManyToOne // Many Jeu to One Editeur
    @JsonManagedReference
    private Editeur editeur;

    @ManyToOne // Many Jeu to One Genre
    private Genre genre;

    @ManyToOne
    private Classification classification;

    @Lob
    private String description;

    private LocalDate dateDeSortie;

    // Jeu est la classe centrale
    //
    @ManyToMany
    private List<Plateforme> plateformes;

    private String image;

    private float prix;

    public JeuEntity(Long id, String nom, Editeur editeur, Genre genre, Classification classification, String description, LocalDate dateDeSortie, List<Plateforme> plateformes, String image, float prix) {
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