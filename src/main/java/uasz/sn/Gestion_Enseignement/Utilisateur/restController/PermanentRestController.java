package uasz.sn.Gestion_Enseignement.Utilisateur.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanentService;

import java.util.List;
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api")
public class PermanentRestController {

    @Autowired
    private PermanentService permanentService;

    @GetMapping(path = "/permanents")
    public List lister_Permanent(){return permanentService.lister();}

    @PostMapping(path = "/permanents")
    public Per ajouter_Permanent(@RequestBody Per per){return permanentService.ajouter(per);}

    @GetMapping(path = "/permanents/{id}")
    public Per rechercher_Permanent(@PathVariable Long id){return permanentService.rechercher(id);}

    @PutMapping(path = "/permanents")
    public  Per modifier_Permanent(@RequestBody Per per){return permanentService.modifier(per);}

    @DeleteMapping(path = "permanents/{id}")
    public String supprimer_Permanent(@PathVariable Long id){permanentService.supprimer(id);
        return "Permanent supprimer avec succes";
    }

}
