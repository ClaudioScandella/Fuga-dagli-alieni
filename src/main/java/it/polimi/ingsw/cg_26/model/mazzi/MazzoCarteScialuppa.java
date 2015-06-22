package it.polimi.ingsw.cg_26.model.mazzi;


import java.util.ArrayList;

import model.carte.CartaScialuppa;
import model.carte.CartaScialuppa.Colore;

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
