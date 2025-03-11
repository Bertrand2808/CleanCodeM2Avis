package fr.esgi.avis.domain.Utilisateur.model;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Utilisateur model
 */

@Data
@SuperBuilder
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Utilisateur {
    protected Long id;

    @NonNull
    @Column(length=80)
    @Size(max=80)
    protected String pseudo;

    @NonNull
    protected String motDePasse;

    @NonNull
    protected String email;

}
