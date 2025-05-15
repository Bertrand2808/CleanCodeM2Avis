package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JeuControllerTest {

    @Mock
    private JeuUseCases jeuUseCases;

    @InjectMocks
    private JeuController jeuController;

    private Jeu fakeJeu;
    private List<Plateforme> fakePlateformes;
    private List<PlateformeDTO> fakePlateformeDTOs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setNom("Action");

        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 18");
        classification.setCouleurRGB("#FF0000");

        Plateforme plateforme = new Plateforme();
        plateforme.setId(1L);
        plateforme.setNom("PlayStation");
        plateforme.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateforme.setJeux(new ArrayList<>());

        fakePlateformes = new ArrayList<>();
        fakePlateformes.add(plateforme);

        PlateformeDTO plateformeDTO = new PlateformeDTO();
        plateformeDTO.setNom("PlayStation");
        plateformeDTO.setDateDeSortie(LocalDate.of(2020, 11, 12));
        plateformeDTO.setJeux(new ArrayList<>());

        fakePlateformeDTOs = new ArrayList<>();
        fakePlateformeDTOs.add(plateformeDTO);

        fakeJeu = new Jeu();
        fakeJeu.setId(1L);
        fakeJeu.setNom("Far Cry");
        fakeJeu.setEditeur(editeur);
        fakeJeu.setGenre(genre);
        fakeJeu.setClassification(classification);
        fakeJeu.setPlateformes(fakePlateformes);
    }

    @Test
    void shouldCreateJeuSuccessfully() {
        JeuDTO jeuDTO = new JeuDTO();
        jeuDTO.setId(1L);
        jeuDTO.setNom("Far Cry");
        jeuDTO.setEditeur(fakeJeu.getEditeur());
        jeuDTO.setGenre(fakeJeu.getGenre());
        jeuDTO.setClassification(fakeJeu.getClassification());
        jeuDTO.setPlateformes(fakePlateformeDTOs);

        when(jeuUseCases.createJeu(any(Jeu.class))).thenReturn(fakeJeu);

        JeuDTO result = jeuController.createJeu(jeuDTO);

        assertNotNull(result);
        assertEquals(fakeJeu.getId(), result.getId());
        assertEquals(fakeJeu.getNom(), result.getNom());
        verify(jeuUseCases, times(1)).createJeu(any(Jeu.class));
    }

    @Test
    void shouldGetAllJeux() {
        when(jeuUseCases.getJeux()).thenReturn(List.of(fakeJeu));

        List<JeuDTO> result = jeuController.getJeux();

        assertEquals(1, result.size());
        assertEquals("Far Cry", result.get(0).getNom());
        verify(jeuUseCases, times(1)).getJeux();
    }

    @Test
    void shouldGetJeuById() {
        when(jeuUseCases.getJeuById(1L)).thenReturn(Optional.of(fakeJeu));

        Optional<JeuDTO> result = jeuController.getJeuById(1L);

        assertTrue(result.isPresent());
        assertEquals("Far Cry", result.get().getNom());
        verify(jeuUseCases, times(1)).getJeuById(1L);
    }

    @Test
    void shouldGetJeuByNom() {
        when(jeuUseCases.getJeuByNom("Far Cry")).thenReturn(Optional.of(fakeJeu));

        Optional<JeuDTO> result = jeuController.getJeuByNom("Far Cry");

        assertTrue(result.isPresent());
        assertEquals("Far Cry", result.get().getNom());
        verify(jeuUseCases, times(1)).getJeuByNom("Far Cry");
    }

    @Test
    void shouldGetJeuxByNomContaining() {
        when(jeuUseCases.getJeuxByNomContaining("Far")).thenReturn(List.of(fakeJeu));

        List<JeuDTO> result = jeuController.getJeuxByNomContaining("Far");

        assertEquals(1, result.size());
        assertEquals("Far Cry", result.get(0).getNom());
        verify(jeuUseCases, times(1)).getJeuxByNomContaining("Far");
    }

    @Test
    void shouldDeleteJeuById() {
        doNothing().when(jeuUseCases).deleteJeuById(1L);

        jeuController.deleteJeuById(1L);

        verify(jeuUseCases, times(1)).deleteJeuById(1L);
    }


}
