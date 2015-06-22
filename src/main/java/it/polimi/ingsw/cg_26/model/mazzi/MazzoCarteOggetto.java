package it.polimi.ingsw.cg_26.model.mazzi;

import java.util.ArrayList;

import model.carte.CartaOggetto;
import model.carte.CartaOggetto.TipoOggetto;

public class MazzoCarteOggetto
{
	private ArrayList<CartaOggetto> mazzoCarteOggetto=new ArrayList<>();
	private ArrayList<CartaOggetto> scartiCarteOggetto=new ArrayList<>();
	
//	--------------------------------------------------------------------------------------------------

	public MazzoCarteOggetto()
	{
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ADRENALINA));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.LUCI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public ArrayList<CartaOggetto> getMazzoCarteOggetto()
	{
		return this.mazzoCarteOggetto;
	}
	
	public ArrayList<CartaOggetto> getScartiCarteOggetto() {
		return this.scartiCarteOggetto;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public void setMazzoCarteOggetto(ArrayList<CartaOggetto> nuovoMazzoCarteOggetto)
	{
		this.mazzoCarteOggetto = nuovoMazzoCarteOggetto;
	}	
}
