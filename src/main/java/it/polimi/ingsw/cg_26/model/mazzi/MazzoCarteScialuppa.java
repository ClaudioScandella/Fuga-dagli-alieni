package it.polimi.ingsw.cg_26.model.mazzi;


import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.util.ArrayList;

/**
 * 	Contiene la classe MazzoCarteScialuppa, che permette di costruire un mazzo di 6 carte 
 * di tipo scialuppa:
 * - 3 verdi
 * - 3 rosse
 * 
 * @author Claudio e Patrizia
 *
 */
public class MazzoCarteScialuppa {

	private ArrayList<CartaScialuppa> mazzoCarteScialuppa;
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	/**
	 * Costruisce un mazzo con:
	 * - 3 verdi
	 * - 3 rosse
	 */
	public MazzoCarteScialuppa()
	{
		mazzoCarteScialuppa=new ArrayList<>();
		for(int i = 0; i<3; i++)
		{
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.ROSSA));
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.VERDE));
		}
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------
	
	public ArrayList<CartaScialuppa> getMazzoCarteScialuppa()
	{
		return this.mazzoCarteScialuppa;
	}
}
