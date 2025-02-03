package uasz.sn.Gestion_Enseignement.maquette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Enseignant;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;
import uasz.sn.Gestion_Enseignement.maquette.modele.MaquetteModele;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaquetteRepository extends JpaRepository<MaquetteModele,Long> {
    List<MaquetteModele> findByFormationId(Long Id);

    List<MaquetteModele> findByFormation(Formation formation);

    Optional<Object> findByFormationIdAndSemestre(Long formationId, int semestre);
}
