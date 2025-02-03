package uasz.sn.Gestion_Enseignement.Utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermanentDTO {

    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String matricule;
    private String grade;
}
