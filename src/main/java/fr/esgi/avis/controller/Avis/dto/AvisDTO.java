package fr.esgi.avis.controller.Avis.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * AvisDTO is a class used to represent the data transfer object of an Avis.
 * It includes validation constraints to ensure data integrity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvisDTO {
    private Long id;

    @NotBlank(message = "La description est requise")
    @Size(min = 10, max = 1000, message = "La description doit contenir entre 10 et 1000 caractères")
    @Pattern(
        regexp = "^[\\p{L}\\p{N}\\s.,!?'\"()-]+$",
        message = "La description ne doit contenir que des lettres, chiffres et ponctuation basique"
    )
    private String description;

    @NotNull(message = "L'ID du jeu est requis")
    @Positive(message = "L'ID du jeu doit être un nombre positif")
    private Long jeuId;

    @Positive(message = "L'ID du joueur doit être un nombre positif")
    private Long joueurId;

    @NotNull(message = "La note est requise")
    @DecimalMin(value = "0.0", message = "La note minimale est 0")
    @DecimalMax(value = "20.0", message = "La note maximale est 20")
    private Float note;

    @PastOrPresent(message = "La date d'envoi ne peut pas être dans le futur")
    private LocalDateTime dateDEnvoi;

    @Positive(message = "L'ID du modérateur doit être un nombre positif")
    private Long moderateurId;
}
