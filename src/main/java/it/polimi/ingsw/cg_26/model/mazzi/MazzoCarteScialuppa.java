package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.util.ArrayList;
//import java.util.List;

public class MazzoCarteScialuppa {

	private ArrayList<CartaScialuppa> mazzoScialuppa = new ArrayList<>();
	private CartaScialuppa cartaScialuppa;
	
	
	public MazzoCarteScialuppa()
	{
		//creo 8 carte scialuppa: 4 verdi, 4 rosse e le aggiungo a mazzoScialuppa
		for(int i = 0; i<4; i++)
		{
			cartaScialuppa=new CartaScialuppa(Colore.VERDE);
			mazzoScialuppa.add(cartaScialuppa);
			
			cartaScialuppa=new CartaScialuppa(Colore.ROSSA);
			mazzoScialuppa.add(cartaScialuppa);
		}
	}
	
	//public ArrayList<CartaScialuppa> getMazzoCarteScialuppa(){
		//return mazzoScialuppa;
	//}
		
	public CartaScialuppa getMazzoScialuppa(int i){
		return mazzoScialuppa.get(i);
	}
	
}