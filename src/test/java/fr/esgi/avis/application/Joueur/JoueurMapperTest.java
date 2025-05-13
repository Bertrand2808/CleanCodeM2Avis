package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.application.Jeu.model.JeuEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JoueurMapperTest {

    private final Random random = new Random();

    @Test
    void shouldMapEntityToDomainSuccessfully() {
        AvatarEntity avatarEntity = new AvatarEntity(1L, "Viking", 1L);

        JoueurEntity joueurEntity = JoueurEntity.builder()
                .id(1L)
                .pseudo("Ezio")
                .email("ezio@ac.com")
                .motDePasse("hiddenBlade")
                .dateDeNaissance(LocalDate.of(1995, 6, 12))
                .avatar(avatarEntity)
                .avis(new ArrayList<>())
                .build();

        JeuEntity jeuEntity = JeuEntity.builder()
                .id(10L)
                .nom("Zelda")
                .editeur(EditeurEntity.builder().id(1L).nom("Ubisoft").build())
                .build();

        ModerateurEntity moderateurEntity = ModerateurEntity.builder()
                .id(99L)
                .pseudo("mod123")
                .motDePasse("securePass")
                .email("mod@example.com")
                .numeroDeTelephone("0601020304")
                .build();

        AvisEntity avisEntity = AvisEntity.builder()
                .id(1L)
                .description("Excellent game")
                .jeu(jeuEntity)
                .joueur(joueurEntity)
                .note(5f)
                .dateDEnvoi(LocalDateTime.now())
                .moderateur(moderateurEntity)
                .build();

        joueurEntity.setAvis(List.of(avisEntity));

        Joueur joueur = JoueurMapper.toDomain(joueurEntity);

        assertNotNull(joueur);
        assertEquals("Ezio", joueur.getPseudo());
        assertEquals("ezio@ac.com", joueur.getEmail());
        assertEquals("Viking", joueur.getAvatar().getNom());
        assertEquals(1, joueur.getAvis().size());
    }

    @Test
    void shouldMapDomainToEntitySuccessfully() {
        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Ubisoft")
                .jeux(List.of())
                .build();

        Jeu jeu = Jeu.builder()
                .id(10L)
                .nom("Assassin's Creed Valhalla")
                .editeur(editeur)
                .build();

        Moderateur moderateur = Moderateur.builder()
                .id(99L)
                .pseudo("ModAC")
                .email("mod@ac.com")
                .motDePasse("modpass")
                .numeroDeTelephone("0102030405")
                .build();

        Joueur joueur = Joueur.builder()
                .id(1L)
                .pseudo("Altair")
                .email("altair@ac.com")
                .motDePasse("creed123")
                .dateDeNaissance(LocalDate.of(1985, 3, 15))
                .avatar(new Avatar(1L, "Viking", 1L))
                .build();

        Avis avis = Avis.builder()
                .id(1L)
                .description("Masterpiece")
                .jeuId(jeu.getId())
                .joueurId(joueur.getId())
                .moderateurId(moderateur.getId())
                .note(5f)
                .dateDEnvoi(LocalDateTime.now())
                .build();

        joueur.setAvis(List.of(avis));

        JoueurEntity entity = JoueurMapper.toEntity(joueur);

        assertNotNull(entity);
        assertEquals("Altair", entity.getPseudo());
        assertEquals("altair@ac.com", entity.getEmail());
        assertEquals("Viking", entity.getAvatar().getNom());
        assertEquals(1, entity.getAvis().size());
    }

    @Test
    void shouldReturnNullWhenMappingNullEntity() {
        assertNull(JoueurMapper.toDomain(null));
    }

    @Test
    void shouldReturnNullWhenMappingNullDomain() {
        assertNull(JoueurMapper.toEntity(null));
    }
}
