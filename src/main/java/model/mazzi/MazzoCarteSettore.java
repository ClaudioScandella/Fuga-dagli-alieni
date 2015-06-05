package model.mazzi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.carte.CartaSettore;
import model.carte.CartaSettore.TipoSettore;

public class MazzoCarteSettore {

	private ArrayList<CartaSettore> mazzoCarteSettore=new ArrayList<>();
	private ArrayList<CartaSettore> scartiCarteSettore=new ArrayList<>();
	
//	--------------------------------------------------------------------------------------------------

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
