package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteOggetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class ControllerMazzoCarteOggetto
{
	private MazzoCarteOggetto mazzoCarteOggetto;
//	private MazzoCarteOggetto scartiCarteOggetto;
	
//	--------------------------------------------------------------------------------------------------
	
	public ControllerMazzoCarteOggetto(MazzoCarteOggetto mazzoCarteOggetto)
	{
		this.mazzoCarteOggetto=mazzoCarteOggetto;
		this.mischia();
	}
	
//	--------------------------------------------------------------------------------------------------

	public CartaOggetto pesca(){
		return mazzoCarteOggetto.getMazzoCarteOggetto().get(0);
	}
	
	public void aggiungiCartaAScartiOggetto(CartaOggetto carta){
		mazzoCarteOggetto.getScartiCarteOggetto().add(carta);
	}
	
	public int numeroCarteOggettoNelMazzo(){
		return mazzoCarteOggetto.getMazzoCarteOggetto().size();
	}
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteOggetto.getMazzoCarteOggetto(), new Random(seed));
    }
	
	public void rigeneraMazzo()
	{
		mazzoCarteOggetto.setMazzoCarteOggetto((ArrayList<CartaOggetto>) mazzoCarteOggetto.getScartiCarteOggetto().clone());
		mazzoCarteOggetto.getScartiCarteOggetto().clear();
		this.mischia();
	}
	
	public void rimuoviCarta()
	{
		mazzoCarteOggetto.getMazzoCarteOggetto().remove(0);
	}
	
	public void mostraMazzo()
	{
        for (CartaOggetto carta : this.mazzoCarteOggetto.getMazzoCarteOggetto())
        {
            System.out.println(carta.getTipoOggetto());
        }
    }
	
	public CartaOggetto pescaCartaOggetto()
	{
		CartaOggetto carta;
		carta=this.pesca();
		this.rimuoviCarta();
		return carta;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
