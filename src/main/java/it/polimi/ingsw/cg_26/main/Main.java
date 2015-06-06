package it.polimi.ingsw.cg_26.main;

import controller.ControllerPartita;
import model.Giocatore;
import model.ModelPartita;

import java.io.*;


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
		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Stefano"));
		
		System.out.println("Partita pronta per iniziare. Attendo comando di inizio.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String comando=br.readLine();
		
		if(comando.equals("inizia"))
			System.out.println("Inizio la partita.");
			
		controllerPartita.iniziaPartita();
		controllerPartita.terminaPartita();
		
		System.out.println("La partita � terminata.");
		System.out.println("Ecco i vincitori: "+controllerPartita.getPartita().getGiocatoriVincenti().get(0).getNomeUtente());
		System.out.println("Ecco i perdenti: "+controllerPartita.getPartita().getGiocatoriPerdenti().get(0).getNomeUtente());
	}
}
