package uasz.sn.Gestion_Enseignement.maquette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.PermanentRepository;
import uasz.sn.Gestion_Enseignement.maquette.modele.MaquetteModele;
import uasz.sn.Gestion_Enseignement.maquette.repository.MaquetteRepository;

import java.util.List;

@Service
@Transactional
public class MaquetteService {
    @Autowired
    private MaquetteRepository maquetteRepository;

    public MaquetteModele ajouter(MaquetteModele maquetteModele){
        return maquetteRepository.save(maquetteModele);
    }
    public List<MaquetteModele> lister(){return maquetteRepository.findAll();}

    public MaquetteModele rechercher(Long id){return maquetteRepository.findById(id).get();}

    public MaquetteModele modifier(MaquetteModele maquetteModele){return maquetteRepository.save(maquetteModele);}

    public  void supprimer(Long id){maquetteRepository.deleteById(id);}
}
