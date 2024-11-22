package uasz.sn.Gestion_Enseignement.Utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Role;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanentService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PermanentController {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EnseignantService enseignantService;
    @Autowired
    PermanentService permanentService;


    @RequestMapping(value = "/Permanent/Accueil",method = RequestMethod.GET)
    public String accueil_Permanent(Model model, Principal principal){
        Utilisateur utilisateur=utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "template_Permanent";
    }


    @RequestMapping(value = "/ChefDepartement/Accueil",method = RequestMethod.GET)
    public String accueil_ResponsableClase(Model model ,Principal principal){
        Utilisateur utilisateur =utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "template_ChefDepartement";
    }
    @RequestMapping(value = "/ChefDepartement/ajouterPermanent", method = RequestMethod.POST)
    public String ajouter_Permanent(Per per) {
        String password = passwordEncoder.encode("Passer123");

        per.setPassword(password);
        per.setDateCreation(new Date());
        per.setAtive(true);
        Role role = utilisateurService.ajouter_Role(new Role("Permanent"));
        enseignantService.ajouter(per);

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        per.setRoles(roles);

        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/modifierPermanent", method = RequestMethod.POST)
    public String modifier_Permanent(Per per) {
        Per per_modif = permanentService.rechercher(per.getId());
        per_modif.setMatricule(per.getMatricule());
        per_modif.setNom(per.getNom());
        per_modif.setPrenom(per.getPrenom());
        per_modif.setSpecialite(per.getSpecialite());
        per_modif.setGrade(per.getGrade());

        enseignantService.modifier(per_modif);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/activerPermanent", method = RequestMethod.POST)
    public String activer_Permanent(Long id) {
        enseignantService.activer(id);
        return "redirect:/ChefDepartement/Enseignant";
    }

    @RequestMapping(value = "/ChefDepartement/archiverPermanent", method = RequestMethod.POST)
    public String archiver_Permanent(Long id) {
        enseignantService.archiver(id);
        return "redirect:/ChefDepartement/Enseignant";
    }

}
