package it.polimi.ingsw.cg_26.model.carte;

public class CartaOggetto
{
	//tipoOggetto può essere:
	//"attacco", "teletrasporto", "adrenalina"
	//"sedativi", "luci", "difesa"
		
		private String tipoOggetto;
		
		public CartaOggetto(String tipoOggetto){
			this.tipoOggetto = tipoOggetto;
		}
		
		public String getTipoOggetto(){
			return tipoOggetto;
		}
}
