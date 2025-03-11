package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.ModerateurDTO;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModerateurDtoMapperTest {

    @Test
    void shouldConvertModerateurToDtoSuccessfully() {
        // GIVEN
        Moderateur moderateur = Moderateur.builder()
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();

        // WHEN
        ModerateurDTO moderateurDTO = ModerateurDtoMapper.toDto(moderateur);

        // THEN
        assertNotNull(moderateurDTO);
        assertEquals(moderateur.getPseudo(), moderateurDTO.getPseudo());
        assertEquals(moderateur.getMotDePasse(), moderateurDTO.getMotDePasse());
        assertEquals(moderateur.getEmail(), moderateurDTO.getEmail());
        assertEquals(moderateur.getNumeroDeTelephone(), moderateurDTO.getNumeroDeTelephone());
    }

    @Test
    void shouldConvertDtoToModerateurSuccessfully() {
        // GIVEN
        ModerateurDTO moderateurDTO = new ModerateurDTO();
        moderateurDTO.setPseudo("modAdmin");
        moderateurDTO.setMotDePasse("password123");
        moderateurDTO.setEmail("admin@example.com");
        moderateurDTO.setNumeroDeTelephone("+33123456789");

        // WHEN
        Moderateur moderateur = ModerateurDtoMapper.toDomain(moderateurDTO);

        // THEN
        assertNotNull(moderateur);
        assertEquals(moderateurDTO.getPseudo(), moderateur.getPseudo());
        assertEquals(moderateurDTO.getMotDePasse(), moderateur.getMotDePasse());
        assertEquals(moderateurDTO.getEmail(), moderateur.getEmail());
        assertEquals(moderateurDTO.getNumeroDeTelephone(), moderateur.getNumeroDeTelephone());
    }

    @Test
    void shouldReturnNullWhenModerateurIsNull() {
        // WHEN
        ModerateurDTO result = ModerateurDtoMapper.toDto(null);

        // THEN
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {
        // WHEN
        Moderateur result = ModerateurDtoMapper.toDomain(null);

        // THEN
        assertNull(result);
    }
}
