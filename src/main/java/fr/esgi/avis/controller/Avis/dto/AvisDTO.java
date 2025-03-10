package fr.esgi.avis.controller.Avis.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * AvisDTO is a class used to represent the data transfer object of an Avis.
 */
@Data
public class AvisDTO {
    private Long id;
    private String description;
    private Long jeuId;
    private Long joueurId;
    private Float note;
    private LocalDateTime dateDEnvoi;
    private Long moderateurId;
}
