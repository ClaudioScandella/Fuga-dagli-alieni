package it.polimi.ingsw.cg_26.model.mappa;

/**
 * Contiene la classe astratta Settore, che definisce metodi e attributi
 * per le classi che la estendono: 
 * - SettorePartenzaAlieni
 * - SettorePartenzaUmani
 * - SettorePericoloso
 * - SettoreScialuppa1
 * - SettoreScialuppa2
 * - SettoreScialuppa3
 * - SettoreScialuppa4
 * - SettoreSicuro
 * - SettoreVuoto
 * 
 * @author Claudio e Patrizia
 *
 */
public abstract class Settore
{
	private String nome; //coordinata
	private String nomeSupplementare; //tipo di settore
	private boolean bloccata=false;
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------
	
	public Settore(String nome)
	{
		this.nome=nome;
	}
	
	public Settore(String nome, String nomeSupplementare)
	{
		this.nome=nome;
		this.nomeSupplementare=nomeSupplementare;
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------

	public String getNome() 
	{
		return nome;
	}
	
	public String getNomeSupplementare()
	{
		return nomeSupplementare;
	}	
	
	public boolean getBloccata()
	{
		return bloccata;
	}

//	---SETTER-----------------------------------------------------------------------------------------------

	public void setBloccata(boolean bloccata) {
		this.bloccata = bloccata;
	}

//	--------------------------------------------------------------------------------------------------

}
