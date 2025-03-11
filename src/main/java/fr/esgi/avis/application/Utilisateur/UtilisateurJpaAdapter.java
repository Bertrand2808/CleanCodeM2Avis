package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.application.Utilisateur.model.UtilisateurEntity;
import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UtilisateurJpaAdapter
 */
@Repository
@RequiredArgsConstructor
public class UtilisateurJpaAdapter implements UtilisateurDataSourcePort {
    private final UtilisateurJpaRepository utilisateurJpaRepository;

    /**
     * Save a Utilisateur
     * @param utilisateur
     * @return Utilisateur
     */
    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        UtilisateurEntity utilisateurEntity = UtilisateurMapper.toEntity(utilisateur);
        return UtilisateurMapper.toDomain(utilisateurJpaRepository.save(utilisateurEntity));
    }

    /**
     * Find a Utilisateur by its pseudo
     * @param pseudo
     * @return Utilisateur
     */
    @Override
    public Optional<Utilisateur> findByPseudo(String pseudo) {
        return Optional.ofNullable(UtilisateurMapper.toDomain(utilisateurJpaRepository.findByPseudo(pseudo)));
    }

    /**
     * Find a Utilisateur by its email
     * @param email
     * @return Utilisateur
     */
    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return Optional.ofNullable(UtilisateurMapper.toDomain(utilisateurJpaRepository.findByEmail(email)));
    }

    /**
     * Delete a Utilisateur by its pseudo
     * @param pseudo
     * @return void
     */
    public void deleteByPseudo(String pseudo) {
        utilisateurJpaRepository.deleteByPseudo(pseudo);
    }

    /**
     * Find a Utilisateur by its id
     * @param id
     * @return Utilisateur
     */
    @Override
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurJpaRepository.findById(id)
                .map(UtilisateurMapper::toDomain);
    }

    /**
     * Delete a Utilisateur by its id
     * @param id
     * @return void
     */
    public void deleteById(Long id) {
        utilisateurJpaRepository.deleteById(id);
    }
}
