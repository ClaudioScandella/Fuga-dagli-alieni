package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettorePericoloso, che estende la classe Settore e permette
 * di creare un settore pericoloso, identificato da un nome (coordinata) e
 * da un nome supplementare (tipo di settore)
 *  
 * @author Claudio e Patrizia
 *
 */
public class SettorePericoloso extends Settore
{
	//COSTRUTTORE
	public SettorePericoloso(String nome, String nomeSupplementare)
	{
		super(nome,nomeSupplementare);
	}
}
