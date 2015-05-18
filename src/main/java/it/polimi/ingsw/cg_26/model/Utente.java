package it.polimi.ingsw.cg_26.model;

import java.util.ArrayList;

public class Utente {
	
	private String nomeUtente;
	private ArrayList<Integer> idPartite = new ArrayList<Integer>();
	
	public Utente(String nomeUtente){
		this.nomeUtente = nomeUtente;
	}
	
	public String getNomeUtente(){
		return nomeUtente;
	}
	
	public ArrayList<Integer> getIdPartite(){
		return idPartite;
	}
	
	//aggiunge una partita alla lista
	public void setIdPartite(int idPartita){
		idPartite.add(idPartita);
	}
}
