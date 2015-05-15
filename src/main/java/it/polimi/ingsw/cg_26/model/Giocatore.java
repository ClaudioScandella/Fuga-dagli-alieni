package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mappe.Settore;

import java.util.ArrayList;

public abstract class Giocatore
{
	private int idGiocatore=0; 
	private String nomeUtente;
	private String personaggio;
	private Settore posizione; 
	ArrayList<CartaOggetto> carteOggetto = new ArrayList<CartaOggetto>(); //max carteOggetto=3?
	ArrayList<Settore> listaMosse = new ArrayList<Settore>();	 //max turni=39?
	private boolean inVita = true;
	
	//utile solo per alieni
	private boolean haUcciso = false;
	
	//true possibile solo per umani
	private boolean scialuppaRaggiunta = false;
	
	public Giocatore(String nomeUtente, String personaggio){
		this.nomeUtente = nomeUtente;
		this.personaggio = personaggio;
		idGiocatore ++; 
	}
	
	public int getIdGiocatore(){return idGiocatore;}
	
	public String getNomeUtente() {return nomeUtente;}
	
	public Settore getPosizione(){ return posizione;}
	
	public void setPosizione(Settore posizione){
		this.posizione = posizione;
		listaMosse.add(posizione); //aggiorna listaMosse
	} 
	
	public String getPersonaggio(){ return personaggio;	}
	
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

	public ArrayList<Settore> getListaMosse(){
		return listaMosse;
	}
	
	public boolean getInVita() {return inVita;}
	
	public void morto(){
		inVita = false;
	}

	//utile solo per alieni
	public boolean getHaUcciso() {return haUcciso;}
	
	public void setHaUcciso() {
		haUcciso = true;
	}
	
	//possibile solo per umani
	public boolean getScialuppaRaggiunta(){return scialuppaRaggiunta;}
	
	public void setScialuppaRaggiunta(){
		scialuppaRaggiunta = true;
	}
}