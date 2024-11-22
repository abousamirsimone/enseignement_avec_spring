package uasz.sn.Gestion_Enseignement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Role;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Per;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Vacataire;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;
import uasz.sn.Gestion_Enseignement.maquette.modele.EC;
import uasz.sn.Gestion_Enseignement.maquette.modele.UE;
import uasz.sn.Gestion_Enseignement.maquette.service.EcService;
import uasz.sn.Gestion_Enseignement.maquette.service.UeService;

import java.util.Date;


@SpringBootApplication
public class GestionEnseignementApplication implements CommandLineRunner {
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private EnseignantService enseignantService;
	@Autowired
	private UeService ueService;
	@Autowired
	private EcService ecService;

	public static void main(String[] args){
		SpringApplication.run(GestionEnseignementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role permanent =utilisateurService.ajouter_Role(new Role("Permanent"));
		Role vacataire =utilisateurService.ajouter_Role(new Role("Vacataire"));
		String password = passwordEncoder().encode("passer123");
		Role chefDepartement=utilisateurService.ajouter_Role(new Role("ChefDepartement"));

		Per user_1=new Per();
		user_1.setNom("Diop");user_1.setPrenom("Ibrahima");user_1.setUsername("idiop@uasz.sn");
		user_1.setPassword(password);user_1.setDateCreation(new Date());user_1.setAtive(true);
		user_1.setSpecialite("Web Semantique");
		user_1.setMatricule("ID2024");user_1.setGrade("Professeur");
		enseignantService.ajouter(user_1);
		utilisateurService.ajouter_UtilisateurRoles(user_1,permanent);


		Vacataire user_2=new Vacataire();
		user_2.setNom("MALACK");user_2.setPrenom("Camir");user_2.setUsername("cmalack@uasz.sn");
		user_2.setPassword(password);user_2.setDateCreation(new Date());user_2.setAtive(true);
		user_2.setSpecialite("Ingenierie de connaissances");
		user_2.setNiveau("Doctorant");
		enseignantService.ajouter(user_2);
		utilisateurService.ajouter_UtilisateurRoles(user_2,vacataire);




		Per user=new Per();
		user.setNom("DIAGNE");user.setPrenom("Serigne");user.setUsername("sdiagne@uasz.sn");
		user.setPassword(password);user.setDateCreation(new Date());user.setAtive(true);
		user.setSpecialite("Base de donnees");
		user.setMatricule("SD2024");user.setGrade("Professeur");
		enseignantService.ajouter(user);
		utilisateurService.ajouter_UtilisateurRoles(user,chefDepartement);



		// Save UE instances
		UE ue1 = new UE();
		ue1.setCode("INF351");
		ue1.setLibelle("Reseaux et Telecoms");
		ue1.setCredits(8);
		ue1.setCoefficient(4);
		ueService.ajouter(ue1);  // Assuming this method persists Ue in the database

// Similarly, save other Ue instances
		UE ue2 = new UE();
		ue2.setCode("INF352");
		ue2.setLibelle("Genie Logiciel 1");
		ue2.setCredits(8);
		ue2.setCoefficient(4);
		ueService.ajouter(ue2);


// Now create EC instances and associate them with the existing Ue instances
		EC ec1 = new EC();
		ec1.setCode("INF3511");
		ec1.setLibelle("Reseau sans fil");
		ec1.setHeure_CM(24);
		ec1.setHeure_TD(12);
		ec1.setHeure_TP(0);
		ec1.setHeure_total(36);
		ec1.setTPE(24);
		ec1.setVHT(60);
		ec1.setUe(ue1);  // Use the existing persisted Ue instance
		ec1.setCoefficient(3);
		ecService.ajouter(ec1);


	}

	public  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}