package fr.esgi.avis.application.Genre.model;

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
public class GenreEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy="genre")
    private List<JeuEntity> jeux;

    public GenreEntity(Long id, String nom, List<JeuEntity> jeux) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
    }
}
