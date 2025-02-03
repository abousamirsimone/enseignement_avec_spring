package uasz.sn.Gestion_Enseignement.Utilisateur.Mapper;

import org.mapstruct.Mapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.EtudiantDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Etudiant;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    EtudiantDTO etudiantToDTO(Etudiant etudiant);
    Etudiant dtoToEtudiant(EtudiantDTO etudiantDTO);
    List<EtudiantDTO> etudiantListToDTO(List<Etudiant> etudiantList);
}
