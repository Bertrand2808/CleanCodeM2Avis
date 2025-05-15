package fr.esgi.avis.controller.Utilisateur;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UtilisateurViewController
 * Controller for Utilisateur views
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UtilisateurViewController {

    /**
     * Affiche la page de connexion
     * @param model : le modèle
     * @return la vue de connexion
     */
    @GetMapping("/connexion")
    public String connexion(Model model) {
        return "connexion";
    }

    /**
     * Affiche la page d'inscription
     * @param model : le modèle
     * @return la vue d'inscription
     */
    @GetMapping("/inscription")
    public String inscription(Model model) {
        return "inscription";
    }

    /**
     * Affiche la page de déconnexion
     * @return redirection vers la page de connexion
     */
    @GetMapping("/deconnexion")
    public String deconnexion() {
        return "redirect:/connexion";
    }
}
