package fr.esgi.avis.domain.Plateforme.model;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Plateforme {
    private Long id;
    @NonNull
    private String nom;
    private List<Jeu> jeux;
    private LocalDate dateDeSortie;
}
