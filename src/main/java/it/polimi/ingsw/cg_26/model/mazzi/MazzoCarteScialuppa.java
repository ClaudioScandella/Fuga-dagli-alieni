package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.util.ArrayList;


public class MazzoCarteScialuppa {

	private ArrayList<CartaScialuppa> mazzoCarteScialuppa;
	
//	--------------------------------------------------------------------------------------------------
	
	public MazzoCarteScialuppa()
	{
		mazzoCarteScialuppa=new ArrayList<>();
		for(int i = 0; i<4; i++)
		{
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.ROSSA));
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.VERDE));
		}
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public ArrayList<CartaScialuppa> getMazzoCarteScialuppa()
	{
		return this.mazzoCarteScialuppa;
	}
}
