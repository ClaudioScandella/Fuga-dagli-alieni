package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;

import java.util.Collections;
import java.util.Random;


public class ControllerMazzoCarteScialuppa
{
	private MazzoCarteScialuppa mazzoCarteScialuppa;

//	--------------------------------------------------------------------------------------------------
	
	public ControllerMazzoCarteScialuppa(MazzoCarteScialuppa mazzoCarteScialuppa)
	{
		this.mazzoCarteScialuppa=mazzoCarteScialuppa;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteScialuppa.getMazzoCarteScialuppa(), new Random(seed));
    }
	
	public CartaScialuppa pesca()
	{
		this.mischia();
		return mazzoCarteScialuppa.getMazzoCarteScialuppa().get(0);
	}
	
	public void rimuoviPrimaCarta()
	{
		mazzoCarteScialuppa.getMazzoCarteScialuppa().remove(0);
	}
}
