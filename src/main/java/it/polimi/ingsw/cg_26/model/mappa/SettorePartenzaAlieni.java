package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettorePartenzaAlieni, che estende la classe Settore e permette
 * di creare un settore partenza alieni, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettorePartenzaAlieni extends Settore
{
	//COSTRUTTORI
	public SettorePartenzaAlieni(String nome)
	{
		super(nome);
	}
	
	public SettorePartenzaAlieni(String nome, String nomeSupplementare)
	{
		super(nome, nomeSupplementare);
	}
}
