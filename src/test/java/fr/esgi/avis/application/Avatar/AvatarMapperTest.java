package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
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

class AvatarMapperTest {

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        JoueurEntity joueurEntity = createJoueurEntity();
        Long joueurId = joueurEntity.getId();

        // GIVEN
        AvatarEntity avatarEntity = new AvatarEntity(1L, "Warrior", joueurId);

        // WHEN
        Avatar avatar = AvatarMapper.toDomain(avatarEntity);

        // THEN
        assertNotNull(avatar);
        assertEquals(avatarEntity.getId(), avatar.getId());
        assertEquals(avatarEntity.getNom(), avatar.getNom());
        assertEquals(avatarEntity.getId(), avatar.getId());
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

        // WHEN
        AvatarEntity avatarEntity = AvatarMapper.toEntity(avatar);

        // THEN
        assertNotNull(avatarEntity);
        assertEquals(avatar.getId(), avatarEntity.getId());
        assertEquals(avatar.getNom(), avatarEntity.getNom());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // WHEN
        Avatar result = AvatarMapper.toDomain(null);

        // THEN
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenAvatarIsNull() {
        // WHEN
        AvatarEntity result = AvatarMapper.toEntity(null);

        // THEN
        assertNull(result);
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
