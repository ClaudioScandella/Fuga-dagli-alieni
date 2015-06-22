package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;

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
	
//	----------------------------------------------------------------------------------------------------
	
	public Giocatore(int idGiocatore, String nomeUtente)
	{
		this.nomeUtente=nomeUtente;
		this.idGiocatore=idGiocatore;
	}
	
//	----------------------------------------------------------------------------------------------------
	
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
	
	public ArrayList<CartaOggetto> getCarteOggetto()
	{
		return carteOggetto;
	}
	
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
	
//	----------------------------------------------------------------------------------------------------

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
	
	public int numeroCarteOggettoPossedute()
	{
		return carteOggetto.size();
	}
	
	public void scartaOggetto(CartaOggetto oggetto)
	{
		this.carteOggetto.remove(oggetto);	
	}

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
