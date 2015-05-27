package it.polimi.ingsw.cg_26.controller.controllerMazzo;

import it.polimi.ingsw.cg_26.controller.ControllerGiocatore;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Partita;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.mappe.Settore;
import it.polimi.ingsw.cg_26.model.mazzi.*;

public class ControllerMazzoScialuppa {
	
	private Partita partita;
	private MazzoCarteScialuppa mazzoScialuppa;

	public ControllerMazzoScialuppa(Partita partita)
	{
		this.partita=partita;
		mazzoScialuppa=new MazzoCarteScialuppa();
		mazzoScialuppa.mischia();
	}
	
	public Colore pesca()
	{
		CartaScialuppa carta=new CartaScialuppa(mazzoScialuppa.pesca().getColore());
		mazzoScialuppa.rimuoviPrimaCarta();
		return carta.getColore();
	}
	
	public void eseguiFunzioneCarta(CartaScialuppa cartaScialuppa, Settore settore, ControllerGiocatore controllerGiocatore, Giocatore giocatore)
	{
		if(cartaScialuppa.getColore()==Colore.ROSSA)
		{
			settore.setBloccata(true);
			if(( partita.getMappa()).numeroScialuppeBloccate()==4)
			{
				partita.finePartita();
			}
			return;
		}
		else
		{
			controllerGiocatore.salvati(giocatore);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

































