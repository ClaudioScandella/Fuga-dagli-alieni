package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import org.junit.Before;
import org.junit.Test;

public class MazzoOggettiTest {
	
	MazzoOggetti m1 = new MazzoOggetti();
	MazzoOggetti m2 = new MazzoOggetti();


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPesca() {
		assertTrue(m1.pesca().getTipoOggetto() == TipoOggetto.ADRENALINA);
		m1.rimuoviCarta();
		m1.rimuoviCarta();
		assertTrue(m1.pesca().getTipoOggetto() == TipoOggetto.ATTACCO);
	}

	@Test
	public void testLunghezzaListaOggetti() {
		assertTrue(m2.lunghezzaListaOggetti() == 12);
		m2.rimuoviCarta();
		m2.rimuoviCarta();
		assertTrue(m2.lunghezzaListaOggetti() == 10);
	}

	@Test
	public void testAggiungiScartiOggetto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMazzoCarteOggetto() {
		fail("Not yet implemented");
	}

	@Test
	public void testMischia() {
		fail("Not yet implemented");
	}

	@Test
	public void testRigeneraMazzo() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostraMazzo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRimuoviCarta() {
		fail("Not yet implemented");
	}

}
