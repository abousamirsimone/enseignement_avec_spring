package uasz.sn.Gestion_Enseignement.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
