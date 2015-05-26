package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.util.ArrayList;
//import java.util.List;
import java.util.Collections;
import java.util.Random;

public class MazzoCarteScialuppa {

	private ArrayList<CartaScialuppa> mazzoCarteScialuppa = new ArrayList<>();
	
	
	public MazzoCarteScialuppa()
	{
		//creo 8 carte scialuppa: 4 verdi, 4 rosse e le aggiungo a mazzoScialuppa
		for(int i = 0; i<4; i++)
		{
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.ROSSA));
			mazzoCarteScialuppa.add(new CartaScialuppa(Colore.VERDE));
		}
	}
	
	//public ArrayList<CartaScialuppa> getMazzoCarteScialuppa(){
		//return mazzoScialuppa;
	//}
		
	public CartaScialuppa getMazzoScialuppa(int i){
		return mazzoCarteScialuppa.get(i);
	}
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteScialuppa, new Random(seed));
    }
	
	public CartaScialuppa pesca()
	{
		return mazzoCarteScialuppa.get(0);
	}
	
	public void rimuoviPrimaCarta()
	{
		mazzoCarteScialuppa.remove(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}