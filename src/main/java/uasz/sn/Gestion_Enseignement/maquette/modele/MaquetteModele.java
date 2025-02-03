package uasz.sn.Gestion_Enseignement.maquette.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @ManyToMany
    @JoinTable(
            name = "maquette_ue",
            joinColumns = @JoinColumn(name = "maquette_id"),
            inverseJoinColumns = @JoinColumn(name = "ue_id")
    )
    private List<UE> ues = new ArrayList<>(); // NE DOIT PAS ÊTRE NULL

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;
    private int semestre;


}
