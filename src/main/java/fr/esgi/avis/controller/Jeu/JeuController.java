package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jeux")
@RequiredArgsConstructor
public class JeuController {

    private final JeuUseCases jeuUseCases;

    @GetMapping
    public String getJeuxPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(required = false) String sort,
            Model model) {

        List<JeuDTO> jeux = jeuUseCases.getJeux().stream()
                .map(JeuDtoMapper::toDto)
                .toList();

        // Créer un objet Page pour la pagination
        PageRequest pageRequest = PageRequest.of(page, size,
            sort != null ? Sort.by(sort) : Sort.by("nom"));

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), jeux.size());

        Page<JeuDTO> pageDeJeux = new PageImpl<>(
            jeux.subList(start, end),
            pageRequest,
            jeux.size()
        );

        model.addAttribute("pageDeJeux", pageDeJeux);
        model.addAttribute("sort", sort);
        model.addAttribute("tempsDeTraitementEnMs", System.currentTimeMillis());

        return "jeux";
    }

    public JeuDTO createJeu(JeuDTO jeuDTO) {
        Jeu jeu = JeuDtoMapper.toDomain(jeuDTO);
        Jeu createdJeu = jeuUseCases.createJeu(jeu);
        return JeuDtoMapper.toDto(createdJeu);
    }

    public List<JeuDTO> getJeux() {
        return jeuUseCases.getJeux().stream()
                .map(JeuDtoMapper::toDto)
                .toList();
    }

    public Optional<JeuDTO> getJeuById(Long id) {
        return jeuUseCases.getJeuById(id)
                .map(JeuDtoMapper::toDto);
    }

    public Optional<JeuDTO> getJeuByNom(String nom) {
        return jeuUseCases.getJeuByNom(nom)
                .map(JeuDtoMapper::toDto);
    }

    public List<JeuDTO> getJeuxByNomContaining(String keyword) {
        return jeuUseCases.getJeuxByNomContaining(keyword).stream()
                .map(JeuDtoMapper::toDto)
                .toList();
    }

    public void deleteJeuById(Long id) {
        jeuUseCases.deleteJeuById(id);
    }
}
