package it.polimi.ingsw.cg_26.controller.controllerMazzo;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Partita;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.mappe.Mappa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoOggetti;
import it.polimi.ingsw.cg_26.model.Giocatore.*;

public class ControllerMazzoOggetti {
	
	private Partita partita;
	private MazzoOggetti mazzoOggetti;
	private Mappa mappa;
	
	public ControllerMazzoOggetti(Partita partita, Mappa mappa){
		this.partita = partita;
		mazzoOggetti=new MazzoOggetti();
		mazzoOggetti.mischia();
		this.mappa=mappa;
	}
	
	public void eseguiFunzioneCarta(CartaOggetto cartaOggetto, Giocatore giocatore, int turno){
		
		switch (cartaOggetto.getTipoOggetto()){
		case ATTACCO: 
			attacca(); 
			break;
			
		case TELETRASPORTO:
			if(giocatore.getPersonaggio() == Personaggio.ALIENO){
				giocatore.setPosizione(mappa.getPartenzaAlieni());
				partita.setLog(turno, giocatore, settoreAlieno);
			}
			else if(giocatore.getPersonaggio() == Personaggio.UMANO){
				giocatore.setPosizione(mappa.getPartenzaUmani());
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
	/*metodi:
	 - mischia mazzo
	 - pesca carta (toglie una carta a caso dal mazzo)
	 		- se il numero di carte oggetto è già tre, la quarta deve scegliere se 
	 		 usarla oppure scartarla
	 - scarta (rimette la carta nel mazzo)
	 - controlla il tipo di carta e chiama il metodo che esegue la funzione
	 */
}
