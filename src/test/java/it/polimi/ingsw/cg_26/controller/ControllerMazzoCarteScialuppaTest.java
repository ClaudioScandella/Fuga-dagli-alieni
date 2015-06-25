package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteScialuppaTest {
	
	private MazzoCarteScialuppa mazzo;
	private ControllerMazzoCarteScialuppa mazzoScialuppa;

	@Before
	public void setUp() throws Exception {
		mazzo = new MazzoCarteScialuppa();
		mazzoScialuppa = new ControllerMazzoCarteScialuppa(mazzo);
	}

	@Test
	public void testControllerMazzoCarteScialuppa() {
		assertTrue(mazzoScialuppa instanceof ControllerMazzoCarteScialuppa);
	}

	@Test
	public void testPesca() {
		assertTrue(mazzoScialuppa.pesca() instanceof CartaScialuppa);
	}

	@Test
	public void testRimuoviPrimaCarta() {
		mazzoScialuppa.rimuoviPrimaCarta();
		assertTrue(mazzo.getMazzoCarteScialuppa().size() == 5);
	}

}
