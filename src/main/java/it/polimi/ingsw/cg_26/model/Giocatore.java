package it.polimi.ingsw.cg_26.model;

import java.util.ArrayList;

import model.carte.CartaOggetto;
import model.carte.CartaOggetto.TipoOggetto;

public class Giocatore
{
	private final int idGiocatore;
//	private ModelPartita partita;
	private String nomeUtente;
	private Personaggio personaggio;
	private String posizione; 
	private boolean adrenalina=false;
	private boolean sedativi=false;
	private ArrayList<CartaOggetto> carteOggetto=new ArrayList<CartaOggetto>(); //max carteOggetto=3?
//	private ArrayList<String> listaMosse=new ArrayList<String>();	 //max turni=39?
	private boolean inVita=true;
	private String vittoria_sconfitta;
	private int portata;
	private boolean haMosso=false;
	private boolean puoPassare=false;
	private boolean haPassato=false;

	//utile solo per alieni
	private boolean haUcciso=false;
	
	//true possibile solo per umani
	private boolean scialuppaRaggiunta=false;
	
	public enum Personaggio {UMANO, ALIENO;}
	
//	----------------------------------------------------------------------------------------------------
	
	public Giocatore(int idGiocatore, String nomeUtente)
	{
		this.nomeUtente=nomeUtente;
		this.idGiocatore=idGiocatore;
	}
	
//	----------------------------------------------------------------------------------------------------
	
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
	
//	public ArrayList<String> getListaMosse()
//	{
//		return listaMosse;
//	}
	
	//ritorna la mossa fatta all'i-esimo turno del giocatore
	// i-esima stringa nell'ArrayList listamosse
//	public String getMossaIesima(int i)
//	{
//		return listaMosse.get(i);
//	}
	
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
	
	public ArrayList<CartaOggetto> getCarteOggetto() {
		return carteOggetto;
	}
	
	public CartaOggetto getCartaOggetto(String nomeOggetto)
	{
		for(int i=0;i<this.carteOggetto.size();i++)
		{
			if(this.carteOggetto.get(i).getTipoOggetto()==TipoOggetto.valueOf(nomeOggetto))
			{
				return this.carteOggetto.get(i);
			}
		}
		return null;
	}
	
	public String getVittoria_sconfitta()
	{
		return this.vittoria_sconfitta;
	}
	
//	----------------------------------------------------------------------------------------------------

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
//		listaMosse.add(posizione); //aggiorna listaMosse
	}
	
	public void setPersonaggio(Personaggio personaggio, int portata, String posizione)
	{
		this.posizione=posizione;
		this.personaggio=personaggio;
		this.portata=portata;
	}

	public void setCartaOggetto(CartaOggetto oggetto)
	{
		carteOggetto.add(oggetto);
	}
	
	public void setInVita(boolean stato)
	{
		inVita=stato;
	}
	
	public void setHaUcciso(boolean stato)
	{
		haUcciso=stato;
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
	
	public boolean possiedeCartaOggetto(String oggetto)
	{
		for(CartaOggetto carta : this.carteOggetto)
		{
			try{
			if(carta.getTipoOggetto().equals(TipoOggetto.valueOf(oggetto)))
				return true;
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public int numeroCarteOggettoPossedute()
	{
		return carteOggetto.size();
	}
	
	public void scartaOggetto(CartaOggetto oggetto)
	{
		carteOggetto.remove(oggetto);	
	}
}
