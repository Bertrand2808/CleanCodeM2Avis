package fr.esgi.avis.application.Editeur;

import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.domain.Editeur.model.Editeur;

import java.util.stream.Collectors;

public class EditeurMapper {
    public static Editeur toDomain(EditeurEntity editeurEntity) {
        if (editeurEntity == null) return null;

        return Editeur.builder()
                .id(editeurEntity.getId())
                .nom(editeurEntity.getNom())
                .jeux(editeurEntity.getJeux().stream()
                        .map(JeuMapper::toDomain)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static EditeurEntity toEntity(Editeur editeur) {
        if (editeur == null) return null;

        return EditeurEntity.builder()
                .id(editeur.getId())
                .nom(editeur.getNom())
                .jeux(editeur.getJeux().stream()
                    .map(JeuMapper::toEntity)
                    .collect(Collectors.toList())
                )
                .build();
    }
}
