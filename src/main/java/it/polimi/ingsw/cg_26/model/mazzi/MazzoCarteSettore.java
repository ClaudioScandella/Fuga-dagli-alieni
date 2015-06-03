package it.polimi.ingsw.cg_26.model.mazzi;

//import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazzoCarteSettore {

	private List<CartaSettore> mazzoCarteSettore=new ArrayList<>();
	private ArrayList<CartaSettore> scartiSettore = new ArrayList<>();

		//crea tutte le carte e le inserisce in ArrayList carteSettore
		
		//creo le carte Silenzio
	public MazzoCarteSettore(){
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
	
	public CartaSettore getMazzoSettore(int i){
		return mazzoCarteSettore.get(i);
	}
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteSettore, new Random(seed));
    }
	
	public CartaSettore pesca(){
		return mazzoCarteSettore.get(0);	
	}
	
	//ritorna il numero di oggetti nel mazzo
		public int lunghezzaListaSettori(){
			return mazzoCarteSettore.size();
		}
		
		public void aggiungiScartiSettore(CartaSettore carta){
			scartiSettore.add(carta);
		}
	
		public void rigeneraMazzo(){
			mazzoCarteSettore = (ArrayList<CartaSettore>) scartiSettore.clone();
			scartiSettore.clear();
		}
		
		public void rimuoviCarta()
		{
			mazzoCarteSettore.remove(0);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
