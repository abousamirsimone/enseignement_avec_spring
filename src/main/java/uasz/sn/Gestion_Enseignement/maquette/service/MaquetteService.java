package uasz.sn.Gestion_Enseignement.maquette.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Enseignant;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;
import uasz.sn.Gestion_Enseignement.maquette.modele.MaquetteModele;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.repository.MaquetteRepository;
import uasz.sn.Gestion_Enseignement.maquette.repository.UeRepository;

import java.util.*;

@Service
@Transactional
public class MaquetteService {
    @Autowired
    private MaquetteRepository maquetteRepository;
    @Autowired
    private UeRepository ueRepository;



    public void ajouter(Long id, Formation formation, List<UE> nouvellesUes, int semestre) {
        // Si l'ID est nul, cela signifie que nous devons créer une nouvelle maquette.
        if (id == null) {
            if (semestre != 1 && semestre != 2) {
                throw new IllegalArgumentException("Le semestre doit être soit 1 soit 2.");
            }
            // Créer une nouvelle maquette
            MaquetteModele nouvelleMaquette = new MaquetteModele();
            nouvelleMaquette.setFormation(formation);  // Affecter la formation
            nouvelleMaquette.setSemestre(semestre);

            // Ajouter les UEs à la maquette (éliminer les doublons)
            Set<UE> ueSet = new HashSet<>(nouvellesUes);
            nouvelleMaquette.getUes().addAll(ueSet);

            // Sauvegarder la maquette (l'ID sera généré automatiquement par la base de données)
            maquetteRepository.save(nouvelleMaquette);

        } else {
            // Si l'ID n'est pas nul, essayer de récupérer la maquette existante
            MaquetteModele maquette = maquetteRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La maquette avec cet ID n'existe pas."));

            // Ajouter les nouvelles UEs à la maquette existante
            Set<UE> ueSet = new HashSet<>(maquette.getUes());
            ueSet.addAll(nouvellesUes); // Éviter les doublons

            // Sauvegarder la maquette mise à jour
            maquette.setUes(new ArrayList<>(ueSet));
            maquetteRepository.save(maquette);
        }
    }





    public List<MaquetteModele> lister(){return maquetteRepository.findAll();}

    public MaquetteModele rechercher(Long id, Formation formation){
        List<MaquetteModele> maquetteOpt = maquetteRepository.findByFormation(formation);
        if (maquetteOpt.isEmpty()) {
            throw new EntityNotFoundException("Aucune maquette trouvée pour cette formation.");
        }
        return maquetteOpt.get(0);
    }

    public MaquetteModele modifier(MaquetteModele maquetteModele){return maquetteRepository.save(maquetteModele);}

    public  void supprimer(Long id){maquetteRepository.deleteById(id);}

    public List<MaquetteModele> listerparformation(Long id) {
        return maquetteRepository.findByFormationId(id);
    }


    public MaquetteModele rechercherParFormationEtSemestre(Long formationId, int semestre) {
        return (MaquetteModele) maquetteRepository.findByFormationIdAndSemestre(formationId, semestre)
                .orElse(null);
    }

    public void archiver(Long id) {
        maquetteRepository.findById(id).ifPresentOrElse(maquette -> {
            maquette.setArchive(!maquette.isArchive());
            maquetteRepository.save(maquette);
        }, () -> {
            throw new RuntimeException("Maquette introuvable avec l'ID : " + id);
        });
    }




}
