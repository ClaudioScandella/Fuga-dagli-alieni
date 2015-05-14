package it.polimi.ingsw.cg_26.model.mazzi;

import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;

import java.util.ArrayList;

public class MazzoOggetti
{

	ArrayList<CartaOggetto> mazzoCarteOggetto = new ArrayList<CartaOggetto>();

	public void setMazzo(){
		mazzoCarteOggetto.add(new CartaOggetto("attacco"));
		mazzoCarteOggetto.add(new CartaOggetto("attacco"));
		mazzoCarteOggetto.add(new CartaOggetto("teletrasporto"));
		mazzoCarteOggetto.add(new CartaOggetto("teletrasporto"));
		mazzoCarteOggetto.add(new CartaOggetto("adrenalina"));
		mazzoCarteOggetto.add(new CartaOggetto("adrenalina"));
		mazzoCarteOggetto.add(new CartaOggetto("sedativi"));
		mazzoCarteOggetto.add(new CartaOggetto("sedativi"));
		mazzoCarteOggetto.add(new CartaOggetto("sedativi"));
		mazzoCarteOggetto.add(new CartaOggetto("luci"));
		mazzoCarteOggetto.add(new CartaOggetto("luci"));
		mazzoCarteOggetto.add(new CartaOggetto("difesa"));
	}
}
//togliere il creaMazzo dal controller (perchè formiamo qui 
//il mazzo e peschiamo a caso)
