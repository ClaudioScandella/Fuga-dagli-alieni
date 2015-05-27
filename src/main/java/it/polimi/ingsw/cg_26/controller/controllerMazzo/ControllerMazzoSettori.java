package it.polimi.ingsw.cg_26.controller.controllerMazzo;

import it.polimi.ingsw.cg_26.model.Partita;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteSettore;

public class ControllerMazzoSettori {

	private Partita partita;
	private MazzoCarteSettore mazzo;
	
	
	
	
	public ControllerMazzoSettori(Partita partita)
	{
		this.partita=partita;
		mazzo=new MazzoCarteSettore();
		mazzo.mischia();
	}
	
	public TipoSettore pesca()
	{
		if(mazzo.lunghezzaListaSettori() < 1)
			mazzo.rigeneraMazzo();
		CartaSettore carta=new CartaSettore(mazzo.pesca().getTipoCartaSettore(),mazzo.pesca().getConOggetto());
		mazzo.rimuoviCarta();
		return carta.getTipoCartaSettore();
		
	}
	
	public void eseguiFunzioneCartaSettore(CartaSettore carta)
	{
		switch(carta.getTipoCartaSettore())
		{
		case RUMOREproprioSETTORE:
			partita.setLogPosizioneDichiarata(turno, idGiocatore, posizioneDichiarata);
			break;
		case RUMOREaSCELTA:
			break;
		case SILENZIO:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}








































