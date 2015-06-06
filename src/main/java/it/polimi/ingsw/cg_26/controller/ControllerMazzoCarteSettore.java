package it.polimi.ingsw.cg_26.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.carte.CartaOggetto;
import model.carte.CartaSettore;
import model.mazzi.MazzoCarteSettore;

public class ControllerMazzoCarteSettore
{
	private MazzoCarteSettore mazzoCarteSettore;
//	private MazzoCarteSettore scartiCarteSettore;
	
//	--------------------------------------------------------------------------------------------------

	public ControllerMazzoCarteSettore(MazzoCarteSettore mazzoCarteSettore)
	{
		this.mazzoCarteSettore=mazzoCarteSettore;
		this.mischia();
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public CartaSettore pesca()
	{
		return mazzoCarteSettore.getMazzoCarteSettore().get(0);	
	}
	
	public void aggiungiCartaAScartiSettore(CartaSettore carta)
	{
		this.mazzoCarteSettore.getScartiCarteSettore().add(carta);
	}
	
	public int numeroCarteSettoreNelMazzo()
	{
		return mazzoCarteSettore.getMazzoCarteSettore().size();
	}
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteSettore.getMazzoCarteSettore(), new Random(seed));
    }
	
	public void rigeneraMazzo(){
		mazzoCarteSettore.setMazzoCarteSettore((ArrayList<CartaSettore>) mazzoCarteSettore.getScartiCarteSettore().clone());
		mazzoCarteSettore.getScartiCarteSettore().clear();
		this.mischia();
	}
	
	public void rimuoviCarta()
	{
		mazzoCarteSettore.getMazzoCarteSettore().remove(0);
	}
	
	public void mostraMazzo()
	{
        for (CartaSettore carta : this.mazzoCarteSettore.getMazzoCarteSettore())
        {
            System.out.println(carta.getTipoCartaSettore());
        }
    }
	
	public CartaSettore pescaCartaSettore()
	{
		CartaSettore carta;
		carta=this.pesca();
		this.rimuoviCarta();
		this.aggiungiCartaAScartiSettore(carta);
		return carta;
	}

}











































