package uasz.sn.Gestion_Enseignement.Utilisateur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.PermanentRepository;

import java.util.List;

@Service
@Transactional
public class PermanentService  {
    @Autowired
    private PermanentRepository permanentRepository;

    public Per ajouter(Per per){
        return permanentRepository.save(per);
    }
    public List<Per> lister(){return permanentRepository.findAll();}

    public Per rechercher(Long id){return permanentRepository.findById(id).get();}

    public Per modifier(Per per){return permanentRepository.save(per);}

    public  void supprimer(Long id){permanentRepository.deleteById(id);}
}
