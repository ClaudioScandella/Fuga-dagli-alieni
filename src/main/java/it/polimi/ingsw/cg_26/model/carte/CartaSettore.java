package it.polimi.ingsw.cg_26.model.carte;

/**
 * Contiene la classe CartaSettore, in cui è possibile creare le carte settore, caratterizzate
 * da un tipo (silenzio, rumore a scelta o rumore nel proprio settore) e dalla
 * presenza o meno di un oggetto.
 * 
 * @author Claudio e Patrizia
 *
 */
public class CartaSettore
{
	/**
	 * 
	 * Una carta settore può essere di tipo: SILENZIO, RUMOREaSCELTA, RUMOREproprioSETTORE.
	 *
	 */
	public enum TipoSettore{SILENZIO, RUMOREaSCELTA, RUMOREproprioSETTORE;}
	private TipoSettore tipo;
	private boolean conOggetto;
	
	//COSTRUTTORE
	public CartaSettore(TipoSettore tipo, boolean conOggetto)
	{
		this.tipo=tipo;
		this.conOggetto=conOggetto;
	}
		
	//GETTER
	public TipoSettore getTipoCartaSettore()
	{
		return tipo;
	}
	
	public boolean getConOggetto()
	{
		return conOggetto;
	}
}
