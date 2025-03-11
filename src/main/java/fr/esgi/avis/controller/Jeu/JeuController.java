package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDTO;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JeuController {

    private final JeuUseCases jeuUseCases;

    public List<JeuDTO> getJeux() {
        return jeuUseCases.getJeux().stream()
                .map(JeuDtoMapper::toDto)
                .toList();
    }

    public Optional<JeuDTO> getJeuById(Long id) {
        return jeuUseCases.getJeuById(id)
                .map(JeuDtoMapper::toDto);
    }

    public Optional<JeuDTO> getJeuByName(String nom) {
        return jeuUseCases.getJeuByName(nom)
                .map(JeuDtoMapper::toDto);
    }

    public JeuDTO createJeu(JeuDTO jeuDTO) {
        return JeuDtoMapper.toDto(
                jeuUseCases.createJeu(jeuDTO.getNom()) // ✅ Only using name as per `JeuUseCases`
        );
    }

    public void deleteJeuById(Long id) {
        jeuUseCases.deleteJeuById(id);
    }
}
