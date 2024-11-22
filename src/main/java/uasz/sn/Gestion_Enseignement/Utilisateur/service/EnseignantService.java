package uasz.sn.Gestion_Enseignement.Utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Enseignant;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.EnseignantRepository;

import java.util.List;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepositpry;

    public Enseignant ajouter(Enseignant enseignant){
        return enseignantRepositpry.save(enseignant);
    }
    public List<Enseignant> lister(){return enseignantRepositpry.findAll();}

    public Enseignant rechercher(Long id){return enseignantRepositpry.findById(id).get();}

    public Enseignant modifier(Enseignant enseignant){return enseignantRepositpry.save(enseignant);}

    public  void supprimer(Long id){enseignantRepositpry.deleteById(id);}
    public void  activer(Long id){
        Enseignant enseignant = enseignantRepositpry.findById(id).get();
        enseignant.setAtive(!enseignant.isAtive());
        enseignantRepositpry.save(enseignant);
    }
    public void archiver(Long id){
        Enseignant enseignant = enseignantRepositpry.findById(id).get();
        enseignant.setArchive(!enseignant.isArchive());
        enseignantRepositpry.save(enseignant);
    }
}
