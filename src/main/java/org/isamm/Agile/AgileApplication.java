package org.isamm.Agile;

import java.util.Date;

import org.isamm.Agile.dao.*;
import org.isamm.Agile.entities.Departement;
import org.isamm.Agile.entities.Entreprise;
import org.isamm.Agile.entities.Equipe;
import org.isamm.Agile.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
//@ComponentScan({"com.delivery.service","com.delivery.request"})
public class AgileApplication implements CommandLineRunner{
	
@Autowired
private ProjetDao pdao ;
@Autowired
private EntrepriseDao edao ;
@Autowired
private DepartementDao ddao ;
@Autowired
private EquipeDao eqdao;
/*@Autowired
private RepositoryRestConfiguration restcofiguration ;*/

	public static void main(String[] args) {
		SpringApplication.run(AgileApplication.class, args); }
	@Override
	public void run(String... args ) throws Exception {
		
		
		
		Entreprise e = edao.save(new Entreprise("delice", "eeya"));
		Departement d = ddao.save(new Departement("informatique", 10));
		Equipe eq = eqdao.save(new Equipe("equipe2"));
		pdao.save(new Projet("p1", new Date(),"quali", eq, d, e));
		  
		
		
		
		
		

	}

}
