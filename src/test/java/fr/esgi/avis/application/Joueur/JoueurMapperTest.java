package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.AvatarMapper;
import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.AvisMapper;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JoueurMapperTest {

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        // GIVEN
        JoueurEntity joueurEntity = createJoueurEntity();

        // WHEN
        Joueur joueur = JoueurMapper.toDomain(joueurEntity);

        // THEN
        assertNotNull(joueur);
        assertEquals(joueurEntity.getId(), joueur.getId());
        assertEquals(joueurEntity.getPseudo(), joueur.getPseudo());
        assertEquals(joueurEntity.getEmail(), joueur.getEmail());
        assertEquals(joueurEntity.getMotDePasse(), joueur.getMotDePasse());
        assertEquals(joueurEntity.getDateDeNaissance(), joueur.getDateDeNaissance());
        assertNotNull(joueur.getAvatar());
        assertEquals(joueurEntity.getAvatar().getNom(), joueur.getAvatar().getNom());
        assertEquals(joueurEntity.getAvis().size(), joueur.getAvis().size());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        // GIVEN
        Joueur joueur = createJoueur();

        // WHEN
        JoueurEntity joueurEntity = JoueurMapper.toEntity(joueur);

        // THEN
        assertNotNull(joueurEntity);
        assertEquals(joueur.getId(), joueurEntity.getId());
        assertEquals(joueur.getPseudo(), joueurEntity.getPseudo());
        assertEquals(joueur.getEmail(), joueurEntity.getEmail());
        assertEquals(joueur.getMotDePasse(), joueurEntity.getMotDePasse());
        assertEquals(joueur.getDateDeNaissance(), joueurEntity.getDateDeNaissance());
        assertNotNull(joueurEntity.getAvatar());
        assertEquals(joueur.getAvatar().getNom(), joueurEntity.getAvatar().getNom());
        assertEquals(joueur.getAvis().size(), joueurEntity.getAvis().size());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // WHEN
        Joueur joueur = JoueurMapper.toDomain(null);

        // THEN
        assertNull(joueur);
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        // WHEN
        JoueurEntity joueurEntity = JoueurMapper.toEntity(null);

        // THEN
        assertNull(joueurEntity);
    }

    private JoueurEntity createJoueurEntity() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        AvatarEntity avatarEntity = new AvatarEntity(1L, "Guerrier", null);
        List<AvisEntity> avisEntities = new ArrayList<>();

        return JoueurEntity.builder()
                .id(10L)
                .pseudo("PlayerOne")
                .motDePasse("password")
                .email("player@example.com")
                .dateDeNaissance(birthdate)
                .avatar(avatarEntity)
                .avis(avisEntities)
                .build();
    }

    private Joueur createJoueur() {
        LocalDate birthdate = LocalDate.of(1995, 6, 15);
        Avatar avatar = new Avatar(1L, "Guerrier", null);
        List<Avis> avisList = new ArrayList<>();

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
}
