package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettoreVuoto, che estende la classe Settore e permette
 * di creare un settore vuoto, identificato da un nome (coordinata) ed eventualmente
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettoreVuoto extends Settore
{
	//COSTRUTTORI
	public SettoreVuoto(String nome)
	{
		super(nome);
	}
	
	public SettoreVuoto(String nome, String nomeSupplementare)
	{ 
		super(nome, nomeSupplementare);
	}
}
