package fr.esgi.avis.initialisation;

import com.github.javafaker.Faker;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avatar.AvatarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@AllArgsConstructor
@Profile({"DEV", "PROD"})
@Transactional(readOnly = true)
public class AjoutDonneesInitiales {

    //private EditeurRepository editeurRepository;
    //private ClassificationRepository classificationRepository;
    //private GenreRepository genreRepository;
    //private PlateformeRepository plateformeRepository;
    //private JeuRepository jeuRepository;
    //private JoueurRepository joueurRepository;
    //private ModerateurRepository moderateurRepository;
    //private AvisRepository avisRepository;
    private AvatarRepository avatarRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // Le fait de déclarer l'attribut en static va dispenser Spring de gérer l'objet
    private static Faker faker = new Faker(Locale.FRENCH);

    // @Override
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    // public void run(String... args) throws Exception {
    public void init() {
        //ajouterEditeurs();
        //ajouterClassifications();
        //ajouterGenres();
        //ajouterPlateformes();
        //ajouterJeux();
        ajouterAvatars();
        //ajouterJoueurs(100);
        //ajouterModerateur();
        //ajouterAvis(200);
        //afficherStatistiques();
    }

    private void ajouterAvatars() {
        avatarRepository.save(new Avatar("Avatar 1"));
        avatarRepository.save(new Avatar("Avatar 2"));
    }

    /*private void ajouterAvis(int nbAvisAAjouter) {
        if (avisRepository.count() == 0) {
            Random random = new Random();
            List<Joueur> joueurs = joueurRepository.findAll();
            for (int i = 0; i < nbAvisAAjouter; i++) {
                Joueur joueur = joueurs.get(random.nextInt(joueurs.size()));
                Avis avis = new Avis(faker.letterify("????????"), jeuRepository.findGamesRandomlySorted().get(0), joueur);
                avis.setNote(random.nextFloat(21));
                joueur.getAvis().add(avis);
                avisRepository.save(avis);
            }
        }
    }*/

