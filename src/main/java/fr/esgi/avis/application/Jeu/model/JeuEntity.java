package fr.esgi.avis.application.Jeu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.esgi.avis.application.Classification.model.ClassificationEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Genre.model.GenreEntity;
import fr.esgi.avis.application.Plateforme.model.PlateformeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(indexes = @Index(name="Jeu_Nom_Index", columnList = "nom"))
@Builder
public class JeuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "jeu_seq")
    @SequenceGenerator(name = "jeu_seq")
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String nom;

    @NonNull
    @ManyToOne
    @JsonManagedReference
    private EditeurEntity editeur;

    @ManyToOne
    private GenreEntity genre;

    @ManyToOne
    private ClassificationEntity classification;

    @Lob
    private String description;

    private LocalDate dateDeSortie;

    @ManyToMany
    private List<PlateformeEntity> plateformes;

    private String image;

    private float prix;

    public JeuEntity(Long id, String nom, EditeurEntity editeur, GenreEntity genre, ClassificationEntity classification, String description, LocalDate dateDeSortie, List<PlateformeEntity> plateformes, String image, float prix) {
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