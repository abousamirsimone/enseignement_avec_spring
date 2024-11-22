package uasz.sn.Gestion_Enseignement.maquette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.repository.UeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UeService {
    @Autowired
    private UeRepository ueRepository;

    public UE ajouter(UE ue){
        return ueRepository.save(ue);
    }
    public List<UE> lister(){return ueRepository.findAll();}

    public UE rechercher(Long id){return ueRepository.findById(id).get();}

    public UE modifier(UE ue){return ueRepository.save(ue);}

    public  void supprimer(Long id){ueRepository.deleteById(id);}

    public UE findById(Long id) {
        return ueRepository.findById(id).get();
    }
    public UE findOptionalById(Long id) {
        return ueRepository.findById(id).get();
    }


}