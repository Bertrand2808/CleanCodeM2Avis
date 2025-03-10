package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Joueur.JoueurMapper;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Avis.model.Avis;

public class AvisMapper {

    /**
     * Convert an AvisEntity to a domain Avis object.
     * @param avisEntity the entity to convert
     * @return Avis domain object
     * @throws IllegalArgumentException if avisEntity is null
     */
    public static Avis toDomain(AvisEntity avisEntity) {
        if (avisEntity == null) {
            throw new IllegalArgumentException("AvisEntity cannot be null");
        }
        return new Avis(
                avisEntity.getId(),
                avisEntity.getDescription(),
                avisEntity.getJoueur().getId(),
                avisEntity.getNote(),
                avisEntity.getDateDEnvoi()
        );
    }

    /**
     * Convert an Avis domain object to an AvisEntity for persistence.
     * @param avis the domain object to convert
     * @return AvisEntity persistence object
     * @throws IllegalArgumentException if avis is null
     */
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

        return avisEntity;
    }
}
