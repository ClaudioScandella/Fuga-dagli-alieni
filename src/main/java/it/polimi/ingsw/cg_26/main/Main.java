package it.polimi.ingsw.cg_26.main;

import it.polimi.ingsw.cg_26.controller.ControllerPartita;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static int idPartita=0;
	private static String nomeMappa="fermi";
	private static int idGiocatore=0;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Inizializzo la partita.");
		ModelPartita modelPartita=new ModelPartita(idPartita, nomeMappa);
		ControllerPartita controllerPartita=new ControllerPartita(modelPartita);
//		Giocatore giocatore=new Giocatore(idGiocatore, idPartita);		
		idPartita++;
		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Claudio"));
		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Diego"));
//		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Barbara"));
//		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Andrea"));
		
		System.out.println("Partita pronta per iniziare. Attendo comando di inizio.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando=br.readLine();
		
		if(comando.equals("inizia"))
			System.out.println("Inizio la partita.");
			
		controllerPartita.iniziaPartita();
		controllerPartita.terminaPartita();
		controllerPartita.stampaVincitoriEPerdenti();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}