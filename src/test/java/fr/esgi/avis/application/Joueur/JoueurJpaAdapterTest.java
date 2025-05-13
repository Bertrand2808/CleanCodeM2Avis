package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JoueurJpaAdapterTest {

    @Mock
    private JoueurJpaRepository joueurJpaRepository;

    @InjectMocks
    private JoueurJpaAdapter joueurJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveJoueurSuccessfully() {
        Joueur joueur = Joueur.builder()
                .id(1L)
                .pseudo("Altair")
                .email("altair@ac.com")
                .motDePasse("creed123")
                .dateDeNaissance(LocalDate.of(1985, 3, 15))
                .avis(Collections.emptyList())
                .avatar(new Avatar(1L, "Viking", 1L))
                .build();

        JoueurEntity entity = JoueurMapper.toEntity(joueur);

        when(joueurJpaRepository.save(any())).thenReturn(entity);

        Joueur saved = joueurJpaAdapter.save(joueur);

        assertNotNull(saved);
        assertEquals("Altair", saved.getPseudo());
        verify(joueurJpaRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAllJoueurs() {
        when(joueurJpaRepository.findAll()).thenReturn(List.of(JoueurMapper.toEntity(
                Joueur.builder()
                        .id(1L)
                        .pseudo("Ezio")
                        .email("ezio@ac.com")
                        .motDePasse("hiddenBlade")
                        .dateDeNaissance(LocalDate.of(1990, 1, 1))
                        .avis(Collections.emptyList())
                        .avatar(new Avatar(1L, "Knight", 1L))
                        .build()
        )));

        List<Joueur> result = joueurJpaAdapter.findAll();

        assertEquals(1, result.size());
        assertEquals("Ezio", result.get(0).getPseudo());
    }

    @Test
    void shouldFindByPseudo() {
        JoueurEntity entity = JoueurEntity.builder()
                .id(1L)
                .pseudo("Altair")
                .email("altair@ac.com")
                .motDePasse("creed123")
                .dateDeNaissance(LocalDate.of(1985, 3, 15))
                .avatar(new AvatarEntity(1L, "Viking", 1L))
                .avis(Collections.emptyList())
                .build();

        when(joueurJpaRepository.findByPseudo("Altair")).thenReturn(entity);

        Optional<Joueur> found = joueurJpaAdapter.findByPseudo("Altair");

        assertTrue(found.isPresent());
        assertEquals("Altair", found.get().getPseudo());
    }

    @Test
    void shouldFindByDateDeNaissance() {
        LocalDate birthDate = LocalDate.of(1985, 3, 15);
        JoueurEntity entity = JoueurEntity.builder()
                .id(1L)
                .pseudo("Altair")
                .email("altair@ac.com")
                .motDePasse("creed123")
                .dateDeNaissance(birthDate)
                .avatar(new AvatarEntity(1L, "Viking", 1L))
                .avis(Collections.emptyList())
                .build();

        when(joueurJpaRepository.findByDateDeNaissance(birthDate)).thenReturn(Optional.of(entity));

        Optional<Joueur> found = joueurJpaAdapter.findByDateDeNaissance(birthDate);

        assertTrue(found.isPresent());
        assertEquals("Altair", found.get().getPseudo());
    }

    @Test
    void shouldDeleteByPseudo() {
        joueurJpaAdapter.deleteByPseudo("Altair");
        verify(joueurJpaRepository, times(1)).deleteByPseudo("Altair");
    }

    @Test
    void shouldCountJoueurs() {
        when(joueurJpaRepository.count()).thenReturn(5L);

        long count = joueurJpaAdapter.count();

        assertEquals(5L, count);
    }
}
