//fare metodo possiedoCartaOggetto!!
package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazzoOggetti
{

	private ArrayList<CartaOggetto> mazzoCarteOggetto = new ArrayList<>();
	private ArrayList<CartaOggetto> scartiOggetto = new ArrayList<>();

	public MazzoOggetti(){
		
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ADRENALINA));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ADRENALINA));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ATTACCO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.ATTACCO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.LUCI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.LUCI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.TELETRASPORTO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.TELETRASPORTO));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.SEDATIVI));
		mazzoCarteOggetto.add(new CartaOggetto(TipoOggetto.DIFESA));

	}
	//per pescare
	public CartaOggetto pesca(){
		return mazzoCarteOggetto.get(0);	
	}
	
	//ritorna il numero di oggetti nel mazzo
	public int lunghezzaListaOggetti(){
		return mazzoCarteOggetto.size();
	}
	
	public void aggiungiScartiOggetto(CartaOggetto carta){
		scartiOggetto.add(carta);
	}
	
	public ArrayList<CartaOggetto> getMazzoCarteOggetto()
	{
		return this.mazzoCarteOggetto;
	}
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteOggetto, new Random(seed));
    }
	
	public void rigeneraMazzo(){
		mazzoCarteOggetto = (ArrayList<CartaOggetto>) scartiOggetto.clone();
		scartiOggetto.clear();
	}
	
	public void mostraMazzo()
	{
        for (CartaOggetto carta : this.mazzoCarteOggetto)
        {
            System.out.println(carta.getTipoOggetto());
        }
    }
	
	public void rimuoviCarta()
	{
		mazzoCarteOggetto.remove(0);
	}

}





















































