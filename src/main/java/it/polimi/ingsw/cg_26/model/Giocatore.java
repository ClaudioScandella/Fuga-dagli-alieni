package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;

import java.util.ArrayList;

public class Giocatore
{
	private final int idGiocatore;
	private int idPartita;
	private String nomeUtente;
	private Personaggio personaggio;
	private String posizione; 
	ArrayList<CartaOggetto> carteOggetto = new ArrayList<CartaOggetto>(); //max carteOggetto=3?
	ArrayList<String> listaMosse = new ArrayList<String>();	 //max turni=39?
	private boolean inVita = true;
	
	//utile solo per alieni
	private boolean haUcciso = false;
	
	//true possibile solo per umani
	private boolean scialuppaRaggiunta = false;
	
	public enum Personaggio { UMANO, ALIENO;}
	
	public Giocatore(String nomeUtente, int idGiocatore, int idPartita){
		this.nomeUtente = nomeUtente;
		this.idPartita = idPartita;
		this.idGiocatore = idGiocatore;
	}
	
	public int getIdPartita(){
		return idPartita;
	}
	
	public int getIdGiocatore(){
		return idGiocatore;
		}
	
	public String getNomeUtente() {
		return nomeUtente;
		}
	
	public String getPosizione(){ 
		return posizione;
	}
	
	public void setPosizione(String posizione){
		this.posizione = posizione;
		listaMosse.add(posizione); //aggiorna listaMosse
	} 
	
	public Personaggio getPersonaggio(){ 
		return personaggio;	
		}
	
	public void setPersonaggio(Personaggio personaggio){
		this.personaggio = personaggio;
	}
	
	//aggiunge una carta oggetto alla lista,
	//però poi nel Controller, quando il giocatore pesca la carta,
	//dobbiamo ricordarci di controllare che gli oggetti nella
	//lista siano meno di 4!
	
	public void setCartaOggetto(CartaOggetto oggetto){
		carteOggetto.add(oggetto);
	}
	
	//controlla che in carteOggetto sia presente la carta
	public boolean possiedoCartaOggetto(CartaOggetto oggetto){
		return carteOggetto.contains(oggetto);
	}
	
	//ritorna il numero di carte oggetto presenti nella lista
	public int numeroCarteOggetto(){
		return carteOggetto.size();
	}
	
	public void usaCartaOggetto(CartaOggetto oggetto){
		//remove(oggetto) rimuove la prima occorrenza trovata dell'oggetto
		//che gli passiamo e ritorna true
		carteOggetto.remove(oggetto);	
	}

	public ArrayList<String> getListaMosse(){
		return listaMosse;
	}
	
	//ritorna la mossa fatta all'i-esimo turno del giocatore
	// i-esima stringa nell'ArrayList listamosse
	public String getMossaIesima(int i){
		return listaMosse.get(i);
	}
	
	public boolean getInVita() {
		return inVita;
	}
	
	public void morto(){
		inVita = false;
	}

	//utile solo per alieni
	public boolean getHaUcciso() {
		return haUcciso;
	}
	
	public void setHaUcciso() {
		haUcciso = true;
	}
	
	//possibile solo per umani
	public boolean getScialuppaRaggiunta()
	{
		return scialuppaRaggiunta;
	}
	
	public void setScialuppaRaggiunta(){
		scialuppaRaggiunta = true;
	}


}