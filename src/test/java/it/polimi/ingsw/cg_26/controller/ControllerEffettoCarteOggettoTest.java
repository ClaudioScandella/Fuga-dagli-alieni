package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.mappa.Mappa;
import it.polimi.ingsw.cg_26.model.mappa.Settore;

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
		//adrenalina
		controller1.eseguiEffettoCarta(c1);
		assertTrue(g1.getPortata() == 2);
		
//		//attacco
		controller2.eseguiEffettoCarta(c2);
		g1.setHaMosso(true);
		g1.setPuoAttaccare(true);
		controller2.eseguiEffettoCarta(c2);
		assertTrue(g1.getHaAttaccato() == true);

		
//		//luci
//		controller3.eseguiEffettoCarta(c3);
		
		
		//sedativi
		g1.setHaMosso(true);
		controller4.eseguiEffettoCarta(c4);
		assertFalse(g1.getSedativi());
		g1.setHaMosso(false);
		controller4.eseguiEffettoCarta(c4);
		assertTrue(g1.getSedativi());
		
		
		//teletrasporto
		controller5.eseguiEffettoCarta(c5);
		assertEquals(g1.getPosizione(), partita.getControllerMappa().getPartenzaUmani());
		
	
	}

}
