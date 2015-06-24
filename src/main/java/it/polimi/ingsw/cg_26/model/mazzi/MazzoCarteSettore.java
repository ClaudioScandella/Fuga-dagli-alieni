package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;

/**	
 * 
 * Contiene la classe MazzoCarteSettore, che permette di creare un mazzo di scarti e un mazzo 
 * di 25 carte di tipo settore:
 * - 5 carte SILENZIO senza oggetto
 * - 4 carte RUMOREaSCELTA con oggetto
 * - 4 carte RUMOREproprioSETTORE con oggetto
 * - 6 carte RUMOREaSCELTA senza oggetto
 * - 6 carte RUMOREproprioSETTORE senza oggetto
 * 
 * @author Patrizia
 *
 */
public class MazzoCarteSettore {

	private ArrayList<CartaSettore> mazzoCarteSettore=new ArrayList<>();
	private ArrayList<CartaSettore> scartiCarteSettore=new ArrayList<>();
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	/**
	 * Costruisce un mazzo con:
	 * - 5 carte SILENZIO senza oggetto
	 * - 4 carte RUMOREaSCELTA con oggetto
	 * - 4 carte RUMOREproprioSETTORE con oggetto
	 * - 6 carte RUMOREaSCELTA senza oggetto
	 * - 6 carte RUMOREproprioSETTORE senza oggetto
	 */
	public MazzoCarteSettore()
	{
		for(int i=0; i<5; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.SILENZIO, false));
		}
			
		for(int i=0; i<4; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREaSCELTA, true));
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREproprioSETTORE, true));
		}
		
		for(int i=0; i<6; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREaSCELTA, false));
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREproprioSETTORE, false));
		}
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------

	public ArrayList<CartaSettore> getMazzoCarteSettore()
	{
		return mazzoCarteSettore;
	}
	
	public ArrayList<CartaSettore> getScartiCarteSettore()
	{
		return scartiCarteSettore;
	}
	
//	---SETTER-----------------------------------------------------------------------------------------------

	public void setMazzoCarteSettore(ArrayList<CartaSettore> mazzoCarteSettore)
	{
		this.mazzoCarteSettore = mazzoCarteSettore;
	}
}
