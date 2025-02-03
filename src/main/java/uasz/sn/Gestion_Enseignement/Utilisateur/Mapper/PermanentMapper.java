package uasz.sn.Gestion_Enseignement.Utilisateur.Mapper;


import org.mapstruct.Mapper;
import uasz.sn.Gestion_Enseignement.Utilisateur.dto.PermanentDTO;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;

import java.util.List;

@Mapper
public interface PermanentMapper {
    PermanentDTO permanentToDTO(Per per);

    Per dtoToPermanent(PermanentDTO permanentDTO);

    List<PermanentDTO>permanentListToDTO(List<Per> pers);
}
