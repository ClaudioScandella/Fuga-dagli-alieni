package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;

/**
 * 
 * Contiene la classe Giocatore, in cui viene tenuta traccia
 * di tutte le caratteristiche del personaggio nel corso della partita.
 * Ogni giocatore è identificato univocamente da un idGiocatore.
 * 
 * @author  Claudio e Patrizia
 *
 */
public class Giocatore
{
	private final int idGiocatore;
	private String nomeUtente;
	private Personaggio personaggio; 
	private String posizione; 
	private boolean adrenalina=false;
	private boolean sedativi=false;
	private boolean inVita=true;
	private String vittoria_sconfitta;
	private int portata;
	private boolean puoAttaccare;
	private boolean haMosso=false;
	private boolean puoPassare=false;
	private boolean haPassato=false;
	private boolean haAttaccato=false;
	private ArrayList<CartaOggetto> carteOggetto=new ArrayList<CartaOggetto>();
	private boolean haUcciso=false;
	private boolean scialuppaRaggiunta=false;
	public enum Personaggio {UMANO, ALIENO;}
	
//	---COSTRUTTORE-------------------------------------------------------------------------------------------------
	
	/**
	 * Costruisce una nuova istanza della classe Giocatore assegnando 
	 * il numero identificativo e il nome utente.
	 * 
	 * @param idGiocatore è il numero che identifica univocamente un giocatore
	 * @param nomeUtente è la stringa contenente il nome dell'utente
	 */
	public Giocatore(int idGiocatore, String nomeUtente)
	{
		this.nomeUtente=nomeUtente;
		this.idGiocatore=idGiocatore;
	}
	
//	---GETTER-------------------------------------------------------------------------------------------------
	
	public boolean getHaAttaccato()
	{
		return this.haAttaccato;
	}
	
	public int getPortata()
	{
		return portata;
	}
	
	public boolean getAdrenalina()
	{
		return this.adrenalina;
	}
	
	public boolean getSedativi()
	{
		return sedativi;
	}
	
	public int getIdGiocatore()
	{
		return idGiocatore;
	}
	
	public String getPosizione()
	{ 
		return posizione;
	}
	
	public Personaggio getPersonaggio()
	{ 
		return personaggio;	
	}
	
	public boolean getInVita()
	{
		return inVita;
	}
	
	public boolean getHaUcciso()
	{
		return haUcciso;
	}
	
	public boolean getScialuppaRaggiunta()
	{
		return scialuppaRaggiunta;
	}
	
	public boolean getHaMosso()
	{
		return haMosso;
	}
	
	public boolean getPuoPassare()
	{
		return puoPassare;
	}
	
	public boolean getHaPassato()
	{
		return haPassato;
	}
	
	public String getNomeUtente()
	{
		return nomeUtente;
	}
	
	/**
	 * 
	 * Ritorna la lista contenente tutte le carte oggetto possedute dal 
	 * giocatore
	 *  
	 */
	public ArrayList<CartaOggetto> getCarteOggetto()
	{
		return carteOggetto;
	}
	
	/**
	 * Tra le carte possedute dal giocatore ritorna la prima avente il
	 * tipoOggetto passato in ingresso
	 *  
	 * @param nomeOggetto è una stringa contenente il tipo di oggetto
	 * @return CartaOggetto
	 */
	public CartaOggetto getCartaOggetto(String nomeOggetto)
	{
		for(CartaOggetto oggetto : this.carteOggetto)
		{
			if(oggetto.getTipoOggetto().equals(TipoOggetto.valueOf(nomeOggetto)))
			{
				return oggetto;
			}
		}
		return null;
	}
	
	public String getVittoria_sconfitta()
	{
		return this.vittoria_sconfitta;
	}
	
	public boolean getPuoAttaccare()
	{
		return this.puoAttaccare;
	}
	
//	---SETTER-------------------------------------------------------------------------------------------------

	public void setHaAttaccato(boolean haAttaccato)
	{
		this.haAttaccato=haAttaccato;
	}
	
	public void setPortata(int portata)
	{
		this.portata=portata;
	}
	
	public void setAdrenalina(boolean adrenalina)
	{
		this.adrenalina=adrenalina;
	}
	
