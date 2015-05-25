package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;
import java.util.List;

public class MazzoCarteSettore {

	private List<CartaSettore> mazzoCarteSettore=new ArrayList<>();

		//crea tutte le carte e le inserisce in ArrayList carteSettore
		
		//creo le carte Silenzio
	public MazzoCarteSettore(){
		for(int i=0; i<5; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.SILENZIO, false));
		}
			
		for(int i=0; i<4; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMORE, true));
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREinSETTORE, true));
		}
		
		for(int i=0; i<6; i++){
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMORE, false));
			mazzoCarteSettore.add(new CartaSettore(TipoSettore.RUMOREinSETTORE, false));
		}
	}
	
	public CartaSettore getMazzoSettore(int i){
		return mazzoCarteSettore.get(i);
	}
}
