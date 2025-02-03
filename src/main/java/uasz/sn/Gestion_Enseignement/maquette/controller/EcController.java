package uasz.sn.Gestion_Enseignement.maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.EcService;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class EcController {
    @Autowired
    private EcService ecService;
    @Autowired
    private UeService ueService;

    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/ChefDepartement/ajouterEc/{id}", method = RequestMethod.POST)
    public String ajouter_Ec(@PathVariable("id") Long id, @ModelAttribute EC ec, Model model) {
        // Vérifier si l'UE existe
        UE ue = ueService.findById(id);

        if (ue!=null) {
            // Associer l'UE à l'EC
            ec.setUe(ue);
            // Ajouter l'EC via le service
            ecService.ajouter(ec);

            // Ajouter les ECs mis à jour au modèle
            model.addAttribute("ecs", ue.getEcs()); // Si la relation est bidirectionnelle
            model.addAttribute("id", id);

            // Rediriger vers la page de la liste des ECs
            return "redirect:/ChefDepartement/Maquette/UE/voir/" + id;
        } else {
            model.addAttribute("error", "L'UE spécifiée n'existe pas.");
            return "errorPage"; // Afficher une page d'erreur en cas d'UE inexistante
        }
    }

    @RequestMapping(value = "/ChefDepartement/modifierEc/{id}", method = RequestMethod.POST)
    public String modifierEc(@PathVariable("id") Long id, @ModelAttribute EC ec, Model model) {
        // Rechercher l'UE associée
        UE ue = ueService.findOptionalById(id);

        if (ue!=null) {
            ec.setUe(ue);

            // Modifier l'EC
            ecService.modifier(ec);

            // Rediriger vers la page de visualisation
            return "redirect:/ChefDepartement/Maquette/UE/voir/" + id;
        } else {
            // Gestion du cas où l'UE n'existe pas
            model.addAttribute("error", "L'UE spécifiée n'existe pas.");
            return "errorPage"; // Remplacez par une page d'erreur appropriée
        }
    }


    @RequestMapping(value = "/ChefDepartement/supprimerEc/{id}",method = RequestMethod.POST)
    public String supprimer_Ec( @PathVariable("id") Long id, @RequestParam("idEc") Long idEc,
                                @ModelAttribute EC ec, Model model){
        UE ue = ueService.findOptionalById(id);

        if (ue != null) {
            ec.setUe(ue);

            // Modifier l'EC
            ecService.supprimer(idEc);

            // Rediriger vers la page de visualisation
            return "redirect:/ChefDepartement/Maquette/UE/voir/" + id;
        } else {
            // Gestion du cas où l'UE n'existe pas
            model.addAttribute("error", "L'UE spécifiée n'existe pas.");
            return "errorPage"; // Remplacez par une page d'erreur appropriée
        }
    }
    @RequestMapping(value = "/ChefDepartement/Maquette/UE/voir/{id}",method = RequestMethod.GET)
    public String voir(Model model , Principal principal , @PathVariable("id") Long id) {
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
