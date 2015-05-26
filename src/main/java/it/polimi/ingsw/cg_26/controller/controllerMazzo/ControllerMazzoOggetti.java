package it.polimi.ingsw.cg_26.controller.controllerMazzo;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Partita;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoOggetti;
import it.polimi.ingsw.cg_26.model.Giocatore.*;

public class ControllerMazzoOggetti {
	
	private Partita partita;
	private MazzoOggetti mazzoOggetti;
	
	public ControllerMazzoOggetti(Partita partita){
		this.partita = partita;
		mazzoOggetti=new MazzoOggetti();
		mazzoOggetti.mischia();
		
	}
	
	public void eseguiFunzioneCarta(CartaOggetto cartaOggetto, Giocatore giocatore, int turno){
		
		switch (cartaOggetto.getTipoOggetto()){
		case ATTACCO: 
			attacca(); 
			break;
			
		case TELETRASPORTO:
			if(giocatore.getPersonaggio() == Personaggio.ALIENO){
				giocatore.setPosizione(SettoreAlieno);
				partita.setLog(turno, giocatore, settoreAlieno);
			}
			else if(giocatore.getPersonaggio() == Personaggio.UMANO){
				giocatore.setPosizione(SettoreUmano);
				partita.setLog(turno, giocatore, settoreAlieno);
			}
			break;
		case  ADRENALINA:
			giocatore.setAdrenalina(true);
			break;
			
		case SEDATIVI:
			giocatore.setSedativi(true);
			break;
		
		case LUCI: 
			String settore = scegliSettore();
			mostraGiocatori(settore);
			break;
			
		}
	}
	
	public CartaOggetto pescaCartaOggetto(Giocatore giocatore){
		if(mazzoOggetti.lunghezzaListaOggetti() < 1) 
			mazzoOggetti.rigeneraMazzo();
		CartaOggetto carta = new CartaOggetto(mazzoOggetti.pesca().getTipoOggetto());
		mazzoOggetti.rimuoviCarta();;
		return carta;
		}
	
	public void scarta(){
		
	}

}
