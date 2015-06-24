package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettoreScialuppa1, che estende la classe Settore e permette
 * di creare un settore scialuppa1, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettoreScialuppa1 extends Settore
{
	//COSTRUTTORI
	public SettoreScialuppa1(String nome)
	{
		super(nome);
	}
	
	public SettoreScialuppa1(String nome, String nomeSupplementare)
	{
		super(nome, nomeSupplementare);
	}
}
