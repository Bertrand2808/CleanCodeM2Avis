package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Moderateur.model.ModerateurEntity;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.model.ModerateurDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Adaptateur entre le domaine Moderateur et la persistance JPA.
 */
@Repository
@RequiredArgsConstructor
public class ModerateurJpaAdapter implements ModerateurDataSourcePort {

    private final ModerateurJpaRepository moderateurJpaRepository;

    /**
     * Sauvegarde un modérateur.
     * @param moderateur Le modérateur à enregistrer.
     * @return Le modérateur enregistré.
     */
    @Override
    public Moderateur save(Moderateur moderateur) {
        ModerateurEntity entity = ModerateurMapper.toEntity(moderateur);
        return ModerateurMapper.toDomain(moderateurJpaRepository.save(entity));
    }

    /**
     * Recherche un modérateur par son ID.
     * @param id L'ID du modérateur à rechercher.
     * @return Un `Optional<Moderateur>` si trouvé.
     */
    @Override
    public Optional<Moderateur> findById(Long id) {
        return moderateurJpaRepository.findById(id)
                .map(ModerateurMapper::toDomain);
    }

    /**
     * Recherche un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur.
     * @return Un `Optional<Moderateur>` si trouvé.
     */
    @Override
    public Optional<Moderateur> findByPseudo(String pseudo) {
        return moderateurJpaRepository.findByPseudo(pseudo)
                .map(ModerateurMapper::toDomain);
    }

    /**
     * Recherche un modérateur par son email.
     * @param email L'email du modérateur.
     * @return Un `Optional<Moderateur>` si trouvé.
     */
    @Override
    public Optional<Moderateur> findByEmail(String email) {
        return moderateurJpaRepository.findByEmail(email)
                .map(ModerateurMapper::toDomain);
    }

    /**
     * Recherche un modérateur par son numéro de téléphone.
     * @param numeroDeTelephone Le numéro de téléphone du modérateur.
     * @return Un `Optional<Moderateur>` si trouvé.
     */
    @Override
    public Optional<Moderateur> findByNumeroDeTelephone(String numeroDeTelephone) {
        return moderateurJpaRepository.findByNumeroDeTelephone(numeroDeTelephone)
                .map(ModerateurMapper::toDomain);
    }

    /**
     * Supprime un modérateur par son ID.
     * @param id L'ID du modérateur à supprimer.
     */
    @Override
    public void deleteById(Long id) {
        moderateurJpaRepository.deleteById(id);
    }

    /**
     * Supprime un modérateur par son pseudo.
     * @param pseudo Le pseudo du modérateur à supprimer.
     */
    @Override
    public void deleteByPseudo(String pseudo) {
        moderateurJpaRepository.deleteByPseudo(pseudo);
    }

    /**
     * Retourne tous les modérateurs existants.
     * @return Une liste de `Moderateur`.
     */
    @Override
    public List<Moderateur> findAll() {
        return moderateurJpaRepository.findAll()
                .stream()
                .map(ModerateurMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Retourne le nombre total de modérateurs.
     * @return Nombre de modérateurs.
     */
    @Override
    public long count() {
        return moderateurJpaRepository.count();
    }
}
