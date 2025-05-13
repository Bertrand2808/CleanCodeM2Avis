package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.ModerateurDTO;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;

/**
 * Classe utilitaire permettant la conversion entre {@link Moderateur} et {@link ModerateurDTO}.
 * Elle assure la transformation bidirectionnelle des données entre le domaine et la couche de présentation.
 *
 * <p>Les méthodes de cette classe permettent :</p>
 * <ul>
 *     <li>De convertir un objet {@link Moderateur} en {@link ModerateurDTO} pour l'exposition des données.</li>
 *     <li>De convertir un objet {@link ModerateurDTO} en {@link Moderateur} pour le traitement métier.</li>
 * </ul>
 *
 * <p>Cette classe est conçue pour être utilisée en tant que classe utilitaire et ne doit pas être instanciée.</p>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
public class ModerateurDtoMapper {

    /**
     * Convertit une instance de {@link Moderateur} en {@link ModerateurDTO}.
     *
     * @param moderateur L'objet domaine représentant un modérateur.
     * @return Un objet {@link ModerateurDTO} contenant les données du modérateur,
     *         ou {@code null} si l'entrée est {@code null}.
     */
    public static ModerateurDTO toDto(Moderateur moderateur) {
        if (moderateur == null) return null;
        ModerateurDTO moderateurDTO = new ModerateurDTO();
        moderateurDTO.setNumeroDeTelephone(moderateur.getNumeroDeTelephone());

        // Champs hérités de l'utilisateur
        moderateurDTO.setPseudo(moderateur.getPseudo());
        moderateurDTO.setEmail(moderateur.getEmail());
        moderateurDTO.setMotDePasse(moderateur.getMotDePasse());

        return moderateurDTO;
    }

    /**
     * Convertit une instance de {@link ModerateurDTO} en {@link Moderateur}.
     *
     * @param moderateurDTO L'objet DTO contenant les données d'un modérateur.
     * @return Un objet {@link Moderateur} avec les données converties,
     *         ou {@code null} si l'entrée est {@code null}.
     */
    public static Moderateur toDomain(ModerateurDTO moderateurDTO) {
        if (moderateurDTO == null) return null;

        Moderateur moderateur = new Moderateur();
        moderateur.setNumeroDeTelephone(moderateurDTO.getNumeroDeTelephone());

        // Champs hérités de l'utilisateur
        moderateur.setEmail(moderateurDTO.getEmail());
        moderateur.setPseudo(moderateurDTO.getPseudo());
        moderateur.setMotDePasse(moderateurDTO.getMotDePasse());

        return moderateur;
    }
}
