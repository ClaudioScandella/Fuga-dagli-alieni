package it.polimi.ingsw.cg_26.model.carte;

/**
 * 
 * Contiene la classe CartaOggetto, in cui è possibile creare carte oggetto di tipo:
 * adrenalina, attacco, difesa, luci, sedativi o teletrasporto.
 * 
 * @author Claudio e Patrizia
 *
 */
public class CartaOggetto
{
	/**
	 * 
	 * Le carte oggetto possono essere esclusivamente di tipo:
	 * adrenalina, attacco, difesa, luci, sedativi, teletrasporto
	 *
	 */
	public enum TipoOggetto{ADRENALINA, ATTACCO, DIFESA, LUCI, SEDATIVI, TELETRASPORTO;}
	private TipoOggetto tipo;
		
	// COSTRUTTORE
	public CartaOggetto(TipoOggetto tipo)
	{
		this.tipo=tipo;
	}
	
	//GETTER
	public TipoOggetto getTipoOggetto()
	{
		return tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		CartaOggetto other = (CartaOggetto) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}