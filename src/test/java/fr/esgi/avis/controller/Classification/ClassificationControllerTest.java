package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Classification.ClassificationUseCases;
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

class ClassificationControllerTest {

    @Mock
    private ClassificationUseCases classificationUseCases;

    @InjectMocks
    private ClassificationController classificationController;

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
        classification.setId(100L);
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
        fakeJeu.setId(10L);
        fakeJeu.setNom("Far Cry");
        fakeJeu.setEditeur(editeur);
        fakeJeu.setGenre(genre);
        fakeJeu.setClassification(classification);
        fakeJeu.setPlateformes(fakePlateformes);
    }

    @Test
    void shouldCreateClassificationSuccessfully() {
        // GIVEN
        JeuDTO fakeJeuDTO = new JeuDTO();
        fakeJeuDTO.setId(10L);
        fakeJeuDTO.setNom("Far Cry");

        Editeur editeur = new Editeur();
        editeur.setId(1L);
        editeur.setNom("Ubisoft");
        fakeJeuDTO.setEditeur(editeur);

        fakeJeuDTO.setGenre(new Genre());
        fakeJeuDTO.setClassification(new Classification());
        fakeJeuDTO.setPlateformes(fakePlateformeDTOs);

        ClassificationDTO dto = new ClassificationDTO();
        dto.setNom("PEGI 16");
        dto.setCouleurRGB("#AAAAAA");
        dto.setJeux(List.of(fakeJeuDTO));

        Classification created = new Classification();
        created.setId(1L);
        created.setNom("PEGI 16");
        created.setCouleurRGB("#AAAAAA");
        created.setJeux(List.of(fakeJeu));

        when(classificationUseCases.createClassification(any(Classification.class))).thenReturn(created);

        // WHEN
        ClassificationDTO result = classificationController.createClassification(dto);

        // THEN
        assertNotNull(result);
        assertEquals(created.getId(), result.getId());
        assertEquals(created.getNom(), result.getNom());
        assertEquals(created.getCouleurRGB(), result.getCouleurRGB());
        verify(classificationUseCases, times(1)).createClassification(any(Classification.class));
    }

    @Test
    void shouldGetAllClassifications() {
        // GIVEN
        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 3");
        classification.setCouleurRGB("#00FF00");
        classification.setJeux(List.of(fakeJeu));

        when(classificationUseCases.getClassifications()).thenReturn(List.of(classification));

        // WHEN
        List<ClassificationDTO> result = classificationController.getClassifications();

        // THEN
        assertEquals(1, result.size());
        assertEquals("PEGI 3", result.get(0).getNom());
        verify(classificationUseCases, times(1)).getClassifications();
    }

    @Test
    void shouldGetClassificationByIdWhenExists() {
        // GIVEN
        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNom("PEGI 7");
        classification.setCouleurRGB("#0000FF");
        classification.setJeux(List.of(fakeJeu));

        when(classificationUseCases.getClassificationById(1L)).thenReturn(Optional.of(classification));

        // WHEN
        Optional<ClassificationDTO> result = classificationController.getClassificationById(1L);

        // THEN
        assertTrue(result.isPresent());
        assertEquals("PEGI 7", result.get().getNom());
        verify(classificationUseCases, times(1)).getClassificationById(1L);
    }

    @Test
    void shouldReturnEmptyWhenClassificationNotFoundById() {
        // GIVEN
        when(classificationUseCases.getClassificationById(99L)).thenReturn(Optional.empty());

        // WHEN
        Optional<ClassificationDTO> result = classificationController.getClassificationById(99L);

        // THEN
        assertTrue(result.isEmpty());
        verify(classificationUseCases, times(1)).getClassificationById(99L);
    }

    @Test
    void shouldGetClassificationByNomWhenExists() {
        // GIVEN
        Classification classification = new Classification();
        classification.setId(2L);
        classification.setNom("PEGI 18");
        classification.setCouleurRGB("#FF0000");
        classification.setJeux(List.of(fakeJeu));

        when(classificationUseCases.getClassificationByNom("PEGI 18")).thenReturn(Optional.of(classification));

        // WHEN
        Optional<ClassificationDTO> result = classificationController.getClassificationByNom("PEGI 18");

        // THEN
        assertTrue(result.isPresent());
        assertEquals("PEGI 18", result.get().getNom());
        verify(classificationUseCases, times(1)).getClassificationByNom("PEGI 18");
    }

    @Test
    void shouldReturnEmptyWhenClassificationNotFoundByNom() {
        // GIVEN
        when(classificationUseCases.getClassificationByNom("Unknown")).thenReturn(Optional.empty());

        // WHEN
        Optional<ClassificationDTO> result = classificationController.getClassificationByNom("Unknown");

        // THEN
        assertTrue(result.isEmpty());
        verify(classificationUseCases, times(1)).getClassificationByNom("Unknown");
    }

    @Test
    void shouldGetClassificationsByNomContaining() {
        // GIVEN
        Classification classification = new Classification();
        classification.setId(3L);
        classification.setNom("PEGI 12");
        classification.setCouleurRGB("#CCCCCC");
        classification.setJeux(List.of(fakeJeu));

        when(classificationUseCases.getClassificationsByNomContaining("PEGI"))
                .thenReturn(List.of(classification));

        // WHEN
        List<ClassificationDTO> result = classificationController.getClassificationsByNomContaining("PEGI");

        // THEN
        assertEquals(1, result.size());
        assertEquals("PEGI 12", result.get(0).getNom());
        verify(classificationUseCases, times(1)).getClassificationsByNomContaining("PEGI");
    }

    @Test
    void shouldDeleteClassificationSuccessfully() {
        // GIVEN
        Long idToDelete = 1L;
        doNothing().when(classificationUseCases).deleteClassificationById(idToDelete);

        // WHEN
        classificationController.deleteClassificationById(idToDelete);

        // THEN
        verify(classificationUseCases, times(1)).deleteClassificationById(idToDelete);
    }
}
