package uasz.sn.Gestion_Enseignement.maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;
import uasz.sn.Gestion_Enseignement.maquette.service.FormationService;

import java.security.Principal;
import java.util.List;

@Controller
public class FormationController {
    @Autowired
    private FormationService formationService;

    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/ChefDepartement/Maquette/Formation/ajouterFormation",method = RequestMethod.POST)
    public String ajouterFormation(Formation formation) {
        formationService.ajouter(formation);
        return "redirect:/ChefDepartement/Maquette/Formation";
    }
    @RequestMapping(value = "/ChefDepartement/Maquette/Formation/modifierFormation",method = RequestMethod.POST)
    public String modifierFormation(Formation formation) {
        formationService.modifier(formation);
        return "redirect:/ChefDepartement/Maquette/Formation";
    }
    @RequestMapping(value = "/ChefDepartement/Maquette/Formation/supprimerFormation",method = RequestMethod.POST)
    public String supprimerFormation(Formation formation) {
        formationService.supprimer(formation.getId());
        return "redirect:/ChefDepartement/Maquette/Formation";
    }

    @RequestMapping(value = "/ChefDepartement/Maquette/Formation",method = RequestMethod.GET)
    public String formation(Model model, Principal principal) {
        List<Formation> formations=formationService.lister();
        model.addAttribute("formations",formations);
        Utilisateur utilisateur=utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("utilisateur",utilisateur);
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "formation";
    }
}
