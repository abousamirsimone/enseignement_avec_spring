package uasz.sn.Gestion_Enseignement.maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.EcService;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

import java.security.Principal;
import java.util.List;

@Controller
public class MaquetteController {
    @Autowired
    private UeService ueService;
    @Autowired
    private EcService ecService;
    @Autowired
    private UtilisateurService utilisateurService;
    @RequestMapping(value = "/ChefDepartement/Maquette/UE",method = RequestMethod.GET)
    public String maquette(Model model, Principal principal) {
        List<UE> ues=ueService.lister();
        model.addAttribute("ues",ues);
        Utilisateur utilisateur=utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("utilisateur",utilisateur);
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "ue";
    }
   @RequestMapping(value = "/ChefDepartement/Maquette/UE/voir/{id}",method = RequestMethod.GET)
    public String voir(Model model ,Principal principal ,@PathVariable("id") Long id) {
       List<EC> ecs = ecService.listerparUE(id);
       model.addAttribute("ecs",ecs);
       UE ue = ueService. findById(id);
       model.addAttribute("ue", ue);

       List<UE> ues =ueService.lister();
       model.addAttribute("ues",ues);
       Utilisateur utilisateur=utilisateurService.rechercher_Utilisateur(principal.getName());
       model.addAttribute("utilisateur",utilisateur);
       model.addAttribute("nom",utilisateur.getNom());
       model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "ec";
   }
}