    /*private void ajouterJoueurs(int nbJoueursAAjouter) {
        if (joueurRepository.count() == 0) {
            Random random = new Random();
            Calendar calendar = Calendar.getInstance();
            Map<String, Joueur> map = new HashMap<>();
            int compteur = 0;
            while (compteur<nbJoueursAAjouter) {
                compteur++;
                calendar.set(1940, 1, 1);
                Date dateDebut = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.set(2003, 1, 1);
                Date dateFin = calendar.getTime();
                Date dateAleatoire = faker.date().between(dateDebut, dateFin);
                calendar.setTime(dateAleatoire);
                LocalDate dateDeNaissance = dateAleatoire.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String prenom = faker.name().firstName();
                String email = prenom + "." + faker.name().lastName().replaceAll(" ", "") + "@soprasteria.com";

                 * Sans Builder : Joueur joueur = new Joueur(); joueur.setPseudo(prenom +
                 * String.valueOf(random.nextInt(999) + 1000)); joueur.setEmail(email);
                 * joueur.setMotDePasse(String.valueOf(random.nextInt(99999999) + 10000000));
                 * joueur.setDateDeNaissance(dateDeNaissance);



                Joueur joueur = Joueur.builder().pseudo(prenom + String.valueOf(random.nextInt(999) + 1000))
                        .email(email).motDePasse(String.valueOf(random.nextInt(99999999) + 10000000))
                        .dateDeNaissance(dateDeNaissance).build();

                //map.put(joueur.getEmail(), joueur);
                joueurRepository.save(joueur);
            }
            //joueurRepository.saveAll(map.values());
            joueurRepository.save(Joueur.builder().pseudo("test").motDePasse("anniversaire")
                    .email("test@m2iformation.fr")
                    .dateDeNaissance(LocalDate.of(1999, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth())).build());
        }
    }

    private void ajouterModerateur() {
        moderateurRepository.save(new Moderateur("Peppe", "azerty", "peppe@spiagge.it", "+39123456789"));
    }

    @Transactional(readOnly = true)
    void afficherStatistiques() {

        // méthode qui renvoie les plateformes dont le nom contient ce qui est donné en paramètre
        System.out.println("Méthode qui renvoie les plateformes dont le nom contient ce qui est donné en paramètre");
        plateformeRepository.findByNomContaining("Sta").forEach(System.out::println);

        // méthode qui renvoie les éditeurs n'ayant pas encore édité de jeux
        System.out.println("Méthode qui renvoie les éditeurs n'ayant pas encore édité de jeux");
        editeurRepository.findEditorsWithoutGames().forEach(e -> System.out.println(e.getNom()));

        // R5 : méthode par dérivation qui renvoie les jeux disponibles sur la plateforme donnée en paramètre
        System.out.println("Méthode par dérivation qui renvoie les jeux disponibles sur la plateforme donnée en paramètre");
        jeuRepository.findByPlateformes(plateformeRepository.findByNom("Nintendo Wii")).forEach(p -> System.out.println(p.getNom()));
    }

    private void ajouterJeux() {
        if (jeuRepository.count()==0) {
            Editeur nintendo = editeurRepository.findByNom("Nintendo");
            Editeur ubisoft = editeurRepository.findByNom("Ubisoft");
            Editeur riot = editeurRepository.findByNom("Riot Games");
            Editeur ankama = editeurRepository.findByNom("Ankama");
            Editeur bioWare = editeurRepository.findByNom("BioWare");
            Editeur cdProjeckRed = editeurRepository.findByNom("CD Projekt Red");
            Editeur blizzard = editeurRepository.findByNom("Blizzard");
            Editeur fromSoftware = editeurRepository.findByNom("FromSoftware");
            Editeur naughtyDog = editeurRepository.findByNom("Naughty Dog");
            Editeur hazelightStudios = editeurRepository.findByNom("Hazelight Studios");
            Editeur idSoftware = editeurRepository.findByNom("idSoftware");

            Genre moba = genreRepository.findByNom("MOBA (Multiplayer online battle arena)");
            Genre rpg = genreRepository.findByNom("RPG (Role-playing game))");

            Jeu jeu = new Jeu("Animal Crossing New Horizons", LocalDate.of(2020, 3, 20), nintendo);
            jeu.setPlateformes(Arrays.asList(plateformeRepository.findAll().get(0), plateformeRepository.findAll().get(1)));
            jeuRepository.save(jeu);

            jeuRepository.save(new Jeu("Zelda Tears of the Kingdom", LocalDate.of(2023, 5, 12), nintendo));
            jeuRepository.save(new Jeu("Assassin's Creed Valhalla", LocalDate.of(2020, 11, 10), ubisoft));

            jeuRepository.save(new Jeu("Warframe"));
            jeuRepository.save(new Jeu("Final Fantasy VIII"));
            jeuRepository.save(new Jeu("Monster Hunter:World"));
            jeuRepository.save(new Jeu("Xenoblade Chronicles"));
            jeuRepository.save(new Jeu("Nier:Automata"));
            jeuRepository.save(new Jeu("Lost Ark"));
            jeuRepository.save(new Jeu("Aion"));
            jeuRepository.save(new Jeu("Métin 2"));
            jeuRepository.save(new Jeu("Tera"));
            jeuRepository.save(new Jeu("Tunic"));
            jeuRepository.save(new Jeu("Satisfactory"));
            jeuRepository.save(new Jeu("Valorant"));
            jeuRepository.save(new Jeu("Octopath Travellers"));
            jeuRepository.save(new Jeu("Minecraft"));
            jeuRepository.save(new Jeu("Outer Wild"));
            jeuRepository.save(new Jeu("Strays"));
            jeuRepository.save(new Jeu("Nier:Replicant"));

            jeuRepository.save(new Jeu("The last of us part II", editeurRepository.findByNom("Naughty Dog")));
            jeuRepository.save(new Jeu("GTA V", editeurRepository.findByNom("Rockstar")));
            jeuRepository.save(new Jeu("Splinter cell", editeurRepository.findByNom("Ubisoft")));

            jeuRepository.save(new Jeu("Mario Kart 8 ", "jeu de course", LocalDate.of(2014, 5, 29),
                    editeurRepository.findByNom("Nintendo")));
            jeuRepository.save(new Jeu("FIFA 2022", "jeu de simulation de football", LocalDate.of(2021, 9, 27),
                    editeurRepository.findByNom("Electronic Arts")));

            jeuRepository.save(new Jeu("League Of Legends", LocalDate.of(2009, 10, 27), riot, moba));
            jeuRepository.save(new Jeu("Dofus", LocalDate.of(2004, 9, 1), ankama, rpg));

            jeuRepository.save(new Jeu("Call of Duty", editeurRepository.findByNom("Activision")));
            jeuRepository.save(new Jeu("EVE", editeurRepository.findByNom("CCP")));
            jeuRepository.save(new Jeu("The Elder Scrolls : Skyrim", editeurRepository.findByNom("Bethesda")));

            jeuRepository.save(new Jeu("Dragon Age: Inquisition", LocalDate.of(2014, 11, 21), bioWare));
            jeuRepository.save(new Jeu("The Witcher 3: Wild Hunt", LocalDate.of(2015, 5, 24), cdProjeckRed));
            jeuRepository.save(new Jeu("Overwatch", LocalDate.of(2016, 11, 21), blizzard));
            jeuRepository.save(new Jeu("The Legend of Zelda: Breath of the Wild", LocalDate.of(2017, 3, 3), nintendo));
            jeuRepository.save(new Jeu("God of War", LocalDate.of(2018, 4, 4), ubisoft));
            jeuRepository.save(new Jeu("Sekiro: Shadows Die Twice", LocalDate.of(2019, 3, 22), fromSoftware));
            jeuRepository.save(new Jeu("The Last of Us Part II", LocalDate.of(2020, 6, 19), naughtyDog));
            jeuRepository.save(new Jeu("It Takes Two", LocalDate.of(2021, 11, 4), hazelightStudios));
            jeuRepository.save(new Jeu("Elden Ring", LocalDate.of(2022, 2, 25), fromSoftware));

            jeuRepository.save(new Jeu("Doom eternal", LocalDate.of(2020, 3, 20), idSoftware));

            jeuRepository.save(new Jeu("Palworld", LocalDate.of(2024, 1, 19), idSoftware));

            jeuRepository.save(new Jeu("Pikmin", LocalDate.of(2001, 10, 26), editeurRepository.findByNom("Nintendo")));

            jeuRepository.save(new Jeu("Halo 5", LocalDate.of(2015, 10, 27), editeurRepository.findByNom("Microsoft")));
        }
    }

    private void ajouterPlateformes() {
        if (plateformeRepository.count() == 0) {
            plateformeRepository.save(new Plateforme("Amstrad CPC"));
            plateformeRepository.save(new Plateforme("Nintendo Wii"));
            plateformeRepository.save(new Plateforme("Nintendo Wii U"));
            plateformeRepository.save(new Plateforme("Nintendo Switch"));
            plateformeRepository.save(new Plateforme("Windows"));
            plateformeRepository.save(new Plateforme("MacOS"));
            plateformeRepository.save(new Plateforme("Steam"));
            plateformeRepository.save(new Plateforme("Neo-Geo"));
            plateformeRepository.save(new Plateforme("PlayStation 1"));
            plateformeRepository.save(new Plateforme("PlayStation 2"));
            plateformeRepository.save(new Plateforme("PlayStation 3"));
            plateformeRepository.save(new Plateforme("PlayStation 4"));
            plateformeRepository.save(new Plateforme("PlayStation 5"));
            plateformeRepository.save(new Plateforme("PlayStation Vita"));
            plateformeRepository.save(new Plateforme("PSP"));
            plateformeRepository.save(new Plateforme("Sega Dreamcast"));
            plateformeRepository.save(new Plateforme("Sega Mastersystem"));
            plateformeRepository.save(new Plateforme("Sega Saturn"));
            plateformeRepository.save(new Plateforme("Xbox One"));
            plateformeRepository.save(new Plateforme("Xbox One Series"));
            plateformeRepository.save(new Plateforme("Xbox 360"));
            plateformeRepository.save(new Plateforme("Amiga"));
            plateformeRepository.save(new Plateforme("Android"));
            plateformeRepository.save(new Plateforme("Atari 8-bit"));
            plateformeRepository.save(new Plateforme("Atari Jaguar"));
            plateformeRepository.save(new Plateforme("Commodore 64"));
            plateformeRepository.save(new Plateforme("Game Boy"));
            plateformeRepository.save(new Plateforme("Game Boy Color"));
            plateformeRepository.save(new Plateforme("Game Boy Advance"));
            plateformeRepository.save(new Plateforme("Game Boy Advance SP"));
            plateformeRepository.save(new Plateforme("NES"));
            plateformeRepository.save(new Plateforme("PC-Engine"));
            plateformeRepository.save(new Plateforme("SNES"));
            plateformeRepository.save(new Plateforme("Nintendo 3DS"));
            plateformeRepository.save(new Plateforme("Nintendo 64"));
            plateformeRepository.save(new Plateforme("Nintendo DS"));
            plateformeRepository.save(new Plateforme("Nintendo Gamecube"));
        }
    }

    private void ajouterGenres() {
        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("FPS (First person shooter)"));
            genreRepository.save(new Genre("TS (real-time strategy)"));
            genreRepository.save(new Genre("RPG (Role-playing game))"));
            genreRepository.save(new Genre("Simulation"));
            genreRepository.save(new Genre("Gestion"));
            genreRepository.save(new Genre("TPS (Third person shooter)"));
            genreRepository.save(new Genre("Digital collectible card game"));
            genreRepository.save(new Genre("MOBA (Multiplayer online battle arena"));
            genreRepository.save(new Genre("Hack n Slash"));
            genreRepository.save(new Genre("Action/Aventure"));
            genreRepository.save(new Genre("Point and click"));
            genreRepository.save(new Genre("Plates-formes"));
            genreRepository.save(new Genre("4X (eXplore, eXpand, eXploit and eXterminate)"));
            genreRepository.save(new Genre("Tactical RPG"));
            genreRepository.save(new Genre("Action RPG"));
        }
    }

    @Transactional(readOnly = true)
    void ajouterClassifications() {
        if (classificationRepository.count() == 0) {
            classificationRepository.save(new Classification("PG 3", "0000FF"));
            classificationRepository.save(new Classification("PG 7", "00FF00"));
            classificationRepository.save(new Classification("PG 12", "FF0000"));
            classificationRepository.save(new Classification("PG 16", "FFFF00"));
            classificationRepository.save(new Classification("PG 18","00FFFF"));
            classificationRepository.save(new Classification("Aucune", "FFFFFF"));
        }
    }


    private void ajouterEditeurs() {
        if (editeurRepository.count() == 0) {
            editeurRepository.save(new Editeur("Activision"));
            editeurRepository.save(new Editeur("Amazon Games"));
            editeurRepository.save(new Editeur("Ankama"));
            editeurRepository.save(new Editeur("Bandai Namco"));
            editeurRepository.save(new Editeur("Bethesda"));
            editeurRepository.save(new Editeur("BioWare"));
            editeurRepository.save(new Editeur("Blizzard"));
            editeurRepository.save(new Editeur("Capcom"));
            editeurRepository.save(new Editeur("CCP"));
            editeurRepository.save(new Editeur("CD Projekt Red"));
            editeurRepository.save(new Editeur("Davilex"));
            editeurRepository.save(new Editeur("Digital Extreme"));
            editeurRepository.save(new Editeur("Electronic Arts"));
            editeurRepository.save(new Editeur("Epic Games"));
            editeurRepository.save(new Editeur("FromSoftware"));
            editeurRepository.save(new Editeur("Hazelight Studios"));
            editeurRepository.save(new Editeur("idSoftware"));
            editeurRepository.save(new Editeur("Microsoft"));
            editeurRepository.save(new Editeur("MonolithSoftware"));
            editeurRepository.save(new Editeur("Naughty Dog"));
            editeurRepository.save(new Editeur("Nintendo"));
            editeurRepository.save(new Editeur("Riot Games"));
            editeurRepository.save(new Editeur("Rockstar"));
            editeurRepository.save(new Editeur("Sega"));
            editeurRepository.save(new Editeur("Square Enix"));
            editeurRepository.save(new Editeur("Tencent"));
            editeurRepository.save(new Editeur("Ubisoft"));
            editeurRepository.save(new Editeur("Ultra Software"));
            editeurRepository.save(new Editeur("Valve"));
            editeurRepository.save(new Editeur("Wildcard"));
        }
    }*/

}
