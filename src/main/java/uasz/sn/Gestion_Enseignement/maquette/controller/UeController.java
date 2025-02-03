package uasz.sn.Gestion_Enseignement.maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

import java.security.Principal;
import java.util.List;

@Controller
public class UeController {
    @Autowired
    private UeService ueService;

    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/ChefDepartement/ajouterUe",method = RequestMethod.POST)
    public String ajouter_Ue(UE ue) {
        ueService.ajouter(ue);
        return "redirect:/ChefDepartement/Maquette/UE";
    }
    @RequestMapping(value = "/ChefDepartement/modifierUe",method = RequestMethod.POST)
    public String modifier_Ue(UE ue) {
        ueService.modifier(ue);
        return "redirect:/ChefDepartement/Maquette/UE";
    }
    @RequestMapping(value = "/ChefDepartement/supprimerUe",method = RequestMethod.POST)
    public String supprimer_Ue(UE ue) {
        ueService.supprimer(ue.getId());
        //eytuu
        return "redirect:/ChefDepartement/Maquette/UE";
    }
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
}
