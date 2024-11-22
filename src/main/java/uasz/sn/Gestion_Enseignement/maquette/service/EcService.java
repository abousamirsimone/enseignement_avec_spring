package uasz.sn.Gestion_Enseignement.maquette.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.repository.EcRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EcService {
    @Autowired
    private EcRepository ecRepository;

    public EC ajouter(EC ec){
        return ecRepository.save(ec);
    }
    public List<EC> lister(){return ecRepository.findAll();}

    public EC rechercher(Long id){return ecRepository.findById(id).get();}

    public EC modifier(EC ec){return ecRepository.save(ec);}

    public  void supprimer(Long id){ecRepository.deleteById(id);}

    public List<EC> listerparUE(Long idUE) {
        return ecRepository.findByUeId(idUE);  // Méthode du repository qui retourne une liste
    }

    public Optional<EC> findOptionalById(Long id) {
        return ecRepository.findById(id);
    }
}