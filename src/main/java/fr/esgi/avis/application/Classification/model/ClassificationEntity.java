package fr.esgi.avis.application.Classification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class ClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String couleurRGB;

    @OneToMany(mappedBy = "classification")
    @JsonIgnore
    private List<JeuEntity> jeux;

    public ClassificationEntity(Long id, String nom, String couleurRGB, List<JeuEntity> jeux) {
        this.id = id;
        this.nom = nom;
        this.couleurRGB = couleurRGB;
        this.jeux = jeux;
    }
}
