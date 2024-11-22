package uasz.sn.Gestion_Enseignement.maquette.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private int coefficient;
    @Column(nullable = false) // Heure de cours magistral obligatoire
    private int heure_CM;
    @Column(nullable = false) // Heure de cours magistral obligatoire
    private int heure_TP;
    @Column(nullable = false) // Heure de cours magistral obligatoire
    private int heure_TD;
    @Column(nullable = false) // Heure de cours magistral obligatoire
    private int heure_total;
    private int TPE;
    private int VHT;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Relation obligatoire avec une UE
    @JoinColumn(name = "ue_id", nullable = false)
    private UE ue;
}
