package fr.esgi.avis.controller.Moderateur.dto;

import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Objet de transfert de données (DTO) représentant un modérateur.
 * Cette classe étend {@link UtilisateurDTO} et ajoute un champ spécifique
 * pour le numéro de téléphone du modérateur.
 *
 * <p>Elle est utilisée pour exposer les données des modérateurs aux couches supérieures
 * (contrôleurs, services) sans exposer directement les entités du domaine.</p>
 *
 * <p>Annotations utilisées :</p>
 * <ul>
 *     <li>{@code @Data} : Génère automatiquement les getters, setters, {@code toString()}, {@code equals()} et {@code hashCode()}.</li>
 * </ul>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModerateurDTO extends UtilisateurDTO {

    /**
     * Numéro de téléphone du modérateur.
     * Ce champ est utilisé pour permettre une communication directe avec le modérateur.
     */
    private String numeroDeTelephone;
}
