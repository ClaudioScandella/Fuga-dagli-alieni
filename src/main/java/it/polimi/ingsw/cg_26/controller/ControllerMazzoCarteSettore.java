package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteSettore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** 
 * Contiene la classe ControllerMazzoCarteSettore, la cui istanza permette di
 * gestire il mazzo di carte settore indicato dalla variabile mazzoCarteSettore.
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerMazzoCarteSettore
{
	private MazzoCarteSettore mazzoCarteSettore;
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	/**
	 * Costruisce un controllerMazzoCarteSettore attribuendo alla variabile mazzoCarteSettore
	 * l'oggetto MazzoCarteSettore che gli viene passato in ingresso. Successivamente
	 * chiama il metodo mischia(), che permette di mischiare le carte del mazzo.
	 * 
	 * @param mazzoCarteSettore
	 */
	public ControllerMazzoCarteSettore(MazzoCarteSettore mazzoCarteSettore)
	{
		this.mazzoCarteSettore=mazzoCarteSettore;
		this.mischia();
	}
	
//	--------------------------------------------------------------------------------------------------
	/**
	 * Permette di pescare la carta in posizione zero dell'arraylist contenente il mazzo
	 * di carte settore.
	 * Ritorna la CartaSettore pescata.
	 * 
	 * @return 
	 */
	public CartaSettore pesca()
	{
		return mazzoCarteSettore.getMazzoCarteSettore().get(0);	
	}
	
	/**
	 * Aggiunge la carta passata in ingresso alla lista degli scarti.
	 * 
	 * @param carta CartaSettore che voglio scartare
	 */
	public void aggiungiCartaAScartiSettore(CartaSettore carta)
	{
		this.mazzoCarteSettore.getScartiCarteSettore().add(carta);
	}
	
	/**
	 * Ritorna il numero di carte settore ancora presenti nel mazzo.
	 * 
	 * @return
	 */
	public int numeroCarteSettoreNelMazzo()
	{
		return mazzoCarteSettore.getMazzoCarteSettore().size();
	}
	
	/**
	 * Mischia la lista di carte settore.
	 */
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteSettore.getMazzoCarteSettore(), new Random(seed));
    }
	
	/**
	 * Questo metodo fa una copia della lista degli scarti e li copia nella lista del mazzo
	 * di carte settore.
	 * Successivamente svuota il mazzo degli scarti e mischia il mazzo rigenerato.
	 */
	public void rigeneraMazzo(){
		mazzoCarteSettore.setMazzoCarteSettore((ArrayList<CartaSettore>) mazzoCarteSettore.getScartiCarteSettore().clone());
		mazzoCarteSettore.getScartiCarteSettore().clear();
		this.mischia();
	}
	
	/**
	 * Rimuove la carta in posizione zero del mazzo di carte settore.
	 */
	public void rimuoviCarta()
	{
		mazzoCarteSettore.getMazzoCarteSettore().remove(0);
	}
	
	/**
	 * Stampa il tipo di settore di tutte le carte settore presenti nel mazzo.
	 */
	public void mostraMazzo()
	{
        for (CartaSettore carta : this.mazzoCarteSettore.getMazzoCarteSettore())
        {
            System.out.println(carta.getTipoCartaSettore());
        }
    }
	
	/**
	 * Pesca una carta, la rimuove dal mazzo e la aggiunge alla lista di carteSettore scartate.
	 * Ritorna la CartaSettore pescata.
	 * 
	 * @return
	 */
	public CartaSettore pescaCartaSettore()
	{
		CartaSettore carta;
		carta=this.pesca();
		this.rimuoviCarta();
		this.aggiungiCartaAScartiSettore(carta);
		return carta;
	}
}
