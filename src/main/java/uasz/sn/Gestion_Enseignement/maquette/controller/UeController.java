package uasz.sn.Gestion_Enseignement.maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

@Controller
public class UeController {
    @Autowired
    private UeService ueService;
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
        return "redirect:/ChefDepartement/Maquette/UE";
    }
}
