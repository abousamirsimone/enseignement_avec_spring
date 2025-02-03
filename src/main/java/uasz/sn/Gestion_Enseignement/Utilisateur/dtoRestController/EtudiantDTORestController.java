package uasz.sn.Gestion_Enseignement.Utilisateur.dtoRestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.Mapper.EtudiantMapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.EtudiantDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Etudiant;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EtudiantService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apiDTO")
public class EtudiantDTORestController {
    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantMapper etudiantMapper;

    @PostMapping(path = "/etudiant")
    public ResponseEntity<EtudiantDTO> ajouter(@RequestBody EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantMapper.dtoToEtudiant(etudiantDTO);
        etudiantService.ajouter(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantDTO);
    }

    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<EtudiantDTO> rechercher(@PathVariable Long id) {
        Etudiant etudiant = etudiantService.rechercher(id);
        EtudiantDTO etudiantDTO = etudiantMapper.etudiantToDTO(etudiant);
        return ResponseEntity.ok(etudiantDTO);
    }

    @PutMapping(path = "/etudiant")
    public ResponseEntity<EtudiantDTO> modifier(@RequestBody EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantService.rechercher((long) etudiantDTO.getId());
        etudiant.setUsername(etudiantDTO.getUsername());
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setMatricule(etudiantDTO.getMatricule());
        etudiantService.modifier(etudiant);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(etudiantDTO);
    }

    @DeleteMapping(path = "/etudiant")
    public ResponseEntity<Void> supprimer(@RequestBody Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(path = "/etudiant")
    public ResponseEntity<List<EtudiantDTO>> lister() {
        List<EtudiantDTO> productList = etudiantMapper.etudiantListToDTO(etudiantService.lister());
        return ResponseEntity.ok(productList);
    }
}
