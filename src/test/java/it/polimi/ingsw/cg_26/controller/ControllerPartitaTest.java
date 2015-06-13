package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;

import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import org.junit.Before;
import org.junit.Test;

public class ControllerPartitaTest {
	
	private ModelPartita partita = new ModelPartita(1234, "galilei");
	private ControllerPartita controller = new ControllerPartita(partita);
	private Giocatore g1 = new Giocatore(1234, "Patrizia");
	private Giocatore g2 = new Giocatore(4321, "Noemi");

	
	@Before
	public void setUp() throws Exception {
		controller.addGiocatore(g1);
		controller.addGiocatore(g2);
		controller.aggiornaTurno();
		partita.setNumeroTurno(40);
		controller.iniziaPartita();
	}

	@Test
	public void testAddGiocatore() {
		assertTrue(partita.getGiocatori().size() == 2);
	}

	@Test
	public void testAggiornaTurno() {
		assertEquals(partita.getNumeroTurno(),40);
	}

//	@Test
//	public void testIniziaPartita() throws IOException {
//		partita.setNumeroTurno(39);
//		controller.iniziaPartita();
//		assertTrue(g1.getPersonaggio() == Personaggio.ALIENO || g1.getPersonaggio() == Personaggio.UMANO);
//		assertTrue(g2.getPersonaggio() == Personaggio.ALIENO || g2.getPersonaggio() == Personaggio.UMANO);
//		assertTrue(partita.getStato() == GameState.RUNNING);
//		partita.setNumeroTurno(40);
//		assertEquals(partita.getStato(), GameState.FINEGIOCO);
//	}

	@Test
	public void testGetPartita() {
		assertEquals(controller.getPartita(), this.partita);
	}

	@Test
	public void testControllaFinePartita() {
		assertFalse(controller.controllaFinePartita());
		
		g1.setInVita(false);
		g2.setInVita(false);
		assertTrue(controller.controllaFinePartita());
		assertTrue(partita.getStato() == GameState.FINEGIOCO);	
	}

	@Test
	public void testTerminaPartita() {
		g1.setVittoria_sconfitta("vittoria");
		g2.setVittoria_sconfitta("vittoria");
		controller.terminaPartita();
		assertEquals(partita.getGiocatoriVincenti().size(), 1);
		assertEquals(partita.getGiocatoriPerdenti().size(), 1);
		
		g1.setVittoria_sconfitta("sconfitta");
		g2.setVittoria_sconfitta("sconfitta");
		assertEquals(partita.getGiocatoriVincenti().size(), 1);
		assertEquals(partita.getGiocatoriPerdenti().size(), 1);
		
		g1.setVittoria_sconfitta("sconfitta");
		g2.setVittoria_sconfitta("vittoria");
		assertEquals(partita.getGiocatoriVincenti().size(), 1);
		assertEquals(partita.getGiocatoriPerdenti().size(), 1);
	
	}

	@Test
	public void testGetGiocatoriInSettore() {
		assertTrue(controller.getGiocatoriInSettore("L06").size() == 1);
		assertTrue(controller.getGiocatoriInSettore("L08").contains(g1) || 
				controller.getGiocatoriInSettore("L08").contains(g2));

	}

	@Test
	public void testGiocatoreCorrente() {
		assertTrue(controller.giocatoreCorrente() == g1 || controller.giocatoreCorrente() == g2);
	}

	@Test
	public void testNumeroUmaniInGioco() {
		g1.setInVita(true);
		g2.setInVita(true);
		assertEquals(controller.numeroUmaniInGioco(), 1);
	}

	@Test
	public void testNumeroAlieniInGioco() {
		g1.setInVita(true);
		g2.setInVita(true);
		assertEquals(controller.numeroAlieniInGioco(), 1);	
		
	}
}
