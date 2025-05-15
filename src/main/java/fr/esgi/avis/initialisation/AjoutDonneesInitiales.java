package fr.esgi.avis.initialisation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.model.ModerateurDataSourcePort;
import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Profile({"DEV", "PROD"})
@Transactional(readOnly = true)
public class AjoutDonneesInitiales {

    private static final String PS5 = "PlayStation 5";
    private static final String AUCUNE = "AUCUNE";

    private EditeurDataSourcePort editeurDataSourcePort;
    private ClassificationDataSourcePort classificationDataSourcePort;
    private GenreDataSourcePort genreDataSourcePort;
    private PlateformeDataSourcePort plateformeDataSourcePort;
    private JeuDataSourcePort jeuDataSourcePort;
    private JoueurDataSourcePort joueurDataSourcePort;
    private ModerateurDataSourcePort moderateurDataSourcePort;
    private AvisDataSourcePort avisDataSourcePort;
    private AvatarDataSourcePort avatarDataSourcePort;

    @PersistenceContext
    private EntityManager entityManager;

    private static Faker faker = new Faker(Locale.FRENCH);

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        ajouterClassifications();
        ajouterGenres();
        ajouterPlateformes();
        ajouterEditeurs();
        ajouterAvatars();
        ajouterJoueurs(100);
        ajouterModerateurs();
        ajouterJeux();
        ajouterAvis(200);
        afficherStatistiques();
    }

    private void ajouterAvatars() {
        if (avatarDataSourcePort.count() == 0) {
            avatarDataSourcePort.save(new Avatar("Avatar 1"));
            avatarDataSourcePort.save(new Avatar("Avatar 2"));
            avatarDataSourcePort.save(new Avatar("Avatar 3"));
            avatarDataSourcePort.save(new Avatar("Avatar 4"));
            avatarDataSourcePort.save(new Avatar("Avatar 5"));
        }
    }

    private void ajouterAvis(int nbAvisAAjouter) {
        if (avisDataSourcePort.count() == 0) {
            Random random = new Random();
            List<Joueur> joueurs = joueurDataSourcePort.findAll();
            List<Jeu> jeux = jeuDataSourcePort.findAll();
            List<Moderateur> moderateurs = moderateurDataSourcePort.findAll();

            for (int i = 0; i < nbAvisAAjouter; i++) {
                Joueur joueur = joueurs.get(random.nextInt(joueurs.size()));
                Jeu jeu = jeux.get(random.nextInt(jeux.size()));
                Moderateur moderateur = moderateurs.get(random.nextInt(moderateurs.size()));
                Avis avis = new Avis(
                    null,
                    faker.lorem().paragraph(),
                    jeu.getId(),
                    joueur.getId(),
                    random.nextFloat(21),
                    LocalDateTime.now(),
                    moderateur.getId()
                );
                avisDataSourcePort.save(avis);
            }
        }
    }

    private void ajouterJoueurs(int nbJoueursAAjouter) {
        if (joueurDataSourcePort.count() == 0) {
            Random random = new Random();
            Calendar calendar = Calendar.getInstance();

            for (int i = 0; i < nbJoueursAAjouter; i++) {
                calendar.set(1940, 1, 1);
                Date dateDebut = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.set(2003, 1, 1);
                Date dateFin = calendar.getTime();
                Date dateAleatoire = faker.date().between(dateDebut, dateFin);
                LocalDate dateDeNaissance = dateAleatoire.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                String prenom = faker.name().firstName();
                String email = prenom + "." + faker.name().lastName().replaceAll(" ", "") + "@soprasteria.com";

                Joueur joueur = Joueur.builder()
                    .pseudo(prenom + String.valueOf(random.nextInt(999) + 1000))
                    .email(email)
                    .motDePasse(String.valueOf(random.nextInt(99999999) + 10000000))
                    .dateDeNaissance(dateDeNaissance)
                    .build();

                joueurDataSourcePort.save(joueur);
            }

            // Ajout d'un compte test
            joueurDataSourcePort.save(Joueur.builder()
                .pseudo("test")
                .motDePasse("anniversaire")
                .email("test@m2iformation.fr")
                .dateDeNaissance(LocalDate.of(1999, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()))
                .build());
        }
    }

    private void ajouterModerateurs() {
        if (moderateurDataSourcePort.count() == 0) {
            moderateurDataSourcePort.save(new Moderateur("Peppe", "azerty", "peppe@spiagge.it", "+39123456789"));
            moderateurDataSourcePort.save(new Moderateur("Admin", "admin123", "admin@avis.fr", "+33123456789"));
            moderateurDataSourcePort.save(new Moderateur("Modo", "modo123", "modo@avis.fr", "+33234567890"));
        }
    }

    @Transactional(readOnly = true)
    void afficherStatistiques() {
        System.out.println("Statistiques de la base de données :");
        System.out.println("Nombre d'éditeurs : " + editeurDataSourcePort.count());
        System.out.println("Nombre de jeux : " + jeuDataSourcePort.count());
        System.out.println("Nombre de joueurs : " + joueurDataSourcePort.count());
        System.out.println("Nombre d'avis : " + avisDataSourcePort.count());
        System.out.println("Nombre de modérateurs : " + moderateurDataSourcePort.count());
    }

    private void ajouterJeux() {
        if (jeuDataSourcePort.count() == 0) {
            // Ajout d'autres jeux...
            jeuDataSourcePort.save(new Jeu(
                "Zelda Tears of the Kingdom",
                editeurDataSourcePort.findByNom("Nintendo").orElseThrow(),
                genreDataSourcePort.findByNom("Action").orElseThrow(),
                classificationDataSourcePort.findByNom("PEGI 12").orElseThrow(),
                "L'intrigue se déroule dans le Royaume d'Hyrule, dévasté par le Cataclysme, phénomène déclenché par le réveil du roi démon, Ganondorf. Le monde est séparé en trois parties : la Surface (ou la Terre), où se déroule la majeure partie de l'aventure ; le Ciel, parsemé de nombreuses îles célestes, que l'on peut atteindre en se projetant dans les airs depuis les tours de reconnaissance (lesquelles donnent accès à la carte de la région) ; et enfin les Profondeurs, que l'on peut atteindre en sautant dans des Abîmes, apparus après le Cataclysme.",
                LocalDate.of(2023, 5, 12),
                Arrays.asList(plateformeDataSourcePort.findByNom("Nintendo Switch").orElseThrow()),
                "doc/assets/zelda.jpg",
                59.99f
            ));
            jeuDataSourcePort.save(new Jeu(
                "Assassin's Creed Valhalla",
                editeurDataSourcePort.findByNom("Ubisoft").orElseThrow(),
                genreDataSourcePort.findByNom("Action").orElseThrow(),
                classificationDataSourcePort.findByNom("PEGI 18").orElseThrow(),
                "Incarnez Eivor, Viking dont l'éducation a reposé sur le combat, et menez votre clan des terres désolées et glacées de Norvège à celles verdoyantes de l'Angleterre du IXe siècle. Fondez-y la colonie de votre peuple et partez à la conquête de territoires hostiles afin de gagner votre place au Valhalla.",
                LocalDate.of(2020, 11, 10),
                Arrays.asList(plateformeDataSourcePort.findByNom(PS5).orElseThrow()),
                "doc/assets/ACV.jpg", 59.99f));
            jeuDataSourcePort.save(new Jeu(
                "League Of Legends",
                editeurDataSourcePort.findByNom("Riot Games").orElseThrow(),
                genreDataSourcePort.findByNom("MOBA (Multiplayer online battle arena)").orElseThrow(),
                classificationDataSourcePort.findByNom(AUCUNE).orElseThrow(),
                "League of Legends est un jeu de stratégie en temps réel où deux équipes de cinq espèces mythiques s'affrontent pour détruire la base de l'opposant.",
                LocalDate.of(2009, 10, 27),
                Arrays.asList(plateformeDataSourcePort.findByNom("PC").orElseThrow()),
                "doc/assets/lol.jpg", 0f));
            jeuDataSourcePort.save(new Jeu(
                "Dofus",
                editeurDataSourcePort.findByNom("Ankama").orElseThrow(),
                genreDataSourcePort.findByNom("RPG (Role-playing game))").orElseThrow(),
                classificationDataSourcePort.findByNom(AUCUNE).orElseThrow(),
                "Dofus est un MMORPG en 2D, avec un gameplay simple et accessible, mais qui offre une grande profondeur et des possibilités de personnalisation infinies.",
                LocalDate.of(2004, 9, 1),
                Arrays.asList(plateformeDataSourcePort.findByNom("PC").orElseThrow()),
                "doc/assets/dofus.jpg", 0f));
            jeuDataSourcePort.save(new Jeu(
                "The Witcher 3: Wild Hunt",
                editeurDataSourcePort.findByNom("CD Projekt Red").orElseThrow(),
                genreDataSourcePort.findByNom("RPG (Role-playing game))").orElseThrow(),
                classificationDataSourcePort.findByNom("PEGI 18").orElseThrow(),
                "The Witcher 3: Wild Hunt est un RPG en 3D, avec un gameplay simple et accessible, mais qui offre une grande profondeur et des possibilités de personnalisation infinies.",
                LocalDate.of(2015, 5, 24),
                Arrays.asList(plateformeDataSourcePort.findByNom("PC").orElseThrow()),
                "doc/assets/TW3.jpg", 59.99f));
            jeuDataSourcePort.save(new Jeu(
                "Elden Ring",
                editeurDataSourcePort.findByNom("FromSoftware").orElseThrow(),
                genreDataSourcePort.findByNom("RPG (Role-playing game))").orElseThrow(),
                classificationDataSourcePort.findByNom("PEGI 18").orElseThrow(),
                "Elden Ring est un RPG en 3D, avec un gameplay simple et accessible, mais qui offre une grande profondeur et des possibilités de personnalisation infinies.",
                LocalDate.of(2022, 2, 25),
                Arrays.asList(plateformeDataSourcePort.findByNom("PC").orElseThrow()),
                "doc/assets/eldenring.jpg", 59.99f));
            jeuDataSourcePort.save(new Jeu(
                "Clair Obscure : Expedition 33",
                editeurDataSourcePort.findByNom("Hazelight Studios").orElseThrow(),
                genreDataSourcePort.findByNom("Action").orElseThrow(),
                classificationDataSourcePort.findByNom("PEGI 12").orElseThrow(),
                "Une fois par an, la Peintresse se réveille. Sur son Monolithe, elle peint son nombre maudit.Et tous ceux de cet âge partent en fumée. Année après année, ce nombre diminue et nous sommes toujours plus nombreux à être effacés. Demain, elle se réveillera pour peindre « 33 ». Et demain, nous partirons pour notre ultime mission : éliminer la Peintresse, pour que plus jamais elle ne peigne la mort. Clair Obscur: Expedition 33 est un RPG au tour par tour révolutionnaire qui intègre des mécaniques de jeu en temps réel, pour rendre les combats plus immersifs et addictifs que jamais. Explorez un monde fantastique rappelant la France de la Belle Époque et peuplé d'ennemis dévastateurs. Nous sommes l'Expédition 33.",
                LocalDate.of(2025, 4, 24),
                Arrays.asList(plateformeDataSourcePort.findByNom("PC").orElseThrow(), plateformeDataSourcePort.findByNom(PS5).orElseThrow(), plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow()),
                "doc/assets/expedition33.jpg", 49.99f));
        }
    }

    private void ajouterPlateformes() {
        if (plateformeDataSourcePort.count() == 0) {
            plateformeDataSourcePort.save(new Plateforme("Nintendo Switch"));
            plateformeDataSourcePort.save(new Plateforme(PS5));
            plateformeDataSourcePort.save(new Plateforme("Xbox Series X"));
            plateformeDataSourcePort.save(new Plateforme("PC"));
            plateformeDataSourcePort.save(new Plateforme("Mobile"));
            plateformeDataSourcePort.save(new Plateforme("Nintendo Wii"));
            plateformeDataSourcePort.save(new Plateforme("PlayStation 4"));
            plateformeDataSourcePort.save(new Plateforme("Xbox One"));
        }
    }

    private void ajouterGenres() {
        if (genreDataSourcePort.count() == 0) {
            genreDataSourcePort.save(new Genre("Action"));
            genreDataSourcePort.save(new Genre("Aventure"));
            genreDataSourcePort.save(new Genre("RPG (Role-playing game))"));
            genreDataSourcePort.save(new Genre("Stratégie"));
            genreDataSourcePort.save(new Genre("Sport"));
            genreDataSourcePort.save(new Genre("Course"));
            genreDataSourcePort.save(new Genre("MOBA (Multiplayer online battle arena)"));
            genreDataSourcePort.save(new Genre("FPS (First person shooter)"));
            genreDataSourcePort.save(new Genre("MMORPG"));
            genreDataSourcePort.save(new Genre("Simulation"));
        }
    }

    private void ajouterClassifications() {
        if (classificationDataSourcePort.count() == 0) {
            classificationDataSourcePort.save(new Classification("PEGI 3", "0000FF"));
            classificationDataSourcePort.save(new Classification("PEGI 7", "00FF00"));
            classificationDataSourcePort.save(new Classification("PEGI 12", "FF0000"));
            classificationDataSourcePort.save(new Classification("PEGI 16", "FFFF00"));
            classificationDataSourcePort.save(new Classification("PEGI 18", "00FFFF"));
            classificationDataSourcePort.save(new Classification(AUCUNE, "FFFFFF"));
        }
    }

    private void ajouterEditeurs() {
        if (editeurDataSourcePort.count() == 0) {
            editeurDataSourcePort.save(new Editeur("Nintendo"));
            editeurDataSourcePort.save(new Editeur("Sony Interactive Entertainment"));
            editeurDataSourcePort.save(new Editeur("Microsoft"));
            editeurDataSourcePort.save(new Editeur("Ubisoft"));
            editeurDataSourcePort.save(new Editeur("Electronic Arts"));
            editeurDataSourcePort.save(new Editeur("Activision Blizzard"));
            editeurDataSourcePort.save(new Editeur("Riot Games"));
            editeurDataSourcePort.save(new Editeur("Ankama"));
            editeurDataSourcePort.save(new Editeur("BioWare"));
            editeurDataSourcePort.save(new Editeur("CD Projekt Red"));
            editeurDataSourcePort.save(new Editeur("FromSoftware"));
            editeurDataSourcePort.save(new Editeur("Naughty Dog"));
            editeurDataSourcePort.save(new Editeur("Hazelight Studios"));
            editeurDataSourcePort.save(new Editeur("idSoftware"));
        }
    }
}
