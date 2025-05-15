package fr.esgi.avis.application.Classification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
