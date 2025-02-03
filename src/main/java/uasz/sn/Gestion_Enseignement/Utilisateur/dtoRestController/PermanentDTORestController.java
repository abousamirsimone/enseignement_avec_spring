package uasz.sn.Gestion_Enseignement.Utilisateur.dtoRestController;

import java.util.List;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Utilisateur.Mapper.PermanentMapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.PermanentDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.PermanentService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/apiDTO")
public class PermanentDTORestController {
    @Autowired
    private PermanentService permanentService;

    private PermanentMapper permanentMapper= Mappers.getMapper(PermanentMapper.class);

    @PostMapping(path = "/permanent")
    public ResponseEntity<PermanentDTO> ajouter(@RequestBody PermanentDTO permanentDTO) {

        Per per= permanentMapper.dtoToPermanent(permanentDTO);
        permanentService.ajouter(per);
        return ResponseEntity.status(HttpStatus.CREATED).body(permanentDTO);
    }
    @GetMapping(path = "/permanent/{id}")
    public ResponseEntity<PermanentDTO> rechercher(@PathVariable Long id) {
        Per per= permanentService.rechercher(id);
        PermanentDTO permanentDTO=permanentMapper.permanentToDTO(per);
        return ResponseEntity.ok(permanentDTO);
    }
    @PutMapping(path = "/permanent")
    public ResponseEntity<PermanentDTO> modifier(@RequestBody PermanentDTO permanentDTO) {
        Per per= permanentService.rechercher((long) permanentDTO.getId());
        per.setUsername(permanentDTO.getUsername());
        per.setNom(permanentDTO.getNom());
        per.setPrenom(permanentDTO.getPrenom());
        per.setMatricule(permanentDTO.getMatricule());
        permanentService.modifier(per);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(permanentDTO);
    }

    @DeleteMapping(path = "/permanent/{id}")
    public ResponseEntity<String> supprimer(@RequestBody Long id) {

        permanentService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping(path = "/permanent")
    public ResponseEntity<List<PermanentDTO>> lister() {
        List<PermanentDTO> productList = permanentMapper.permanentListToDTO(permanentService.lister());
        return ResponseEntity.ok(productList);
    }

}
