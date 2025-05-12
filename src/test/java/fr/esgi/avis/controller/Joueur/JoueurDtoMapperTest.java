package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JoueurDtoMapperTest {

    @Test
    void shouldConvertDomainToDtoSuccessfully() {
        Joueur joueur = new Joueur();
        joueur.setPseudo("Test");
        joueur.setEmail("test@test.com");
        joueur.setMotDePasse("pwd");
        joueur.setDateDeNaissance(LocalDate.of(1995, 1, 1));
        joueur.setAvatar(new fr.esgi.avis.domain.Avatar.model.Avatar(1L, "Knight", 1L));
        joueur.setAvis(Collections.emptyList());

        JoueurDTO dto = JoueurDtoMapper.toDto(joueur);

        assertNotNull(dto);
        assertEquals("Test", dto.getPseudo());
        assertEquals("test@test.com", dto.getEmail());
    }

    @Test
    void shouldConvertDtoToDomainSuccessfully() {
        JoueurDTO dto = new JoueurDTO();
        dto.setPseudo("Test");
        dto.setEmail("test@test.com");
        dto.setMotDePasse("pwd");
        dto.setDateDeNaissance(LocalDate.of(1995, 1, 1));

        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setNom("Knight");
        dto.setAvatar(avatarDTO);

        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Solid game");
        avisDTO.setNote(4.0f);
        avisDTO.setDateDEnvoi(LocalDate.now().atStartOfDay());
        avisDTO.setJoueurId(1L);
        avisDTO.setJeuId(1L);
        avisDTO.setModerateurId(1L);
        dto.setAvis(Collections.singletonList(avisDTO));

        Joueur joueur = JoueurDtoMapper.toDomain(dto);

        assertNotNull(joueur);
        assertEquals("Test", joueur.getPseudo());
        assertEquals("test@test.com", joueur.getEmail());
    }

    @Test
    void shouldReturnNullForNullJoueur() {
        assertNull(JoueurDtoMapper.toDto(null));
    }

    @Test
    void shouldReturnNullForNullJoueurDTO() {
        assertNull(JoueurDtoMapper.toDomain(null));
    }
}
