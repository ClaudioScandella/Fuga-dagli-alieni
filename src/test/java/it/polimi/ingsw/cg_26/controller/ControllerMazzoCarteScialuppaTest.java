package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteScialuppaTest {
	
	private MazzoCarteScialuppa mazzo;
	private ControllerMazzoCarteScialuppa controller;

	@Before
	public void setUp() throws Exception {
		mazzo = new MazzoCarteScialuppa();
		controller = new ControllerMazzoCarteScialuppa(mazzo);
	}

	@Test
	public void testMischia() {
		controller.mischia();
		assertTrue(mazzo.getMazzoCarteScialuppa().size() == 8);	
	}

	@Test
	public void testPesca(){
		assertTrue(controller.pesca() instanceof CartaScialuppa);
	}

	@Test
	public void testRimuoviPrimaCarta() {
		controller.rimuoviPrimaCarta();
		assertEquals(mazzo.getMazzoCarteScialuppa().size(), 7);
	}

}
