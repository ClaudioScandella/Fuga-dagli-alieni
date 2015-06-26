package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;

import java.util.Collections;
import java.util.Random;

/**
 * Contiene la classe ControllerMazzoCarteScialuppa, la cui istanza permette di
 * gestire il mazzo di carte scialuppa indicato dalla variabile mazzoCarteSettore.
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerMazzoCarteScialuppa
{
	private MazzoCarteScialuppa mazzoCarteScialuppa;

//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	
	public ControllerMazzoCarteScialuppa(MazzoCarteScialuppa mazzoCarteScialuppa)
	{
		this.mazzoCarteScialuppa=mazzoCarteScialuppa;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	/**
	 * Permette di mischiare la lista di carte scialuppa.
	 */
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteScialuppa.getMazzoCarteScialuppa(), new Random(seed));
    }
	
	/**
	 * Mischia la lista di carte scialuppa e ritorna la carta scialuppa in 
	 * posizione zero
	 * 
	 * @return
	 */
	public CartaScialuppa pesca()
	{
		this.mischia();
		return mazzoCarteScialuppa.getMazzoCarteScialuppa().get(0);
	}
	
	/**
	 * Rimuove la carta in posizione zero della lista di carte settore
	 */
	public void rimuoviPrimaCarta()
	{
		mazzoCarteScialuppa.getMazzoCarteScialuppa().remove(0);
	}
}
