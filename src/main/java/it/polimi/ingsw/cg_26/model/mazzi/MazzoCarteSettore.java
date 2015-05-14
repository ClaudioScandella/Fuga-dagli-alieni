package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;

import java.util.ArrayList;

public class MazzoCarteSettore {

	ArrayList<CartaSettore> mazzoCarteSettore = new ArrayList<CartaSettore>();

	public void setMazzo(){
		for(int i=0; i<5; i++){
			mazzoCarteSettore.add(new CartaSettore("silenzio", false));
		}
		
		for(int i=0; i<4; i++){
			mazzoCarteSettore.add(new CartaSettore("rumore", true));
		}
		
		for(int i=0; i<6; i++){
			mazzoCarteSettore.add(new CartaSettore("rumore", false));
		}
		
		for(int i=0; i<4; i++){
			mazzoCarteSettore.add(new CartaSettore("rumoreInSettore", true));
		}
		
		for(int i=0; i<6; i++){
			mazzoCarteSettore.add(new CartaSettore("rumoreInSettore", false));
		}
	}
}
