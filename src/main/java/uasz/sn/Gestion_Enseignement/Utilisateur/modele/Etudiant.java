package uasz.sn.Gestion_Enseignement.Utilisateur.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;

import java.util.Date;

@Entity
@Table(name = "Etudiant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends Utilisateur {
    private String matricule;
    private Date datenaissance;
}
