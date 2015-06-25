package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ControllerEffettoCarteOggettoTest {
	
	private ModelPartita partita;
	private ControllerPartita controllerPartita;
	private ControllerEffettoCarteOggetto controller1, controller2, controller3, controller4, controller5;
	private CartaOggetto c1, c2, c3, c4, c5;
	private Giocatore g1;

	@Before
	public void setUp() throws Exception {
		partita = new ModelPartita(1234, "galilei");
		controllerPartita = new ControllerPartita(partita);
		controller1 = new ControllerEffettoCarteOggetto("ADRENALINA", controllerPartita);
		controller2 = new ControllerEffettoCarteOggetto("ATTACCO", controllerPartita);
		controller3 = new ControllerEffettoCarteOggetto("LUCI", controllerPartita);
		controller4 = new ControllerEffettoCarteOggetto("SEDATIVI", controllerPartita);
		controller5 = new ControllerEffettoCarteOggetto("TELETRASPORTO", controllerPartita);
		c1 = new CartaOggetto(TipoOggetto.ADRENALINA);
		c2 = new CartaOggetto(TipoOggetto.ATTACCO);
		c3 = new CartaOggetto(TipoOggetto.LUCI);
		c4 = new CartaOggetto(TipoOggetto.SEDATIVI);
		c5 = new CartaOggetto(TipoOggetto.TELETRASPORTO);
		g1 = new Giocatore(1111,"Patrizia");
		g1.setPersonaggio(Personaggio.UMANO, 1, "L08");
		controllerPartita.addGiocatore(g1);

	}

	@Test
	public void testControllerEffettoCarteOggetto() {
		assertTrue(controller1 instanceof ControllerEffettoCarteOggetto);
	}

	@Test
	public void testEseguiEffettoCarta() throws IOException {
		//ADRENALINA
			//haMosso==true
		controllerPartita.giocatoreCorrente().setHaMosso(true);
		controller1.eseguiEffettoCarta(c1);
		assertTrue(controllerPartita.giocatoreCorrente().getPortata() == 1);
			//haMosso==false
		controllerPartita.giocatoreCorrente().setHaMosso(false);
		controller1.eseguiEffettoCarta(c1);
		assertTrue(controllerPartita.giocatoreCorrente().getPortata() == 2);
		
		//ATTACCO
			//haMosso==true 
		controllerPartita.giocatoreCorrente().setHaMosso(true);
		controllerPartita.giocatoreCorrente().setHaAttaccato(false);
		controllerPartita.giocatoreCorrente().setPuoAttaccare(false);
		controller2.eseguiEffettoCarta(c2);
		assertTrue(controllerPartita.giocatoreCorrente().getPuoAttaccare() == true);
		assertTrue(controllerPartita.giocatoreCorrente().getHaAttaccato() == true);

			//haMosso==false
		controllerPartita.giocatoreCorrente().setHaMosso(false);
		controllerPartita.giocatoreCorrente().setHaAttaccato(false);
		controllerPartita.giocatoreCorrente().setPuoAttaccare(false);
		controller2.eseguiEffettoCarta(c2);
		assertTrue(controllerPartita.giocatoreCorrente().getPuoAttaccare() == false);
		
//		//LUCi
//		controller3.eseguiEffettoCarta(c3);
		
		
		//SEDATIVI
			//haMosso==true
		g1.setHaMosso(true);
		controller4.eseguiEffettoCarta(c4);
		assertFalse(g1.getSedativi());
			//haMosso==false - sedativi true
		g1.setHaMosso(false);
		controller4.eseguiEffettoCarta(c4);
		assertTrue(g1.getSedativi());
		
		
		//TELETRASPORTO
		controller5.eseguiEffettoCarta(c5);
		assertEquals(g1.getPosizione(), partita.getControllerMappa().getPartenzaUmani());
			
	}
	
	@Test
	public void testInserisciSettoreLuci(){
			//il settore non esiste
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		controller3.inserisciSettoreLuci("D15");
		assertEquals(partita.getStatoAvanzamentoTurno(), StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);					
			//il settore esiste
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		controller3.inserisciSettoreLuci("P08");
		assertEquals(partita.getStatoAvanzamentoTurno(), StatoAvanzamentoTurno.ATTESA_COMANDO);	
	}

}
