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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settore other = (Settore) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
