package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurDtoMapperTest {
    
    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        // GIVEN 
        Utilisateur utilisateur = createUtilisateur();
        
        // WHEN 
        UtilisateurDTO utilisateurDTO = UtilisateurDtoMapper.toDto(utilisateur);
        
        // THEN 
        assertNotNull(utilisateurDTO);
        assertEquals(utilisateur.getPseudo(), utilisateurDTO.getPseudo());
        assertEquals(utilisateur.getEmail(), utilisateurDTO.getEmail());
        assertEquals(utilisateur.getMotDePasse(), utilisateurDTO.getMotDePasse());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        // GIVEN
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setPseudo("test");
        joueurDTO.setMotDePasse("password");
        joueurDTO.setEmail("test@exemple.com");
        joueurDTO.setDateDeNaissance(LocalDate.of(1990, 1, 1));

        // WHEN
        Utilisateur utilisateur = UtilisateurDtoMapper.toDomain(joueurDTO);

        // THEN
        assertNotNull(utilisateur);
        assertEquals(joueurDTO.getPseudo(), utilisateur.getPseudo());
        assertEquals(joueurDTO.getMotDePasse(), utilisateur.getMotDePasse());
        assertEquals(joueurDTO.getEmail(), utilisateur.getEmail());
    }


    @Test
    void shouldReturnNullWhenUtilisateurIsNull() {
        // WHEN
        UtilisateurDTO utilisateurDTO = UtilisateurDtoMapper.toDto(null);

        // THEN
        assertNull(utilisateurDTO);
    }

    @Test
    void shouldReturnNullWhenUtilisateurDtoIsNull() {
        // GIVEN
        Utilisateur utilisateur = UtilisateurDtoMapper.toDomain(null);

        // THEN
        assertNull(utilisateur);
    }

    private Utilisateur createUtilisateur() {
        return Joueur.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
    }
}