package it.polimi.ingsw.cg_26.model;

import java.util.ArrayList;

public class Partita {
	
	private int idPartita;
	private int numeroGiocatori;
	private int turno; //indica il giocatore 
	private int giro = 0; //numero turni di ogni giocatore
						  //quando tutti i giocatori hanno giocato: giro++
	private String tipoMappa;
	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	private int[][][] log; //tipo??
	private int numeroUmaniMorti = 0;
	private int numeroAlieniMorti = 0;
	private int numeroUmaniScappati = 0;

	public Partita(int idPartita, ArrayList<Giocatore> giocatori, String tipoMappa ){
		this.idPartita = idPartita;
		this.giocatori = giocatori;
		this.tipoMappa = tipoMappa;
		numeroGiocatori = giocatori.size();
		idPartita++;
	}
	
	public int getIdPartita(){
		return idPartita;
	}
	
	public int getNumeroGiocatori(){
		return numeroGiocatori;
	}
	
	public int getTurno(){
		return turno;
	}
	
	public void setTurno(int idGiocatore){
		turno = idGiocatore;
	}
	
	public String getTipoMappa(){
		return tipoMappa;
	}
	
	public ArrayList<Giocatore> getGiocatori(){
		return giocatori;
	}
	
	
	public int getGito(){
		return giro;
	}
	
	//aggiungere setter e getter per il log
	
	public int getNumeroUmaniMorti(){
		return numeroUmaniMorti;
	}
	
	public void setNumeroUmaniMorti(int umaniUccisi){
		numeroUmaniMorti += umaniUccisi;
	}
	
	public int getNumeroAlieniMorti(){
		return numeroAlieniMorti;
	}
	
	public void setNumeroAlieniMorti(int alieniUccisi){
		numeroAlieniMorti += alieniUccisi;
	}
	
	public int getNumeroUmaniScappati(){
		return numeroUmaniScappati;
	}
	
	public void setNumeroUmaniScappati(){
		numeroUmaniScappati++;
	}
	
}
