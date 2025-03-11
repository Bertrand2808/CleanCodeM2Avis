package fr.esgi.avis.domain.Plateforme.model;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Plateforme {
    private Long id;
    @NonNull
    private String nom;
    private List<Jeu> jeux;
    private LocalDate dateDeSortie;

    public Plateforme(Long id, String nom, List<Jeu> jeux, LocalDate dateDeSortie) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
        this.dateDeSortie = dateDeSortie;
    }
}
