package fr.esgi.avis.application.Classification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

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
    private List<Jeu> jeux;

    public ClassificationEntity(Long id, String nom, String couleurRGB, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.couleurRGB = couleurRGB;
        this.jeux = jeux;
    }
}
