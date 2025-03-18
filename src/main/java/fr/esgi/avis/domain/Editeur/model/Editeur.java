package fr.esgi.avis.domain.Editeur.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder
public class Editeur {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NonNull
    @NotBlank(message="Merci de préciser le nom de l'éditeur")
    @Size(min=2, message="Le nom de l'éditeur doit comporter au moins {min} caractères")
    private String nom;

    @JsonBackReference
    @ToString.Exclude
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Jeu> jeux;

    public Editeur(Long id, String nom, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
    }
}
