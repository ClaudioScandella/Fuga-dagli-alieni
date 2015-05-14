package it.polimi.ingsw.cg_26.model.carte;

public class CartaScialuppa
{
	//tipoCartaScialuppa può essere:
	//"rossa", "verde"
			
			private String tipoCartaScialuppa;
			
			public CartaScialuppa(String tipoCartaScialuppa){
				this.tipoCartaScialuppa = tipoCartaScialuppa;
			}
			
			public String getTipoCartaScialuppa(){
				return tipoCartaScialuppa;
			}


}
