package fr.esgi.avis.useCases.Avis;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AvisUseCasesTest {
    
    @Mock
    private AvisDataSourcePort avisDataSourcePort;
    
    @InjectMocks
    private AvisUseCases avisUseCases;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void shouldCreateAvisSuccessfully() {
        // GIVEN
        Avis avis = createAvis();
        when(avisDataSourcePort.save(any(Avis.class))).thenReturn(avis);

        // WHEN
        Avis createdAvis = avisUseCases.createAvis(avis);

        // THEN
        assertNotNull(createdAvis);
        assertEquals(avis.getId(), createdAvis.getId());
        assertEquals(avis.getDescription(), createdAvis.getDescription());
        assertEquals(avis.getJoueurId(), createdAvis.getJoueurId());
        assertEquals(avis.getNote(), createdAvis.getNote());
        assertEquals(avis.getDateDEnvoi(), createdAvis.getDateDEnvoi());

    }

    @Test
    void shouldGetAvisByIdWhenExists() {
        // GIVEN
        Long avisId = 1L;
        Avis avis = createAvis();
        avis.setId(avisId);
        when(avisDataSourcePort.findById(avisId)).thenReturn(Optional.of(avis));

        // WHEN
        Optional<Avis> foundAvis = avisUseCases.getAvisById(avisId);

        // THEN
        assertNotNull(foundAvis);
        assertEquals(avisId, foundAvis.map(Avis::getId).orElse(null)); // foundAvis.map(Avis::getId) returns an Optional<Long>
        assertEquals(avis.getDescription(), foundAvis.map(Avis::getDescription).orElse(null));
        assertEquals(avis.getJoueurId(), foundAvis.map(Avis::getJoueurId).orElse(null));
        assertEquals(avis.getNote(), foundAvis.map(Avis::getNote).orElse(null));
    }

    @Test
    void shouldReturnEmptyWhenAvisNotFound() {
        // GIVEN
        Long avisId = 1L;
        when(avisDataSourcePort.findById(avisId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Avis> foundAvis = avisUseCases.getAvisById(avisId);

        // THEN
        assertTrue(foundAvis.isEmpty());
    }

    @Test
    void shouldDeleteAvisSuccessfully() {
        // GIVEN
        Long avisId = 1L;

        // WHEN
        avisUseCases.deleteAvis(avisId);

        // THEN
        // Verify that the deleteById method of the avisDataSourcePort is called once with the avisId as an argument
        verify(avisDataSourcePort, times(1)).deleteById(avisId);
    }


    
    private Avis createAvis() {
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
        Long joueurId = 1L;
        joueur.setId(joueurId);
        Avis newAvis = new Avis(
                1L,
                "Super jeu",
                10L,
                joueur.getId(),
                5.0f,
                LocalDateTime.now(),
                99L
        );
        avis.add(newAvis);
        joueur.setAvis(avis);

        return newAvis;
    }
}