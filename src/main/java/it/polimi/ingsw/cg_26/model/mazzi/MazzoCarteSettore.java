package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;
import java.util.List;

public class MazzoCarteSettore {

	private List<CartaSettore> mazzoSettori=new ArrayList<>();
	private CartaSettore cartaSettore;
	

	public MazzoCarteSettore()
	{
		//crea tutte le carte e le inserisce in ArrayList carteSettore
		
		//creo le carte Silenzio
		cartaSettore = new CartaSettore(TipoSettore.SILENZIO, false);
		for(int i=0;i<5;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		
		//creo le carte RumoreAscelta
		cartaSettore = new CartaSettore(TipoSettore.RUMORE, true);
		for(int i=0;i<4;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		cartaSettore = new CartaSettore(TipoSettore.RUMORE, false);
		for(int i=0;i<6;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		
		//creo le carte RumoreNelSettore
		cartaSettore = new CartaSettore(TipoSettore.RUMOREinSETTORE, true);
		for(int i=0;i<4;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		cartaSettore = new CartaSettore(TipoSettore.RUMOREinSETTORE, false);
		for(int i=0;i<6;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
	}
}