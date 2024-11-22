package uasz.sn.Gestion_Enseignement.maquette.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaquetteModele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    @OneToMany(mappedBy = "maquette")
    private Collection<UE> UEs;
    @OneToOne(mappedBy = "maquette")
    private Classe classe;


}
