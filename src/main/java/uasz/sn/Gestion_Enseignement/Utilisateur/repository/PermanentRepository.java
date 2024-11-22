package uasz.sn.Gestion_Enseignement.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;

@Repository
public interface PermanentRepository extends JpaRepository<Per,Long> {
}
