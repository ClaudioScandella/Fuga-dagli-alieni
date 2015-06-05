package model.mazzi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ADRENALINA));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ATTACCO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ATTACCO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.LUCI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.LUCI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.TELETRASPORTO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.TELETRASPORTO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.DIFESA));
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
	
//	--------------------------------------------------------------------------------------------------
	
}
