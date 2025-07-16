package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JeuViewController
 * Controller for Jeu views
 */
@Controller
@RequestMapping("/jeux")
@RequiredArgsConstructor
public class JeuViewController {

    private final JeuUseCases jeuUseCases;
    private final JeuDtoMapper jeuDtoMapper;
    private final AvisController avisController;

    /**
     * Affiche les détails d'un jeu
     * @param id : l'identifiant du jeu
     * @param model : le modèle
     * @return la vue des détails du jeu
     */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        JeuDTO jeu = jeuDtoMapper.toDto(jeuUseCases.getJeuById(id).orElseThrow());
        model.addAttribute("jeu", jeu);

        // Récupérer et trier les avis par date décroissante
        List<AvisDTO> avisTries = avisController.getAvisByJeuId(id)
                .stream()
                .sorted((a1, a2) -> a2.getDateDEnvoi().compareTo(a1.getDateDEnvoi()))
                .collect(Collectors.toList());

        model.addAttribute("avis", avisTries);
        return "jeu";
    }
}
