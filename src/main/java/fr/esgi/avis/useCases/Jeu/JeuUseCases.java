package fr.esgi.avis.useCases.Jeu;

import fr.esgi.avis.domain.jeu.JeuRepository;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JeuUseCases {

    private final JeuRepository jeuRepository;

    public Jeu createJeu(String nom) {
        return jeuRepository.save(new Jeu(nom));
    }

    public List<Jeu> getJeux() {
        return jeuRepository.findAll();
    }

    public Optional<Jeu> getJeuById(Long id) {
        return jeuRepository.findById(id);
    }

    public Optional<Jeu> getJeuByName(String nom) {
        return jeuRepository.findByNom(nom);
    }

    public void deleteJeuById(Long id) {
        jeuRepository.deleteById(id);
    }
}
