package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteOggetto;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteOggettoTest {
	
	private CartaOggetto carta;
	private MazzoCarteOggetto mazzo = new MazzoCarteOggetto();
	private MazzoCarteOggetto mazzo1 = new MazzoCarteOggetto();
	private ControllerMazzoCarteOggetto controllerMazzo = new ControllerMazzoCarteOggetto(mazzo);
	private ControllerMazzoCarteOggetto controllerMazzo1 = new ControllerMazzoCarteOggetto(mazzo1);

	@Before
	public void setUp() throws Exception {
		carta = new CartaOggetto(TipoOggetto.ADRENALINA);
		controllerMazzo1.aggiungiCartaAScartiOggetto(carta);
		controllerMazzo1.aggiungiCartaAScartiOggetto(carta);
		controllerMazzo1.aggiungiCartaAScartiOggetto(carta);

	}

	@Test
	public void testControllerMazzoCarteOggetto() {
		assertTrue(controllerMazzo instanceof ControllerMazzoCarteOggetto);
	}

	@Test
	public void testPesca() {
		assertTrue(controllerMazzo.pesca() instanceof CartaOggetto);
	}

	@Test
	public void testAggiungiCartaAScartiOggetto() {
		assertTrue(mazzo1.getScartiCarteOggetto().contains(carta));
	}

	@Test
	public void testNumeroCarteOggettoNelMazzo() {
		assertTrue(controllerMazzo.numeroCarteOggettoNelMazzo() == 12);
	}

	@Test
	public void testRigeneraMazzo() {
		controllerMazzo1.rigeneraMazzo();
		assertTrue(mazzo1.getMazzoCarteOggetto().size() == 3);
	}

	@Test
	public void testRimuoviCarta() {
		int num = mazzo1.getMazzoCarteOggetto().size();
		controllerMazzo1.rimuoviCarta();
		assertTrue(mazzo1.getMazzoCarteOggetto().size() == num-1);
	}

	@Test
	public void testPescaCartaOggetto() {
		int num = mazzo1.getMazzoCarteOggetto().size();
		CartaOggetto c = controllerMazzo1.pescaCartaOggetto();
		assertEquals(mazzo1.getMazzoCarteOggetto().size(), num-1);
	}

}
