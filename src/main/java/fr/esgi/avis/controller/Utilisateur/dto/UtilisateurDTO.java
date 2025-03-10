package fr.esgi.avis.controller.Utilisateur.dto;

import lombok.Data;

/**
 * Utilisateur DTO
 */
@Data
public class UtilisateurDTO {
    private String pseudo;
    private String motDePasse;
    private String email;
}
