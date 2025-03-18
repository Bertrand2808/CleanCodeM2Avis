package fr.esgi.avis.application.Genre.model;

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
public class GenreEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="genre")
    private List<Jeu> jeux;

    public GenreEntity(Long id, String nom, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
    }
}
