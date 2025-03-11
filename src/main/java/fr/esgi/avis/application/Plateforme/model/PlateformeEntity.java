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
public class PlateformeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToMany(mappedBy = "plateformes")
    private List<JeuEntity> jeux;

    private LocalDate dateDeSortie;

    public PlateformeEntity(Long id, String nom, List<JeuEntity> jeux, LocalDate dateDeSortie) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
        this.dateDeSortie = dateDeSortie;
    }
}