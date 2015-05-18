package it.polimi.ingsw.cg_26;

public class CartaSettore extends Carta
{
	public enum TipoSettore{SILENZIO, RUMORE, RUMOREinSETTORE;}
	private TipoSettore tipo;
	private boolean conOggetto;
	
	//tipoCartaSettore può essere:
		//"silenzio", "rumore",
		//"rumoreInSettore"
		
	
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
