package it.polimi.ingsw.cg_26.controller.controllerPartita;

import java.util.ArrayList;

import it.polimi.ingsw.cg_26.model.*;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;

public class InizializzaPartita {

	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	private int idPartita;
	private static int counterP = 0;
	private int idGiocatore;
	private static int counterG = 0;
	private String[] mappa = new String[2];
	private static int i = 0;
	private Giocatore g1;
	private Personaggio ALIENO, UMANO;
	private Partita p1;
	
	//controlla la lunghezza dell'ArrayList giocatori: 
	//se è uguale a 1: crea una partita e aggiunge il giocatore
	//se è minore o uguale a 8: aggiunge il giocatore
	//se è uguale a 8: inizia la partita
	
	public void aggiungiGiocatore(Utente utente ){
		mappa[0] = "Galilei";
		mappa[1] = "Fermi";
		mappa[2] = "Galvani";
		
		if(giocatori.size() == 1){
			creaPartita();
		}
		
		if(giocatori.size()<=8){
			idGiocatore = counterG++;
			g1 = new Giocatore(utente.getNomeUtente(),idGiocatore, idPartita);
			giocatori.add(g1);
			if((g1.getIdGiocatore() % 2)==0){
				g1.setPersonaggio(ALIENO);
			}
			else if((g1.getIdGiocatore() % 2) == 1){
				g1.setPersonaggio(UMANO);
			}
			
		}
		if(giocatori.size() == 8){
			iniziaPartita(p1);
		}
	}
		private void creaPartita(){
			idPartita = counterP++;
			p1 = new Partita(idPartita,giocatori,mappa[i % 3]);
			i++;
			
			//creo i mazzi
			//creo la mappa
			//posiziono i personaggi( ad ogni persoaggio assegno una posizione e aggiorno il log)
			
	}
		
		public void iniziaPartita(Partita partita){
			//estrarre un numero da 0 a 7 per decidere il giocatore iniziale
			//p1.setTurno(numero);
		}

}
	
