package model.carte;

public class CartaSettore
{
	public enum TipoSettore{SILENZIO, RUMOREaSCELTA, RUMOREproprioSETTORE;}
	private TipoSettore tipo;
	private boolean conOggetto;
	

	public CartaSettore(TipoSettore tipo, boolean conOggetto)
	{
		this.tipo=tipo;
		this.conOggetto=conOggetto;
	}
		
	public TipoSettore getTipoCartaSettore()
	{
		return tipo;
	}
	
	public boolean getConOggetto()
	{
		return conOggetto;
	}
}
