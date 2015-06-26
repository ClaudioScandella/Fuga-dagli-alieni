package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteOggetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Contiene la classe ControllerMazzoCarteOggetto, la cui istanza permette di
 * gestire il mazzo di carte oggetto indicato dalla variabile mazzoCarteOggetto.
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerMazzoCarteOggetto
{
	private MazzoCarteOggetto mazzoCarteOggetto;
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	/**
	 * Costruisce il ControllerMazzoCarteOggetto assegnando alla variabile
	 * mazzoCarteOggetto il riferimento all'oggetto MazzoCarteOggetto passatogli 
	 * in ingresso.
	 * Successivamente mischia il mazzo.
	 * 
	 * @param mazzoCarteOggetto
	 */
	public ControllerMazzoCarteOggetto(MazzoCarteOggetto mazzoCarteOggetto)
	{
		this.mazzoCarteOggetto=mazzoCarteOggetto;
		this.mischia();
	}
	
//	--------------------------------------------------------------------------------------------------
	/**
	 * Permette di pescare la carta in posizione zero dell'arraylist contenente il mazzo
	 * di carte oggetto.
	 * Ritorna la CartaOggetto pescata.
	 * 
	 * @return 
	 */
	public CartaOggetto pesca(){
		return mazzoCarteOggetto.getMazzoCarteOggetto().get(0);
	}
	
	/**
	 * Aggiunge la carta passata in ingresso alla lista degli scarti.
	 * 
	 * @param carta CartaOggetto che voglio scartare
	 */
	public void aggiungiCartaAScartiOggetto(CartaOggetto carta){
		mazzoCarteOggetto.getScartiCarteOggetto().add(carta);
	}
	
	/**
	 * Ritorna il numero di carte oggetto ancora presenti nel mazzo.
	 * 
	 * @return
	 */
	public int numeroCarteOggettoNelMazzo(){
		return mazzoCarteOggetto.getMazzoCarteOggetto().size();
	}
	
	/**
	 * Mischia la lista di carte settore.
	 */
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(mazzoCarteOggetto.getMazzoCarteOggetto(), new Random(seed));
    }
	
	/**
	 * Questo metodo fa una copia della lista degli scarti e li copia nella lista del mazzo
	 * di carte oggetto.
	 * Successivamente svuota il mazzo degli scarti e mischia il mazzo rigenerato.
	 */
	public void rigeneraMazzo()
	{
		mazzoCarteOggetto.setMazzoCarteOggetto((ArrayList<CartaOggetto>) mazzoCarteOggetto.getScartiCarteOggetto().clone());
		mazzoCarteOggetto.getScartiCarteOggetto().clear();
		this.mischia();
	}
	
	/**
	 * Rimuove la carta in posizione zero del mazzo di carte oggetto.
	 */
	public void rimuoviCarta()
	{
		mazzoCarteOggetto.getMazzoCarteOggetto().remove(0);
	}
	
	/**
	 * Stampa il tipo di oggetto di tutte le carte oggetto presenti nel mazzo.
	 */
	public void mostraMazzo()
	{
        for (CartaOggetto carta : this.mazzoCarteOggetto.getMazzoCarteOggetto())
        {
            System.out.println(carta.getTipoOggetto());
        }
    }
	
	/**
	 * Pesca una carta e la rimuove dal mazzo.
	 * Ritorna la CartaSettore pescata.
	 * 
	 * @return
	 */
	public CartaOggetto pescaCartaOggetto()
	{
		CartaOggetto carta;
		carta=this.pesca();
		this.rimuoviCarta();
		return carta;
	}
}
