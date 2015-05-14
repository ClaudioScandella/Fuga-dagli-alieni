package it.polimi.ingsw.cg_26.model.mappe;

public class TipoSettore {
	//può essere:
	//"sicuro", "pericoloso", "umano",
	//"alieno","scialuppa"

	private String tipoSettore;
	
	public TipoSettore(String tipoSettore){
		this.tipoSettore = tipoSettore;
	}
	
	public String getTipoSettore(){
		return tipoSettore;
	}

}
