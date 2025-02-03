package uasz.sn.Gestion_Enseignement.maquette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;
import uasz.sn.Gestion_Enseignement.maquette.repository.EcRepository;
import uasz.sn.Gestion_Enseignement.maquette.repository.FormationRepository;

import java.util.List;
@Service
@Transactional
public class FormationService {
    @Autowired
    private FormationRepository formationRepository;

    public Formation ajouter(Formation formation){
        return formationRepository.save(formation);
    }
    public List<Formation> lister(){return formationRepository.findAll();}

    public Formation rechercher(Long id){return formationRepository.findById(id).get();}

    public Formation modifier(Formation formation){return formationRepository.save(formation);}

    public  void supprimer(Long id){formationRepository.deleteById(id);}


    public Formation findById(Long id) {
        return formationRepository.findById(id).get();
    }

    public Formation findOptionalById(Long id) {
        return formationRepository.findById(id).get();
    }

    public Formation getById(Long id) {
        return formationRepository.findById(id).get();
    }
}
