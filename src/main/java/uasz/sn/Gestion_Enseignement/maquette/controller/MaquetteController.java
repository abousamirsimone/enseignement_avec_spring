package uasz.sn.Gestion_Enseignement.maquette.controller;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.modele.Formation;
import uasz.sn.Gestion_Enseignement.maquette.modele.MaquetteModele;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.EcService;
import uasz.sn.Gestion_Enseignement.maquette.service.FormationService;
import uasz.sn.Gestion_Enseignement.maquette.service.MaquetteService;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/ChefDepartement")
public class MaquetteController {

    @Autowired
    private UeService ueService;
    @Autowired
    private EcService ecService;
    @Autowired
    private FormationService formationService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private MaquetteService maquetteService;

    @GetMapping("/Formation/voirMaquette/{id}")
    public String chefDepartement_Maquette(@PathVariable("id") Long id, Model model, Principal principal) {
        Formation formation = formationService.findById(id);
        model.addAttribute("formation", formation != null ? formation : new Formation());

        try {
            MaquetteModele maquettes = maquetteService.rechercher(id, formation);
            model.addAttribute("maquettes", maquettes != null ? maquettes : new MaquetteModele());
        } catch (EntityNotFoundException e) {
            model.addAttribute("maquettes", new MaquetteModele());
        }

        List<UE> ues = ueService.lister();
        model.addAttribute("ues", ues != null ? ues : new ArrayList<>());

        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        if (utilisateur != null) {
            model.addAttribute("nom", utilisateur.getNom());
            model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        }

        return "maquette";
    }


    @PostMapping("/ajouterUEM/{id}")
    public String ajouterMaquette(
            @PathVariable("id") Long id,
            @RequestParam("ueIds") List<Long> ueIds,
            @RequestParam("semestre") int semestre,
            RedirectAttributes redirectAttributes) {

        Formation formation = formationService.findById(id);
        if (formation == null) {
            redirectAttributes.addFlashAttribute("message", "Formation non trouvée.");
            return "redirect:/ChefDepartement/Formation";
        }
        if (semestre != 1 && semestre != 2) {
            redirectAttributes.addFlashAttribute("message", "Le semestre doit être soit 1 soit 2.");
            return "redirect:/ChefDepartement/Formation";
        }
        // Création ou récupération de la maquette associée au semestre
        MaquetteModele maquette = maquetteService.rechercherParFormationEtSemestre(id, semestre);
        if (maquette == null) {
            maquette = new MaquetteModele();
            maquette.setFormation(formation);
            maquette.setSemestre(semestre);
        }

        if (ueIds != null && !ueIds.isEmpty()) {
            List<UE> nouvellesUes = ueService.findAllById(ueIds);

            // Éviter les doublons
            Set<UE> ueSet = new HashSet<>(maquette.getUes());
            ueSet.addAll(nouvellesUes);  // Ajoute les nouvelles UEs sans doublons

            maquette.setUes(new ArrayList<>(ueSet)); // Convertir en liste

            maquetteService.ajouter(maquette.getId(), maquette.getFormation(), maquette.getUes(), semestre);
        }


        redirectAttributes.addFlashAttribute("success", "Maquette ajoutée avec succès !");
        return "redirect:/ChefDepartement/Formation/voirMaquette/" + id;
    }



    @RequestMapping(value = "/ChefDepartement/archiverUEM", method = RequestMethod.POST)
    public String archiver_Permanent(@RequestParam("id") Long idFormation, @RequestParam("idm") Long idm) {
        maquetteService.archiver(idm);
        return "redirect:/ChefDepartement/Formation/voirMaquette/" + idFormation;
    }



}