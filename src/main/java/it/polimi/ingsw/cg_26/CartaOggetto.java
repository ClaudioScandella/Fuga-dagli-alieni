package it.polimi.ingsw.cg_26;
 
public class CartaOggetto extends Carta
{
	public enum TipoOggetto{ADRENALINA, ATTACCO, DIFESA, LUCI, SEDATIVI, TELETRASPORTO;}
	private TipoOggetto tipo;
	
	
	//tipoOggetto può essere:
	//"attacco", "teletrasporto", "adrenalina"
	//"sedativi", "luci", "difesa"
		
	public CartaOggetto(TipoOggetto tipo)
	{
		this.tipo=tipo;
	}
	
	public TipoOggetto getTipoOggetto()
	{
		return tipo;
	}
}
