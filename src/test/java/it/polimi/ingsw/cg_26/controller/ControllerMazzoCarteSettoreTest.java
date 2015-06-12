package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteSettore;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteSettoreTest {
	
	private MazzoCarteSettore mazzo;
	private ControllerMazzoCarteSettore controller;

	@Before
	public void setUp() throws Exception {
		mazzo = new MazzoCarteSettore();
		controller = new ControllerMazzoCarteSettore(mazzo);
	}

	@Test
	public void testPesca() {
		assertTrue(controller.pesca() instanceof CartaSettore);
	}

	@Test
	public void testNumeroCarteSettoreNelMazzo() {
		assertEquals(controller.numeroCarteSettoreNelMazzo(), 25);
		controller.pescaCartaSettore();
		assertEquals(controller.numeroCarteSettoreNelMazzo(), 24);	
	}

	@Test
	public void testMischia() {
		controller.mischia();
		assertTrue(controller.numeroCarteSettoreNelMazzo() == 25);	
	}

	@Test
	public void testRigeneraMazzo() {
		for(; controller.numeroCarteSettoreNelMazzo() != 0;){
			controller.pescaCartaSettore();
		}
		controller.rigeneraMazzo();
		assertEquals(controller.numeroCarteSettoreNelMazzo(), 25);	
	}

	@Test
	public void testPescaCartaSettore() {
		assertTrue(controller.pescaCartaSettore() instanceof CartaSettore);
	}

}
