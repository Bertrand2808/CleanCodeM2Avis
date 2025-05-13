package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Classification.ClassificationMapper;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AvisMapperTest {

    private Random random = new Random();

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        // GIVEN
        JoueurEntity joueurEntity = createJoueurEntity();
        Long joueurId = random.nextLong(100);
        joueurEntity.setId(joueurId);

        EditeurEntity editeurEntity = EditeurEntity.builder()
                .id(1L)
                .nom("Ubisoft")
                .build();

        JeuEntity jeuEntity = JeuEntity.builder()
                .id(10L)
                .nom("Zelda")
                .editeur(editeurEntity)
                .build();

        ModerateurEntity moderateurEntity = ModerateurEntity.builder()
                .id(1L)
                .pseudo("mod123")
                .motDePasse("securePass")
                .email("mod@example.com")
                .numeroDeTelephone("0601020304")
                .build();

        AvisEntity avisEntity = AvisEntity.builder()
                .id(1L)
                .description("Super jeu")
                .jeu(jeuEntity)
                .joueur(joueurEntity)
                .note(5f)
                .dateDEnvoi(LocalDateTime.now())
                .moderateur(moderateurEntity)
                .build();

        // WHEN
        Avis avis = AvisMapper.toDomain(avisEntity);

        // THEN
        assertNotNull(avis);
        assertEquals(avisEntity.getId(), avis.getId());
        assertEquals(avisEntity.getDescription(), avis.getDescription());
        assertEquals(joueurId, avis.getJoueurId());
        assertEquals(avisEntity.getNote(), avis.getNote());
        assertEquals(avisEntity.getDateDEnvoi(), avis.getDateDEnvoi());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        // GIVEN
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        List<Avis> avisList = new ArrayList<>();

        Joueur joueur = Joueur.builder()
                .id(42L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@example.com")
                .dateDeNaissance(birthdate)
                .avis(avisList)
                .build();

        Avatar avatar = new Avatar(1L, "Warrior", joueur.getId());
        joueur.setAvatar(avatar);

        Avis avisDomain = Avis.builder()
                .id(1L)
                .description("Super jeu")
                .jeuId(10L)
                .joueurId(joueur.getId())
                .note(5f)
                .dateDEnvoi(LocalDateTime.now())
                .moderateurId(99L)
                .build();

        avisList.add(avisDomain);
        joueur.setAvis(avisList);

        // WHEN
        AvisEntity avisEntity = AvisMapper.toEntity(avisDomain);

        // THEN
        assertNotNull(avisEntity);
        assertNotNull(avisEntity.getId());
        assertNotNull(avisEntity.getJoueur());
        assertNotNull(avisEntity.getDateDEnvoi());
        assertEquals(avisDomain.getId(), avisEntity.getId());
        assertEquals(avisDomain.getDescription(), avisEntity.getDescription());
        assertEquals(joueur.getId(), avisEntity.getJoueur().getId());
        assertEquals(avisDomain.getNote(), avisEntity.getNote());
        assertEquals(avisDomain.getDateDEnvoi(), avisEntity.getDateDEnvoi());
    }

    @Test
    void shouldThrowExceptionWhenEntityIsNull() {
        // WHEN & THEN
        assertNull(AvisMapper.toDomain(null));
    }

    @Test
    void shouldThrowExceptionWhenAvisIsNull() {
        // WHEN & THEN
        assertNull(AvisMapper.toEntity(null));
    }

    private JoueurEntity createJoueurEntity() {
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        List<AvisEntity> avis = new ArrayList<>();

        JoueurEntity joueurEntity = JoueurEntity.builder()
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@example.com")
                .dateDeNaissance(birthdate)
                .avis(avis)
                .build();

        AvatarEntity avatarEntity = new AvatarEntity(
                null,
                "Warrior",
                joueurEntity.getId()
        );

        joueurEntity.setAvatar(avatarEntity);

        return joueurEntity;
    }
}
