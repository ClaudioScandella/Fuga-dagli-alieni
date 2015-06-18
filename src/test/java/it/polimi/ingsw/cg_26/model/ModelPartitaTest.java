package it.polimi.ingsw.cg_26.model;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.controller.ControllerMappa;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteOggetto;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteScialuppa;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteSettore;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ModelPartitaTest {
	
	private ModelPartita model = new ModelPartita(1234, "galilei");	
	private Giocatore g1 = new Giocatore(1111,"Patrizia");
	private Giocatore g2 = new Giocatore(2222, "Michela");
	private Giocatore g3 = new Giocatore(3333, "Matilde");
	private ArrayList<Giocatore> lista = new ArrayList<Giocatore>();
	private ArrayList<Giocatore> vincenti = new ArrayList<Giocatore>();
	private ArrayList<Giocatore> perdenti = new ArrayList<Giocatore>();

	
	@Before
	public void setUp() throws Exception {
		model.setStato(GameState.RUNNING);
		model.setGiocatore(g1);
		model.setGiocatore(g2);
		lista.add(g1);
		lista.add(g2);
		model.addGiocatoreVincente(g1);
		vincenti.add(g1);
		model.addGiocatorePerdente(g2);
		perdenti.add(g2);
		model.setNumeroTurno(10);
		model.setNumeroGiocatoreCorrente(8);
		
	}

	@Test
	public void testModelPartita() {
		assertTrue(model instanceof ModelPartita);
	}
	
	@Test
	public void testGetStato() {
		model.setStato(GameState.RUNNING);
		assertTrue(model.getStato() == GameState.RUNNING);
	}

	@Test
	public void testGetIdPartita() {
		assertTrue(model.getIdPartita() == 1234);
	}

	@Test
	public void testGetNomeMappa() {
		assertTrue(model.getNomeMappa() == "galilei");
	}

	@Test
	public void testGetGiocatori() {
		assertEquals(model.getGiocatori(), this.lista);
	}

//	@Test
//	public void testGetGiocatoriFuoriGioco() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetGiocatoriPerdenti() {
		assertEquals(model.getGiocatoriPerdenti(), this.perdenti);	}

	@Test
	public void testGetGiocatoriVincenti() {
		assertEquals(model.getGiocatoriVincenti(), this.vincenti);
	}

	@Test
	public void testGetLog() {
		assertTrue(model.getLog() instanceof LOG);
		}

	@Test
	public void testGetControllerMappa() {
		assertTrue(model.getControllerMappa() instanceof ControllerMappa);	}

	@Test
	public void testGetControllerMazzoCarteScialuppa() {
		assertTrue(model.getControllerMazzoCarteScialuppa() instanceof ControllerMazzoCarteScialuppa);
	}

	@Test
	public void testGetControllerMazzoCarteSettore() {
		assertTrue(model.getControllerMazzoCarteSettore() instanceof ControllerMazzoCarteSettore);
	}

	@Test
	public void testGetControllerMazzoCarteOggetto() {
		assertTrue(model.getControllerMazzoCarteOggetto() instanceof ControllerMazzoCarteOggetto);
	}

	@Test
	public void testGetNumeroTurno() {
		assertTrue(model.getNumeroTurno() == 10);
	}

	@Test
	public void testGetNumeroGiocatoreCorrente() {
		assertTrue(model.getNumeroGiocatoreCorrente() == 8);
	}
	
	@Test
	public void testSetGiocatore() {
		int num = model.getGiocatori().size();
		model.setGiocatore(g3);
		assertTrue(model.getGiocatori().size() == num+1);
	}
	
	@Test
	public void testSetStato() {
		model.setStato(GameState.INIZIALIZZAZIONE);
		assertTrue(model.getStato() == GameState.INIZIALIZZAZIONE);
	}
	
	@Test
	public void testSetNumeroTurno() {
		model.setNumeroTurno(8);
		assertTrue(model.getNumeroTurno() == 8);
	}
	
	@Test
	public void testSetNumeroGiocatoreCorrente() {
		model.setNumeroGiocatoreCorrente(7);
		assertTrue(model.getNumeroGiocatoreCorrente() == 7);
	}
	
	@Test
	public void testAddGiocatorePerdente() {
		int num = model.getGiocatoriPerdenti().size();
		model.addGiocatorePerdente(g3);
		assertTrue(model.getGiocatoriPerdenti().size() == num+1);
	}

	@Test
	public void testAddGiocatoreVincente() {
		int num = model.getGiocatoriVincenti().size();
		model.addGiocatoreVincente(g3);
		assertTrue(model.getGiocatoriVincenti().size() == num+1);
	}
}

