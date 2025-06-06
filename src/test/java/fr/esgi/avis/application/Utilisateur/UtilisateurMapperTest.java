package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurMapperTest {

    /**
     * Classe inconnue pour les tests
     * Permet de tester le cas où le type d'utilisateur n'est pas géré
     */
    private static class UnknownUtilisateurEntity extends UtilisateurEntity {
        public UnknownUtilisateurEntity(Long id, String pseudo, String motDePasse, String email) {
            super(id, pseudo, motDePasse, email);
        }
    }

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        // GIVEN
        UtilisateurEntity utilisateurEntity = JoueurEntity.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();

        // WHEN
        Utilisateur utilisateur = UtilisateurMapper.toDomain(utilisateurEntity);

        // THEN
        assertNotNull(utilisateur);
        assertEquals(utilisateurEntity.getId(), utilisateur.getId());
        assertEquals(utilisateurEntity.getPseudo(), utilisateur.getPseudo());
        assertEquals(utilisateurEntity.getMotDePasse(), utilisateur.getMotDePasse());
        assertEquals(utilisateurEntity.getEmail(), utilisateur.getEmail());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        // GIVEN
        Utilisateur utilisateur = Joueur.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();

        // WHEN
        UtilisateurEntity utilisateurEntity = UtilisateurMapper.toEntity(utilisateur);

        // THEN
        assertNotNull(utilisateurEntity);
        assertEquals(utilisateur.getId(), utilisateurEntity.getId());
        assertEquals(utilisateur.getPseudo(), utilisateurEntity.getPseudo());
        assertEquals(utilisateur.getMotDePasse(), utilisateurEntity.getMotDePasse());
        assertEquals(utilisateur.getEmail(), utilisateurEntity.getEmail());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTypeIsNotManaged() {
        // GIVEN
        UtilisateurEntity utilisateurEntity = new UnknownUtilisateurEntity(
                1L, "PseudoTest", "mdpTest", "test@exemple.com"
        );

        // WHEN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> UtilisateurMapper.toDomain(utilisateurEntity)
        );

        // THEN
        assertEquals("Type d'utilisateur non géré : UnknownUtilisateurEntity", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmptyFieldsInToDomain() {
        // GIVEN
        UtilisateurEntity utilisateurEntity = JoueurEntity.builder()
                .id(1L)
                .pseudo("")
                .motDePasse("")
                .email("")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();

        // WHEN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> UtilisateurMapper.toDomain(utilisateurEntity)
        );

        // THEN
        assertEquals("Pseudo, motDePasse and email cannot be empty", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmptyFieldsInToEntity() {
        // GIVEN
        Utilisateur utilisateur = Joueur.builder()
                .id(1L)
                .pseudo("")
                .motDePasse("")
                .email("")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();

        // WHEN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> UtilisateurMapper.toEntity(utilisateur)
        );

        // THEN
        assertEquals("Pseudo, motDePasse and email cannot be empty", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNotManagedInToEntity() {
        // GIVEN
        Utilisateur utilisateur = new Utilisateur() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getPseudo() {
                return "PseudoTest";
            }

            @Override
            public String getMotDePasse() {
                return "mdpTest";
            }

            @Override
            public String getEmail() {
                return "test@exemple.com";
            }
        };

        // WHEN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> UtilisateurMapper.toEntity(utilisateur)
        );

        // Debug pour voir le message exact
        System.out.println("Exception message: " + thrown.getMessage());

        // THEN
        assertNotNull(thrown.getMessage()); // Vérifie que le message n'est pas null
        assertFalse(thrown.getMessage().isBlank()); // Vérifie qu'il y a bien un message
        assertTrue(thrown.getMessage().startsWith("Type d'utilisateur non géré")); // Vérifie que ça commence bien par le message attendu
    }

}
