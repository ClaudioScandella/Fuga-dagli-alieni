package it.polimi.ingsw.cg_26.model.carte;

public class CartaOggetto extends Carta
{
	//tipoOggetto può essere:
	//"attacco", "teletrasporto", "adrenalina"
	//"sedativi", "luci", "difesa"
		
		private String tipoOggetto;
		
		public CartaOggetto(String tipoOggetto){
			super(tipoOggetto);
		}
		
		public String getTipoOggetto(){
			return tipoOggetto;
		}
}
