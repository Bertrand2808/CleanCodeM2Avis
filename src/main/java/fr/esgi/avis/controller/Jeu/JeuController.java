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
