package fr.esgi.avis.application.Plateforme.model;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plateforme")
@Builder
public class PlateformeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToMany(mappedBy = "plateformes")
    private List<JeuEntity> jeux;

    private LocalDate dateDeSortie;
}