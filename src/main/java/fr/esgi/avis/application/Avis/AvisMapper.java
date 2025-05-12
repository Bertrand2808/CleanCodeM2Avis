package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Avis.model.Avis;

public class AvisMapper {

    public static Avis toDomain(AvisEntity avisEntity) {
        if (avisEntity == null) {
            throw new IllegalArgumentException("AvisEntity cannot be null");
        }

        return new Avis(
                avisEntity.getId(),
                avisEntity.getDescription(),
                avisEntity.getJeu().getId(),
                avisEntity.getJoueur().getId(),
                avisEntity.getNote(),
                avisEntity.getDateDEnvoi(),
                avisEntity.getModerateur().getId()
        );
    }

    public static AvisEntity toEntity(Avis avis) {
        if (avis == null) {
            throw new IllegalArgumentException("Avis cannot be null");
        }

        AvisEntity avisEntity = new AvisEntity();
        avisEntity.setId(avis.getId());
        avisEntity.setDescription(avis.getDescription());
        avisEntity.setNote(avis.getNote());
        avisEntity.setDateDEnvoi(avis.getDateDEnvoi());

        JoueurEntity joueurEntity = new JoueurEntity();
        joueurEntity.setId(avis.getJoueurId());
        avisEntity.setJoueur(joueurEntity);

        JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(avis.getJeuId());
        avisEntity.setJeu(jeuEntity);

        ModerateurEntity moderateurEntity = new ModerateurEntity();
        moderateurEntity.setId(avis.getModerateurId());
        avisEntity.setModerateur(moderateurEntity);

        return avisEntity;
    }
}
