package fr.esgi.avis.controller.Utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la connexion d'un utilisateur
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnexionDTO {
    private String pseudo;
    private String motDePasse;
}
