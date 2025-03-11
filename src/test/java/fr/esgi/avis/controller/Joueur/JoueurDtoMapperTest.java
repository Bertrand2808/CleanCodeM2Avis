package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Joueur.dto.JoueurDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JoueurDtoMapperTest {

    @Test
    void shouldConvertJoueurToDtoSuccessfully() {
        // GIVEN
        Joueur joueur = createJoueur();

        // WHEN
        JoueurDTO joueurDTO = JoueurDtoMapper.toDto(joueur);

        // THEN
        assertNotNull(joueurDTO);
        assertEquals(joueur.getPseudo(), joueurDTO.getPseudo());
        assertEquals(joueur.getEmail(), joueurDTO.getEmail());
        assertEquals(joueur.getMotDePasse(), joueurDTO.getMotDePasse());
        assertEquals(joueur.getDateDeNaissance(), joueurDTO.getDateDeNaissance());
        assertNotNull(joueurDTO.getAvatar());
        assertEquals(joueur.getAvatar().getNom(), joueurDTO.getAvatar().getNom());
        assertEquals(joueur.getAvis().size(), joueurDTO.getAvis().size());
    }

    @Test
    void shouldConvertDtoToJoueurSuccessfully() {
        // GIVEN
        JoueurDTO joueurDTO = createJoueurDTO();
        // WHEN
        Joueur joueur = JoueurDtoMapper.toDomain(joueurDTO);

        // THEN
        assertNotNull(joueur);
        assertEquals(joueurDTO.getPseudo(), joueur.getPseudo());
        assertEquals(joueurDTO.getEmail(), joueur.getEmail());
        assertEquals(joueurDTO.getMotDePasse(), joueur.getMotDePasse());
        assertEquals(joueurDTO.getDateDeNaissance(), joueur.getDateDeNaissance());
        assertNotNull(joueur.getAvatar());
        assertEquals(joueurDTO.getAvatar().getNom(), joueur.getAvatar().getNom());
        assertEquals(joueurDTO.getAvis().size(), joueur.getAvis().size());
    }

    @Test
    void shouldReturnNullWhenJoueurIsNull() {
        // WHEN
        JoueurDTO joueurDTO = JoueurDtoMapper.toDto(null);

        // THEN
        assertNull(joueurDTO);
    }

    @Test
    void shouldReturnNullWhenJoueurDtoIsNull() {
        // WHEN
        Joueur joueur = JoueurDtoMapper.toDomain(null);

        // THEN
        assertNull(joueur);
    }

    private Joueur createJoueur() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        Avatar avatar = new Avatar(1L, "Guerrier", null);
        List<Avis> avisList = new ArrayList<>();
        Avis avis = new Avis();
        avis.setId(1L);
        avis.setDescription("Super jeu");
        avis.setNote(5.0f);
        avis.setJoueurId(1L);
        avis.setDateDEnvoi(LocalDateTime.now());
        avisList.add(avis);

        return Joueur.builder()
                .id(10L)
                .pseudo("PlayerOne")
                .motDePasse("password")
                .email("player@example.com")
                .dateDeNaissance(birthdate)
                .avatar(avatar)
                .avis(avisList)
                .build();
    }

    private JoueurDTO createJoueurDTO() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(1L);
        avatarDTO.setNom("Guerrier");

        List<AvisDTO> avisDTOList = new ArrayList<>();
        AvisDTO avisDTO = new AvisDTO();
        avisDTO.setId(1L);
        avisDTO.setDescription("Super jeu");
        avisDTO.setNote(5.0f);
        avisDTOList.add(avisDTO);

        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setId(10L);
        joueurDTO.setPseudo("PlayerOne");
        joueurDTO.setMotDePasse("password");
        joueurDTO.setEmail("player@example.com");
        joueurDTO.setDateDeNaissance(birthdate);
        joueurDTO.setAvatar(avatarDTO);
        joueurDTO.setAvis(avisDTOList);

        avisDTO.setJoueurId(joueurDTO.getId());
        avatarDTO.setId(joueurDTO.getId());

        return joueurDTO;
    }

}
