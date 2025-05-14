package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.EditeurDTO;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EditeurController {

    private final EditeurUseCases editeurUseCases;

    public EditeurDTO createEditeur(EditeurDTO editeurDTO) {
        Editeur editeur = EditeurDtoMapper.toDomain(editeurDTO);
        Editeur createdEditeur = editeurUseCases.createEditeur(editeur);
        return EditeurDtoMapper.toDto(createdEditeur);
    }

    public List<EditeurDTO> getEditeurs() {
        return editeurUseCases.getEditeurs().stream()
                .map(EditeurDtoMapper::toDto)
                .toList();
    }

    public Optional<EditeurDTO> getEditeurById(Long id) {
        return editeurUseCases.getEditeurById(id)
                .map(EditeurDtoMapper::toDto);
    }

    public Optional<EditeurDTO> getEditeurByNom(String nom) {
        return editeurUseCases.getEditeurByNom(nom)
                .map(EditeurDtoMapper::toDto);
    }

    public List<EditeurDTO> getEditeursByNomContaining(String keyword) {
        return editeurUseCases.getEditeursByNomContaining(keyword).stream()
                .map(EditeurDtoMapper::toDto)
                .toList();
    }

    public void deleteEditeurById(Long id) {
        editeurUseCases.deleteEditeurById(id);
    }
}
