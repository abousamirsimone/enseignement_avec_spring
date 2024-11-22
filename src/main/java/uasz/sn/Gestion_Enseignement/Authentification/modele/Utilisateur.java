package uasz.sn.Gestion_Enseignement.Authentification.modele;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    private String nom;
    private  String prenom;
    @Temporal(TemporalType.TIMESTAMP)
    private  Date dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private String adresse;
    private String telephone;
}
