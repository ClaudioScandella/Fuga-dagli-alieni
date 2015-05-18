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
}