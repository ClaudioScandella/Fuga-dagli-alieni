package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;

public class MazzoCarteSettore {

	private ArrayList<CartaSettore> mazzoCarteSettore=new ArrayList<>();
	private ArrayList<CartaSettore> scartiCarteSettore=new ArrayList<>();
	
//	--------------------------------------------------------------------------------------------------

	public MazzoCarteSettore()
	{
		for(int i=0; i<5; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.SILENZIO, true));//DEVE ESSERE FALSE
		}
			
		for(int i=0; i<4; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREaSCELTA, true));
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREproprioSETTORE, true));
		}
		
		for(int i=0; i<6; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREaSCELTA, true));//DEVE ESSERE FALSE
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREproprioSETTORE, true));//DEVE ESSERE FALSE
		}
	}
	
//	--------------------------------------------------------------------------------------------------

	public ArrayList<CartaSettore> getMazzoCarteSettore()
	{
		return mazzoCarteSettore;
	}
	
	public ArrayList<CartaSettore> getScartiCarteSettore()
	{
		return scartiCarteSettore;
	}
	
//	--------------------------------------------------------------------------------------------------

	public void setMazzoCarteSettore(ArrayList<CartaSettore> mazzoCarteSettore)
	{
		this.mazzoCarteSettore = mazzoCarteSettore;
	}
}
