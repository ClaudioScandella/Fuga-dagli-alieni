package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;
import java.util.List;

public class MazzoOggetti
{

	List<CartaOggetto> mazzoCarteOggetto = new ArrayList<>();
	private CartaOggetto cartaOggetto;
	

	public MazzoOggetti(){
		
		cartaOggetto=new CartaOggetto(TipoOggetto.ADRENALINA);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		cartaOggetto=new CartaOggetto(TipoOggetto.ATTACCO);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		cartaOggetto=new CartaOggetto(TipoOggetto.LUCI);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		cartaOggetto=new CartaOggetto(TipoOggetto.TELETRASPORTO);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		cartaOggetto=new CartaOggetto(TipoOggetto.SEDATIVI);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		mazzoCarteOggetto.add(cartaOggetto);
		cartaOggetto=new CartaOggetto(TipoOggetto.DIFESA);
		mazzoCarteOggetto.add(cartaOggetto);

	}
	
	public List<CartaOggetto> getMazzo(){
		return mazzoCarteOggetto;
	}
}
