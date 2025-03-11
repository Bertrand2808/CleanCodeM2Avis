package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AvisMapperTest {

    private Random random = new Random();

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        JoueurEntity joueurEntity = createJoueurEntity();
        Long joueurId = random.nextLong(100);
        joueurEntity.setId(joueurId);

        // GIVEN
        AvisEntity avisEntity = new AvisEntity(1L, "Super jeu", joueurEntity, 5f, LocalDateTime.now());

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
        List<Avis> avis = new ArrayList<>();
        Joueur joueur = Joueur.builder()
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@example.com")
                .dateDeNaissance(birthdate)
                .avis(avis)
                .build();
        Avatar avatar = new Avatar(
                null,
                "Warrior",
                joueur.getId()
        );
        joueur.setAvatar(avatar);
        Long joueurId = random.nextLong(100);
        joueur.setId(joueurId);
        Avis avisDomain = new Avis(
                1L,
                "Super jeu",
                joueur.getId(),
                5f,
                LocalDateTime.now()
        );
        avis.add(avisDomain);
        joueur.setAvis(avis);

        // WHEN
        AvisEntity avisEntity = AvisMapper.toEntity(avisDomain);

        // THEN
        assertNotNull(avisEntity);
        assertNotNull(avisEntity.getId());
        assertNotNull(avisEntity.getJoueur());
        assertNotNull(avisEntity.getDateDEnvoi());
        assertEquals(avisDomain.getId(), avisEntity.getId());
        assertEquals(avisDomain.getDescription(), avisEntity.getDescription());
        assertEquals(avisDomain.getDescription(), avisEntity.getDescription());
        assertEquals(joueur.getId(), avisEntity.getJoueur().getId());
        assertEquals(avisDomain.getNote(), avisEntity.getNote());
        assertEquals(avisDomain.getDateDEnvoi(), avisEntity.getDateDEnvoi());
    }

    @Test
    void shouldThrowExceptionWhenEntityIsNull() {
        // WHEN & THEN
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> AvisMapper.toDomain(null));
        assertEquals("AvisEntity cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAvisIsNull() {
        // WHEN & THEN
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> AvisMapper.toEntity(null));
        assertEquals("Avis cannot be null", exception.getMessage());
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
