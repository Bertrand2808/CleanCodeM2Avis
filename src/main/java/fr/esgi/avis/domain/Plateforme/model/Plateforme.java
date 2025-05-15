package fr.esgi.avis.domain.Plateforme.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Plateforme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nom;
    private List<Jeu> jeux;
    private LocalDate dateDeSortie;
}
