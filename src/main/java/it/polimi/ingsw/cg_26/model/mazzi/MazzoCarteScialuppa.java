package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;

import java.util.ArrayList;

public class MazzoCarteScialuppa {

	ArrayList<CartaScialuppa> mazzoCarteScialuppa = new ArrayList<CartaScialuppa>();

	public void setMazzo(){
		for(int i=0; i<4; i++){
			mazzoCarteScialuppa.add(new CartaScialuppa("rossa"));
		}
		
		for(int i=0; i<4; i++){
			mazzoCarteScialuppa.add(new CartaScialuppa("verde"));
		}
	}
}
