package it.polimi.ingsw.cg_26.model.carte;

public class CartaSettore extends Carta
{
	private boolean conOggetto;
	
	//tipoCartaSettore può essere:
		//"silenzio", "rumore",
		//"rumoreInSettore"
		
	private String tipoCartaSettore;
		
	public CartaSettore(String tipoCartaSettore, boolean conOggetto){
			super(tipoCartaSettore);
			this.conOggetto = conOggetto;
		}
		
	public String getTipoCartaSettore(){
			return tipoCartaSettore;
		}
	
	public boolean getConOggetto(){
		return conOggetto;
	}
}
