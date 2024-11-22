package uasz.sn.Gestion_Enseignement.Utilisateur.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;

@Entity
@Table(name = "adminitratif")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Administratif extends Utilisateur {
    private String matricule;
}
