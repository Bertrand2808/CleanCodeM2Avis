package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.ModerateurDTO;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.usecases.Moderateur.ModerateurUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModerateurController {

    private final ModerateurUseCases moderateurUseCases;

    public ModerateurDTO createModerateur(ModerateurDTO moderateurDTO) {
        Moderateur moderateur = ModerateurDtoMapper.toDomain(moderateurDTO);
        Moderateur createdModerateur = moderateurUseCases.createModerateur(moderateur);
        return ModerateurDtoMapper.toDto(createdModerateur);
    }

    public List<ModerateurDTO> getAllModerateurs() {
        return moderateurUseCases.getAllModerateurs()
                .stream().map(ModerateurDtoMapper::toDto)
                .toList();
    }

    public Optional<ModerateurDTO> getModerateurByPseudo(String pseudo) {
        return moderateurUseCases.getModerateurByPseudo(pseudo)
                .map(ModerateurDtoMapper::toDto);
    }

    public Optional<ModerateurDTO> getModerateurById(Long id) {
        return moderateurUseCases.getModerateurById(id)
                .map(ModerateurDtoMapper::toDto);
    }

    public Optional<ModerateurDTO> getModerateurByEmail(String email) {
        return moderateurUseCases.getModerateurByEmail(email)
                .map(ModerateurDtoMapper::toDto);
    }

    public Optional<ModerateurDTO> getByNumeroDeTelephone(String numeroDeTelephone) {
        return moderateurUseCases.getByNumeroDeTelephone(numeroDeTelephone)
                .map(ModerateurDtoMapper::toDto);
    }

    public void deleteModerateurByPseudo(String pseudo) {
        moderateurUseCases.deleteModerateurByPseudo(pseudo);
    }

    public void deleteModerateurById(Long id){
        moderateurUseCases.deleteModerateurById(id);
    }



}
