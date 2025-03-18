package fr.esgi.avis.application.Editeur;


import fr.esgi.avis.application.Editeur.model.EditeurEntity;
import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EditeurJpaAdapter implements EditeurDataSourcePort {
    private final EditeurJpaRepository editeurJpaRepository;

    @Override
    public Editeur save(Editeur editeur) {
        EditeurEntity editeurEntity = EditeurMapper.toEntity(editeur);
        return EditeurMapper.toDomain(editeurJpaRepository.save(editeurEntity));
    }

    @Override
    public List<Editeur> findAll() {
        return editeurJpaRepository.findAll()
                .stream()
                .map(EditeurMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Editeur> findById(Long id) {
        return editeurJpaRepository.findById(id).map(EditeurMapper::toDomain);
    }

    @Override
    public Optional<Editeur> findByNom(String nom) {
        return editeurJpaRepository.findByNom(nom).map(EditeurMapper::toDomain);
    }

    @Override
    public List<Editeur> findByNomContaining(String keyword) { // ✅ Implemented method
        return editeurJpaRepository.findByNomContaining(keyword)
                .stream()
                .map(EditeurMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        editeurJpaRepository.deleteById(id);
    }
}
