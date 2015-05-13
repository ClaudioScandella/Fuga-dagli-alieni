package it.polimi.ingsw.cg_26;

import java.util.*;

public abstract class Giocatore
{
	private String nomeUtente;
	private Settore posizione;
	private Collection<Carta> carteOggetto;
	private boolean inVita=true;
	//private enum personaggio {UMANO, ALIENO};
	int numeroIdentificativo;
	
	

	//setters
	//getters
	
	//public abstract boolean mossaValida();
	public abstract void muovi();
	
	public abstract void pescaCartaSettore();
	//public abstract void scartaCarta();
	
	public void passaTurno()
	{
		
	}
}