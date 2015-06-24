package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;

/**
 * Contiene la classe MazzoCarteOggetto, che permette di creare un mazzo di scarti e 
 * un mazzo di 12 carte di tipo oggetto:
 * - 2 carte adrenalina
 * - 2 carte attacco
 * - 2 carte luci
 * - 2 carte teletrasporto
 * - 3 carte sedativi
 * - 1 carta difesa
 * 
 * @author Claudio e Patrizia
 *
 */
public class MazzoCarteOggetto
{
	private ArrayList<CartaOggetto> mazzoCarteOggetto=new ArrayList<>();
	private ArrayList<CartaOggetto> scartiCarteOggetto=new ArrayList<>();
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	/**
	 * Costruisce un mazzo con:
	 * - 2 carte adrenalina
	 * - 2 carte attacco
	 * - 2 carte luci
	 * - 2 carte teletrasporto
	 * - 3 carte sedativi
	 * - 1 carta difesa
	 */
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
	
//	---GETTER-----------------------------------------------------------------------------------------------
	
	public ArrayList<CartaOggetto> getMazzoCarteOggetto()
	{
		return this.mazzoCarteOggetto;
	}
	
	public ArrayList<CartaOggetto> getScartiCarteOggetto() {
		return this.scartiCarteOggetto;
	}
	
//	---SETTER-----------------------------------------------------------------------------------------------
	
	public void setMazzoCarteOggetto(ArrayList<CartaOggetto> nuovoMazzoCarteOggetto)
	{
		this.mazzoCarteOggetto = nuovoMazzoCarteOggetto;
	}	
}
