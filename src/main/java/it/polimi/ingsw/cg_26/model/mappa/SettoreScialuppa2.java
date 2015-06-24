package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettoreScialuppa2, che estende la classe Settore e permette
 * di creare un settore scialuppa3, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettoreScialuppa2 extends Settore
{
	//COSTRUTTORI
	public SettoreScialuppa2(String nome)
	{
		super(nome);
	}
	
	public SettoreScialuppa2(String nome, String nomeSupplementare)
	{
		super(nome, nomeSupplementare);
	}
}
