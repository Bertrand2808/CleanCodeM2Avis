package fr.esgi.avis.domain.Classification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Classification {
    private Long id;
    @NonNull private String nom;
    @NonNull private String couleurRGB;
    @JsonIgnore private List<Jeu> jeux;

    public Classification(Long id, String nom, String couleurRGB, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.couleurRGB = couleurRGB;
        this.jeux = jeux;
    }
}
