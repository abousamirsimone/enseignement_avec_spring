package uasz.sn.Gestion_Enseignement.maquette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Enseignant;
import uasz.sn.Gestion_Enseignement.maquette.modele.MaquetteModele;

@Repository
public interface MaquetteRepository extends JpaRepository<MaquetteModele,Long> {
}
