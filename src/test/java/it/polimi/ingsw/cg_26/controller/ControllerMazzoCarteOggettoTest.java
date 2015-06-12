package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteOggetto;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteOggettoTest {
	
	private MazzoCarteOggetto mazzo;
	private ControllerMazzoCarteOggetto controller;
	private CartaOggetto carta;


	@Before
	public void setUp() throws Exception {
		mazzo = new MazzoCarteOggetto();
		controller = new ControllerMazzoCarteOggetto(mazzo);
	}

	@Test
	public void testPesca() {
		assertTrue(controller.pesca() instanceof CartaOggetto);
	}

	@Test
	public void testMischia() {
		controller.mischia();
		assertTrue(controller.numeroCarteOggettoNelMazzo() == 12);
	}

	@Test
	public void testNumeroCarteOggettoNelMazzo() {
		assertEquals(controller.numeroCarteOggettoNelMazzo(), 12);
		controller.pescaCartaOggetto();
		assertEquals(controller.numeroCarteOggettoNelMazzo(), 11);
	}

	@Test
	public void testRigeneraMazzo() {
		for( ; controller.numeroCarteOggettoNelMazzo() != 0; ){
			carta = controller.pescaCartaOggetto();
			controller.aggiungiCartaAScartiOggetto(carta);
		}
		controller.rigeneraMazzo();
		assertEquals(controller.numeroCarteOggettoNelMazzo(), 12);
		}

	@Test
	public void testPescaCartaOggetto() {
		assertTrue(controller.pescaCartaOggetto() instanceof CartaOggetto);
	}

}
