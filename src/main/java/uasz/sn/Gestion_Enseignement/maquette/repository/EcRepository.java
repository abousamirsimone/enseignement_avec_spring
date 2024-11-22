package uasz.sn.Gestion_Enseignement.maquette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;

import java.util.List;

public interface EcRepository extends JpaRepository<EC,Long> {
    List<EC> findByUeId(Long idUE);
}

