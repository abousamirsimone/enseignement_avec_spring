package uasz.sn.Gestion_Enseignement.Utilisateur.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String matricule;
}
