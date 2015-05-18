package it.polimi.ingsw.cg_26;

public class CartaScialuppa
{
	public enum Colore{VERDE, ROSSA;}
	private Colore colore;
	
	public CartaScialuppa(Colore colore)
	{
		this.colore=colore;
	}
	
	public Colore getColore()
	{
		return this.colore;
	}
}
