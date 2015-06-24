package it.polimi.ingsw.cg_26.model.mappa;

/**
 * 
 * Contiene la classe SettoreSicuro, che estende la classe Settore e permette
 * di creare un settore sicuro, identificato da un nome (coordinata) e
 * da un nome supplementare (tipo di settore).
 * 
 * @author Claudio e Patrizia
 *
 */
public class SettoreSicuro extends Settore
{
	//COSTRUTTORE
	public SettoreSicuro(String nome, String nomeSupplementare)
	{
		super(nome,nomeSupplementare);
	}
}
