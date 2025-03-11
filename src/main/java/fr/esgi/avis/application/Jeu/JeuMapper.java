package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.domain.Jeu.model.Jeu;

public class JeuMapper {
    public static Jeu toDomain(JeuEntity jeuEntity) {
        if (jeuEntity == null) {
            throw new IllegalArgumentException("JeuEntity cannot be null");
        }
        return new Jeu(jeuEntity.getId(), jeuEntity.getNom());
    }

    public static JeuEntity toEntity(Jeu jeu) {
        if (jeu == null) {
            throw new IllegalArgumentException("Jeu cannot be null");
        }
        return new JeuEntity(jeu.getId(), jeu.getNom());
    }
}