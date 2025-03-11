package fr.esgi.avis.application.Moderateur.model;

import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Représente une entité persistante d'un modérateur, héritant de {@link UtilisateurEntity}.
 * Cette classe est utilisée pour la persistance des données dans la base de données.
 *
 * <p>Annotations utilisées :</p>
 * <ul>
 *     <li>{@code @Entity} : Définit cette classe comme une entité JPA.</li>
 *     <li>{@code @SuperBuilder} : Active le design pattern Builder en héritant du parent.</li>
 *     <li>{@code @NoArgsConstructor} : Génère un constructeur sans argument, requis par JPA.</li>
 *     <li>{@code @AllArgsConstructor} : Génère un constructeur avec tous les arguments.</li>
 *     <li>{@code @Data} : Génère automatiquement les getters, setters, toString, equals et hashCode.</li>
 * </ul>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModerateurEntity extends UtilisateurEntity {

    /**
     * Numéro de téléphone du modérateur.
     * Il permet une communication directe avec le modérateur et peut être utilisé pour des notifications.
     */
    private String numeroDeTelephone;

    /**
     * Constructeur permettant d'initialiser un modérateur avec ses informations essentielles.
     *
     * @param pseudo            Le pseudonyme du modérateur.
     * @param motDePasse        Le mot de passe du modérateur.
     * @param mail              L'adresse email du modérateur.
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     */
    public ModerateurEntity(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
        super(pseudo, motDePasse, mail);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}
