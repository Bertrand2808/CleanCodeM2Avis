package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModerateurMapperTest {

    @Test
    void shouldConvertEntityToDomainSuccessfully() {
        // GIVEN
        ModerateurEntity moderateurEntity = ModerateurEntity.builder()
                .id(1L)
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();

        // WHEN
        Moderateur moderateur = ModerateurMapper.toDomain(moderateurEntity);

        // THEN
        assertNotNull(moderateur);
        assertEquals(moderateurEntity.getId(), moderateur.getId());
        assertEquals(moderateurEntity.getPseudo(), moderateur.getPseudo());
        assertEquals(moderateurEntity.getMotDePasse(), moderateur.getMotDePasse());
        assertEquals(moderateurEntity.getEmail(), moderateur.getEmail());
        assertEquals(moderateurEntity.getNumeroDeTelephone(), moderateur.getNumeroDeTelephone());
    }

    @Test
    void shouldConvertDomainToEntitySuccessfully() {
        // GIVEN
        Moderateur moderateur = Moderateur.builder()
                .id(1L)
                .pseudo("modAdmin")
                .motDePasse("password123")
                .email("admin@example.com")
                .numeroDeTelephone("+33123456789")
                .build();

        // WHEN
        ModerateurEntity moderateurEntity = ModerateurMapper.toEntity(moderateur);

        // THEN
        assertNotNull(moderateurEntity);
        assertEquals(moderateur.getId(), moderateurEntity.getId());
        assertEquals(moderateur.getPseudo(), moderateurEntity.getPseudo());
        assertEquals(moderateur.getMotDePasse(), moderateurEntity.getMotDePasse());
        assertEquals(moderateur.getEmail(), moderateurEntity.getEmail());
        assertEquals(moderateur.getNumeroDeTelephone(), moderateurEntity.getNumeroDeTelephone());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // WHEN
        Moderateur result = ModerateurMapper.toDomain(null);

        // THEN
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenDomainIsNull() {
        // WHEN
        ModerateurEntity result = ModerateurMapper.toEntity(null);

        // THEN
        assertNull(result);
    }
}