	public void setSedativi(boolean sedativi)
	{
		this.sedativi=sedativi;
	}
	
	public void setPosizione(String posizione)
	{
		this.posizione=posizione;
	}
	
	public void setPuoAttaccare(boolean puoAttaccare)
	{
		this.puoAttaccare=puoAttaccare;
	}
	
	/**
	 * Assegna al giocatore un ruolo, il numero di settori di cui si può spostare 
	 * ad ogni mossa e la posizione iniziale.
	 * Nel caso il personaggio sia alieno assegna il valore true alla variabile
	 * puoAttaccare; altrimenti, se il personaggio è umano, a false.
	 * (gli alieni possono sempre attaccare, gli umani solo se in possesso della
	 * carta oggetto attacco)
	 * 
	 * @param personaggio è il ruolo di un giocatore (alieno o umano)
	 * @param portata è il numero di settore di cui si può spostare
	 * @param posizione iniziale
	 */
	public void setPersonaggio(Personaggio personaggio, int portata, String posizione)
	{
		this.posizione=posizione;
		this.personaggio=personaggio;
		this.portata=portata;
		if(this.personaggio.equals(Personaggio.ALIENO))
			setPuoAttaccare(true);
		else
			setPuoAttaccare(false);
	}

	/**
	 * Aggiunge la carta oggetto passata in ingresso alla lista di carte 
	 * oggetto possedute dal giocatore
	 * 
	 * @param oggetto
	 */
	public void setCartaOggetto(CartaOggetto oggetto)
	{
		carteOggetto.add(oggetto);
	}
	
	public void setInVita(boolean stato)
	{
		inVita=stato;
	}
	
	/**
	 * Assegna il valore passato in ingresso alla variabile haUcciso e,
	 * nel caso haUcciso sia uguale a true e il personaggio sia alieno, 
	 * aggiorna la portata a 3 (nutrimento alieno)
	 * 
	 * @param stato
	 */
	public void setHaUcciso(boolean stato)
	{
		haUcciso=stato;
		if(haUcciso==true && personaggio==Personaggio.ALIENO)
			portata=3;
	}
	
	public void setScialuppaRaggiunta(boolean stato)
	{
		scialuppaRaggiunta=stato;
	}

	public void setHaMosso(boolean haMosso)
	{
		this.haMosso=haMosso;
	}
	
	public void setPuoPassare(boolean puoPassare)
	{
		this.puoPassare = puoPassare;
	}
	
	public void setHaPassato(boolean haPassato)
	{
		this.haPassato=haPassato;
	}
	
	public void setVittoria_sconfitta(String vittoria_sconfitta)
	{
		this.vittoria_sconfitta=vittoria_sconfitta;
	}
	
//	----------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo che ritorna true nel caso in cui il giocatore possieda almeno una 
	 * carta del tipo passato in ingresso, altrimenti ritorna false
	 * 
	 * @param oggetto è il nome di un tipo di oggetto
	 * @return 
	 */
	public boolean possiedeCartaOggetto(String oggetto)
	{
		for(CartaOggetto carta : this.carteOggetto)
		{
			try
			{
			if(carta.getTipoOggetto().equals(TipoOggetto.valueOf(oggetto)))
				return true;
			}
			catch(IllegalArgumentException e)
			{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Ritorna il numero di carte oggetto possedute dal giocatore
	 * 
	 * @return lunghessa della lista carteOggetto
	 */
	public int numeroCarteOggettoPossedute()
	{
		return carteOggetto.size();
	}
	
	
	/**
	 * Scarta la carta oggetto passata in ingresso rimuovendola dal mazzo degli 
	 * oggetti posseduti dal giocatore
	 * @param oggetto
	 */
	public void scartaOggetto(CartaOggetto oggetto)
	{
		this.carteOggetto.remove(oggetto);	
	}

	/**
	 * Ritorna una stringa contenente il nome di tutti i tipi di carte oggetto 
	 * possetute dal dal giocatore 
	 */
	public String stampaCarteOggetto()
	{
		String stringaDaStampare="";
		for(CartaOggetto oggetto : this.carteOggetto)
		{
			stringaDaStampare+=(oggetto.getTipoOggetto().name())+" ";
		}
		return stringaDaStampare;
	}	
}
