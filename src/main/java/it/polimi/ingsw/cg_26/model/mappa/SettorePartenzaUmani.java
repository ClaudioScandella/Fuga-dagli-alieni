package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettorePartenzaUmani, che estende la classe Settore e permette
 * di creare un settore partenza umani, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettorePartenzaUmani extends Settore
{
	//COSTRUTTORI
	public SettorePartenzaUmani(String nome)
	{
		super(nome);
	}
	
	public SettorePartenzaUmani(String nome, String nomeSupplementare)
	{
		super(nome, nomeSupplementare);
	}
}
