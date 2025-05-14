package fr.esgi.avis.domain.Genre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Genre {
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @JsonIgnore
    private List<Jeu> jeux;

    public Genre(Long id, String nom, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
    }
}
