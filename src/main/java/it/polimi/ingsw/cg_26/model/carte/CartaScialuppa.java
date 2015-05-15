package it.polimi.ingsw.cg_26.model.carte;

public class CartaScialuppa extends Carta
{
	//tipoCartaScialuppa può essere:
	//"rossa", "verde"
			
			private String tipoCartaScialuppa;
			
			public CartaScialuppa(String tipoCartaScialuppa){
				super(tipoCartaScialuppa);
			}
			
			public String getTipoCartaScialuppa(){
				return tipoCartaScialuppa;
			}


}
