package fr.esgi.avis.application.Editeur.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Builder
public class EditeurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NonNull
    @NotBlank(message="Merci de préciser le nom de l'éditeur")
    @Size(min=2, message="Le nom de l'éditeur doit comporter au moins {min} caractères")
    private String nom;

    @OneToMany(mappedBy="editeur", cascade = CascadeType.REMOVE)
    @JsonBackReference
    @ToString.Exclude
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Jeu> jeux;

    public EditeurEntity(Long id, String nom, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
    }
}
