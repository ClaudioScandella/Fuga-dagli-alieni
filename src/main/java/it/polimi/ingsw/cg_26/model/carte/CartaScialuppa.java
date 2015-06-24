package it.polimi.ingsw.cg_26.model.carte;

/**
 * 
 * Contiene la classe  CartaScialuppa, in cui è possibile creare delle carte scialuppa
 * di colore verde o rosso.
 * 
 * @author Claudio e Patrizia
 *
 */
public class CartaScialuppa
{
	/**
	 * 
	 * Una carta scialuppa può essere esclusivamente verde o rossa.
	 *
	 */
	public enum Colore{VERDE, ROSSA;}
	private Colore colore;
	
	//COSTRUTTORE
	public CartaScialuppa(Colore colore)
	{
		this.colore=colore;
	}
	
	//GETTER
	public Colore getColore()
	{
		return this.colore;
	}
}
