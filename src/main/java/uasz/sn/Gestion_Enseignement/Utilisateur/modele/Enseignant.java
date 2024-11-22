package uasz.sn.Gestion_Enseignement.Utilisateur.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;

@Entity
@Table(name = "Enseignant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public abstract class  Enseignant extends Utilisateur {
    private String specialite;
    private boolean ative;
    private boolean archive;
}
