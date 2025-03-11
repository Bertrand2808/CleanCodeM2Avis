package fr.esgi.avis.controller.Joueur.dto;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoueurDTO extends UtilisateurDTO {
    private Long id;
    private LocalDate dateDeNaissance;
    private List<AvisDTO> avis;
    private AvatarDTO avatar;
}
