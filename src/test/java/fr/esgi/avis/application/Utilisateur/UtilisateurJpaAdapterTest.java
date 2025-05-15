package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UtilisateurJpaAdapterTest {

    @Mock
    private UtilisateurJpaRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurJpaAdapter utilisateurJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveUtilisateurSuccessfully() {
        // GIVEN
        Utilisateur utilisateur = createUtilisateur();
        UtilisateurEntity utilisateurEntity = createUtilisateurEntity();
        when(utilisateurRepository.save(any(UtilisateurEntity.class))).thenReturn(utilisateurEntity);

        // WHEN
        Utilisateur savedUtilisateur = utilisateurJpaAdapter.save(utilisateur);

        // THEN
        assertNotNull(savedUtilisateur);
        assertEquals(utilisateur.getPseudo(), savedUtilisateur.getPseudo());
        verify(utilisateurRepository, times(1)).save(any(UtilisateurEntity.class));
    }

    @Test
    void shouldFindUtilisateurByPseudoWhenExists() {
        // GIVEN
        String pseudo = "PseudoTest";
        UtilisateurEntity utilisateurEntity = createUtilisateurEntity();
        when(utilisateurRepository.findByPseudo(pseudo)).thenReturn(utilisateurEntity);

        // WHEN
        Utilisateur foundUtilisateur = utilisateurJpaAdapter.findByPseudo(pseudo).get();

        // THEN
        assertNotNull(foundUtilisateur);
        assertEquals(pseudo, foundUtilisateur.getPseudo());
        verify(utilisateurRepository, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldFindUtilisateurByEmailWhenExists() {
        // GIVEN
        String email = "test@exemple.com";
        UtilisateurEntity utilisateurEntity = createUtilisateurEntity();
        when(utilisateurRepository.findByEmail(email)).thenReturn(utilisateurEntity);

        // WHEN
        Optional<Utilisateur> foundUtilisateur = Optional.of(utilisateurJpaAdapter.findByEmail(email).get());

        // THEN
        assertNotNull(foundUtilisateur);
        assertEquals(email, foundUtilisateur.get().getEmail());
        verify(utilisateurRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldReturnEmptyWhenUtilisateurNotFound() {
        // GIVEN
        String pseudo = "PseudoTest";
        when(utilisateurRepository.findByPseudo(pseudo)).thenReturn(null);

        // WHEN
        Optional<Utilisateur> foundUtilisateur = utilisateurJpaAdapter.findByPseudo(pseudo);

        // THEN
        assertFalse(foundUtilisateur.isPresent());
        verify(utilisateurRepository, times(1)).findByPseudo(pseudo);
    }

    @Test
    void shouldDeleteUtilisateurByPseudoSuccessfully() {
        // GIVEN
        String pseudo = "PseudoTest";
        doNothing().when(utilisateurRepository).deleteByPseudo(pseudo);

        // WHEN
        utilisateurJpaAdapter.deleteByPseudo(pseudo);

        // THEN
        verify(utilisateurRepository, times(1)).deleteByPseudo(pseudo);
    }

    @Test
    void shouldFindUtilisateurByIdWhenExists() {
        // GIVEN
        Long id = 1L;
        UtilisateurEntity utilisateurEntity = createUtilisateurEntity();
        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(utilisateurEntity));

        // WHEN
        Optional<Utilisateur> foundUtilisateur = utilisateurJpaAdapter.findById(id);

        // THEN
        assertTrue(foundUtilisateur.isPresent());
        assertEquals(id, foundUtilisateur.get().getId());
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmptyWhenUtilisateurNotFoundById() {
        // GIVEN
        Long id = 1L;
        when(utilisateurRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Optional<Utilisateur> foundUtilisateur = utilisateurJpaAdapter.findById(id);

        // THEN
        assertFalse(foundUtilisateur.isPresent());
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void shouldDeleteUtilisateurByIdSuccessfully() {
        // GIVEN
        Long id = 1L;
        doNothing().when(utilisateurRepository).deleteById(id);

        // WHEN
        utilisateurJpaAdapter.deleteById(id);

        // THEN
        verify(utilisateurRepository, times(1)).deleteById(id);
    }


    private UtilisateurEntity createUtilisateurEntity() {
        UtilisateurEntity utilisateurEntity = JoueurEntity.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
        return utilisateurEntity;
    }

    private Utilisateur createUtilisateur() {
        Utilisateur utilisateur = Joueur.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@exemple.com")
                .dateDeNaissance(LocalDate.of(1990, 1, 1))
                .build();
        return utilisateur;
    }



}