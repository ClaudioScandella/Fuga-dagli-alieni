package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettoreScialuppa4, che estende la classe Settore e permette
 * di creare un settore scialuppa4, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettoreScialuppa4 extends Settore
{
	//COSTRUTTORI
	public SettoreScialuppa4(String nome)
	{
		super(nome);
	}
	
	public SettoreScialuppa4(String nome, String nomeSupplementare)
	{
		super(nome, nomeSupplementare);
	}
}
