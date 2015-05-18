package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.mappe.Settore;

import java.util.ArrayList;

public class Partita {
	
	private int idPartita;
	private int numeroGiocatori;
	private int turno; //indica il giocatore 
	private int giro = 0; //numero turni di ogni giocatore
						  //quando tutti i giocatori hanno giocato: giro++
	private String tipoMappa;
	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	private Object[][][] log = new Object [39][8][3]; //[turno][giocatore][0] = mosse
													  //[turno][giocatore][1] = posizioneDichiarata
													  //[turno][giocatore][2] = haPescatoCarta
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
	
	public void setGiocatori(Giocatore nuovoGiocatore){
		giocatori.add(nuovoGiocatore);
	}
	
	public ArrayList<Giocatore> getGiocatori(){
		return giocatori;
	}
	
	public int getGiro(){
		return giro;
	}
	
	//setter log
	public void setLog(int turno, int idGiocatore, Settore spostamento){
		log[turno][idGiocatore][0] = spostamento;
	}
	
	public void setLogPosizioneDichairata(int turno, int idGiocatore, Settore posizioneDichiarata){
		log[turno][idGiocatore][1] = posizioneDichiarata;
	}
	
	//pescaCarta è true se il giocatore ha pescato una carta
	public void setLog(int turno, int idGiocatore, boolean pescaCarta){
		log[turno][idGiocatore][2] = pescaCarta;
	}
	
	//getter log - serve???
	public Object[][][] getLog(){
		return log;
	}
	
	public Object posizioneDichiarata(int turno, int idGiocatore){
		return log[turno][idGiocatore][1];
	}
	
	public Object haPescato(int turno, int idGiocatore){
		return log[turno][idGiocatore][2];
	}
	
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
