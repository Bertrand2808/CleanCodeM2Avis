package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.AvatarJpaRepository;
import fr.esgi.avis.application.Avatar.model.AvatarEntity;
import fr.esgi.avis.application.Joueur.model.JoueurEntity;
import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JoueurJpaAdapter
 */
@Repository
@RequiredArgsConstructor
public class JoueurJpaAdapter implements JoueurDataSourcePort {
    private final JoueurJpaRepository joueurJpaRepository;
    private final AvatarJpaRepository avatarJpaRepository;

    /**
     * Save a Joueur
     * @param joueur
     * @return Joueur
     */
    @Override
    public Joueur save(Joueur joueur) {
        JoueurEntity joueurEntity = JoueurMapper.toEntity(joueur);
        return JoueurMapper.toDomain(joueurJpaRepository.save(joueurEntity));
    }

    @Override
    public List<Joueur> findAll() {
        return joueurJpaRepository.findAll()
                .stream()
                .map(JoueurMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Find a Joueur by its pseudo
     * @param pseudo
     * @return Joueur
     */
    @Override
    public Optional<Joueur> findByPseudo(String pseudo) {
        return Optional.ofNullable(JoueurMapper.toDomain(joueurJpaRepository.findByPseudo(pseudo)));
    }

    /**
     * Fin a Joueur by its id
     * @param id id of the player
     * @return Joueur found
     */
    @Override
    public Optional<Joueur> findById(Long id) {
        return joueurJpaRepository.findById(id)
                .map(JoueurMapper::toDomain);
    }

    /**
     * Find a Joueur by its date of birth
     * @param dateDeNaissance
     * @return Joueur
     */
    @Override
    public Optional<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance) {
        return joueurJpaRepository.findByDateDeNaissance(dateDeNaissance)
                .map(JoueurMapper::toDomain);
    }

    /**
     * Delete a Joueur by its pseudo
     * @param pseudo
     * @return void
     */
    public void deleteByPseudo(String pseudo) {
        joueurJpaRepository.deleteByPseudo(pseudo);
    }

    @Override
    public long count() {
        return joueurJpaRepository.count();
    }

    /**
     * Met à jour l'avatar d'un joueur.
     * @param joueurId ID du joueur.
     * @param avatarId ID du nouvel avatar.
     */
    @Override
    public void updateAvatar(Long joueurId, Long avatarId) {
        Optional<JoueurEntity> joueurEntity = joueurJpaRepository.findById(joueurId);
        Optional<AvatarEntity> avatarEntity = avatarJpaRepository.findById(avatarId);

        if (joueurEntity.isEmpty() || avatarEntity.isEmpty()){
            throw new IllegalArgumentException("Joueur ou Avatar introuvable.");
        }

        JoueurEntity joueurEntityToUpdate = joueurEntity.get();
        AvatarEntity avatarEntityToUpdate = avatarEntity.get();

        joueurEntityToUpdate.setAvatar(avatarEntityToUpdate);
        joueurJpaRepository.save(joueurEntityToUpdate);
    }
}

