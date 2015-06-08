package it.polimi.ingsw.cg_26.model.carte;

public class CartaOggetto
{
	public enum TipoOggetto{ADRENALINA, ATTACCO, DIFESA, LUCI, SEDATIVI, TELETRASPORTO;}
	private TipoOggetto tipo;
		
	public CartaOggetto(TipoOggetto tipo)
	{
		this.tipo=tipo;
	}
	
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