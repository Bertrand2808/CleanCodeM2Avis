package fr.esgi.avis.domain.Moderateur.model;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Représente un modérateur, une extension de la classe {@link Utilisateur}.
 * Un modérateur dispose des mêmes caractéristiques qu'un utilisateur,
 * avec en supplément un numéro de téléphone pour une identification
 * et une communication plus directe.
 *
 * <p>Cette classe est annotée avec Lombok pour générer automatiquement :
 * <ul>
 *     <li>Les getters et setters ({@code @Data})</li>
 *     <li>Un constructeur sans arguments ({@code @NoArgsConstructor})</li>
 *     <li>Un constructeur avec tous les arguments ({@code @AllArgsConstructor})</li>
 *     <li>Un builder pour une construction fluide ({@code @SuperBuilder})</li>
 * </ul>
 * </p>
 *
 * @author Bertrand2808
 * @version 1.0
 * @since 2025-03-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Moderateur extends Utilisateur {

    /**
     * Numéro de téléphone du modérateur.
     * Ce champ peut être utilisé pour contacter le modérateur en cas de besoin.
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
    public Moderateur(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
        super(pseudo, motDePasse, mail);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}
