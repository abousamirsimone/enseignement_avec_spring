package uasz.sn.Gestion_Enseignement.Utilisateur.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Etudiant;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Vacataire;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.VacataireService;

import java.util.List;
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api")
public class VacataireRestController {
    @Autowired
    private VacataireService vacataireService;
    @GetMapping(path = "/vacataire")
    public List lister_Vacataire(){return vacataireService.lister();}

    @PostMapping(path = "/vacataire")
    public Vacataire ajouter_Vacataire(@RequestBody Vacataire vacataire){return vacataireService.ajouter(vacataire);}

    @GetMapping(path = "/vacataire/{id}")
    public Vacataire rechercher_Vacataire(@PathVariable Long id){return vacataireService.rechercher(id);}

    @PutMapping(path = "/vacataire")
    public  Vacataire modifier_Vacataire(@RequestBody Vacataire vacataire){return vacataireService.modifier(vacataire);}

    @DeleteMapping(path = "vacataire/{id}")
    public String supprimer_Vacataire(@PathVariable Long id){vacataireService.supprimer(id);
        return "Vacataire supprimer avec succes";
    }

}
