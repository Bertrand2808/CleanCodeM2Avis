package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Avis.model.AvisEntity;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvisJpaAdapterTest {

    @Mock
    private AvisJpaRepository avisJpaRepository;

    @InjectMocks
    private AvisJpaAdapter avisJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Random random = new Random();
    private LocalDateTime now = LocalDateTime.now();

    @Test
    void shouldSaveAvisSuccessfully() {
        // GIVEN
        Long avisId = random.nextLong(100);
        Joueur joueur = createJoueur();
        Avis avis = new Avis(
                avisId,
                "Super jeu",
                10L,
                joueur.getId(),
                5.0f,
                now,
                99L
        );


        AvisEntity avisEntity = AvisMapper.toEntity(avis);
        when(avisJpaRepository.save(any(AvisEntity.class))).thenReturn(avisEntity);

        // WHEN
        Avis savedAvis = avisJpaAdapter.save(avis);

        // THEN
        assertNotNull(savedAvis);
        assertEquals(avis.getDescription(), savedAvis.getDescription());
        assertEquals(avis.getNote(), savedAvis.getNote());
        verify(avisJpaRepository, times(1)).save(any(AvisEntity.class));
    }

    @Test
    void shouldFindAvisByIdSuccessfully() {
        // GIVEN
        Long avisId = random.nextLong(100);
        Joueur joueur = createJoueur();
        Avis avis = new Avis(
                avisId,
                "Super jeu",
                10L,
                joueur.getId(),
                5.0f,
                now,
                99L
        );


        AvisEntity avisEntity = AvisMapper.toEntity(avis);
        when(avisJpaRepository.findById(avisId)).thenReturn(Optional.of(avisEntity));

        // WHEN
        Optional<Avis> foundAvis = avisJpaAdapter.findById(avisId);

        // THEN
        assertTrue(foundAvis.isPresent());
        assertEquals(avis.getDescription(), foundAvis.get().getDescription());
        assertEquals(avis.getNote(), foundAvis.get().getNote());
        verify(avisJpaRepository, times(1)).findById(avisId);
    }

    @Test
    void shouldReturnEmptyWhenAvisNotFound() {
        // GIVEN
        Long avisId = random.nextLong(100);
        when(avisJpaRepository.findById(avisId)).thenReturn(Optional.empty());

        // WHEN
        Optional<Avis> foundAvis = avisJpaAdapter.findById(avisId);

        // THEN
        assertFalse(foundAvis.isPresent());
        verify(avisJpaRepository, times(1)).findById(avisId);
    }

    @Test
    void shouldDeleteAvisSuccessfully() {
        // GIVEN
        Long avisId = random.nextLong(100);
        doNothing().when(avisJpaRepository).deleteById(avisId);

        // WHEN
        avisJpaAdapter.deleteById(avisId);

        // THEN
        verify(avisJpaRepository, times(1)).deleteById(avisId);
    }

    private Joueur createJoueur() {
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        List<Avis> avis = new ArrayList<>();

        Joueur joueur = Joueur.builder()
                .id(1L)
                .pseudo("PseudoTest")
                .motDePasse("mdpTest")
                .email("test@example.com")
                .dateDeNaissance(birthdate)
                .avis(avis)
                .build();

        Avatar avatar = new Avatar(
                1L,
                "Warrior",
                joueur.getId()
        );

        joueur.setAvatar(avatar);

        return joueur;
    }

    @Test
    void shouldFindAvisByJeuIdSuccessfully() {
        // GIVEN
        Long jeuId = random.nextLong(100);
        Joueur joueur = createJoueur();
        Avis avis = new Avis(
                1L,
                "Super jeu",
                10L,
                joueur.getId(),
                5.0f,
                now,
                99L
        );

        AvisEntity avisEntity = AvisMapper.toEntity(avis);
        when(avisJpaRepository.findByJeu_Id(jeuId)).thenReturn(List.of(avisEntity));

        // WHEN
        List<Avis> foundAvis = avisJpaAdapter.findByJeuId(jeuId);

        // THEN
        assertNotNull(foundAvis);
        assertEquals(1, foundAvis.size());
        assertEquals(avis.getDescription(), foundAvis.get(0).getDescription());
        assertEquals(avis.getNote(), foundAvis.get(0).getNote());
        verify(avisJpaRepository, times(1)).findByJeu_Id(jeuId);
    }

    @Test
    void shouldReturnEmptyListWhenNoAvisFound() {
        // GIVEN
        Long jeuId = random.nextLong(100);
        when(avisJpaRepository.findByJeu_Id(jeuId)).thenReturn(Collections.emptyList());

        // WHEN
        List<Avis> foundAvis = avisJpaAdapter.findByJeuId(jeuId);

        // THEN
        assertTrue(foundAvis.isEmpty());
        verify(avisJpaRepository, times(1)).findByJeu_Id(jeuId);
    }
}
