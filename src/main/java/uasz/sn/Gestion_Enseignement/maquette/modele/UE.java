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
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private int credits;
    private int coefficient;
    @ManyToOne
    private MaquetteModele maquette;
    @OneToMany(mappedBy = "ue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EC> ecs = new ArrayList<>();

}
