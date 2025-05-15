package fr.esgi.avis.useCases.Jeu;

import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JeuUseCases {

    private final JeuDataSourcePort jeuDataSourcePort;

    public Jeu createJeu(Jeu jeu) {
        return jeuDataSourcePort.save(jeu);
    }

    public List<Jeu> getJeux() {
        return jeuDataSourcePort.findAll();
    }

    public Optional<Jeu> getJeuById(Long id) {
        return jeuDataSourcePort.findById(id);
    }

    public Optional<Jeu> getJeuByNom(String nom) {
        return jeuDataSourcePort.findByNom(nom);
    }

    public List<Jeu> getJeuxByNomContaining(String keyword) {
        return jeuDataSourcePort.findByNomContaining(keyword);
    }

    public void deleteJeuById(Long id) {
        jeuDataSourcePort.deleteById(id);
    }
}

