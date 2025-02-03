package uasz.sn.Gestion_Enseignement.maquette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
