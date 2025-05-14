package fr.esgi.avis.useCases.Editeur;

import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditeurUseCases {

    private final EditeurDataSourcePort EditeurDataSourcePort;

    public Editeur createEditeur(Editeur Editeur) {
        return EditeurDataSourcePort.save(Editeur);
    }

    public List<Editeur> getEditeurs() {
        return EditeurDataSourcePort.findAll();
    }

    public Optional<Editeur> getEditeurById(Long id) {
        return EditeurDataSourcePort.findById(id);
    }

    public Optional<Editeur> getEditeurByNom(String nom) {
        return EditeurDataSourcePort.findByNom(nom);
    }

    public List<Editeur> getEditeursByNomContaining(String keyword) {
        return EditeurDataSourcePort.findByNomContaining(keyword);
    }

    public void deleteEditeurById(Long id) {
        EditeurDataSourcePort.deleteById(id);
    }
}
