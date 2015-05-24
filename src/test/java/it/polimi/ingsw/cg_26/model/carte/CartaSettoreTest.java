package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import org.junit.Before;
import org.junit.Test;

public class CartaSettoreTest {
	
	private CartaSettore c1, c2, c3;
	private CartaSettore co1, co2, co3;
	private TipoSettore SILENZIO, RUMORE, RUMOREinSETTORE;

	@Before
	public void setUp() throws Exception {
		c1 = new CartaSettore(SILENZIO, false);
		c2 = new CartaSettore(RUMORE,false);
		c3 = new CartaSettore(RUMOREinSETTORE, false);
		co1 = new CartaSettore(SILENZIO, true);
		co2 = new CartaSettore(RUMORE,true);
		co3 = new CartaSettore(RUMOREinSETTORE, true);
	}

	@Test
	public void testCartaSettore() {
		assertTrue(c1.getTipoCartaSettore() == SILENZIO);
		assertTrue(c2.getTipoCartaSettore() == RUMORE);
		assertTrue(c3.getTipoCartaSettore() == RUMOREinSETTORE);
		assertTrue(co1.getTipoCartaSettore() == SILENZIO);
		assertTrue(co2.getTipoCartaSettore() == RUMORE);
		assertTrue(co3.getTipoCartaSettore() == RUMOREinSETTORE);
		
		assertFalse(c1.getConOggetto());
		assertFalse(c2.getConOggetto());
		assertFalse(c3.getConOggetto());
		assertTrue(co1.getConOggetto());
		assertTrue(co2.getConOggetto());
		assertTrue(co3.getConOggetto());
	}

	@Test
	public void testGetTipoCartaSettore() {
		assertTrue(c1.getTipoCartaSettore() == SILENZIO);
		assertTrue(c2.getTipoCartaSettore() == RUMORE);
		assertTrue(c3.getTipoCartaSettore() == RUMOREinSETTORE);
		assertTrue(co1.getTipoCartaSettore() == SILENZIO);
		assertTrue(co2.getTipoCartaSettore() == RUMORE);
		assertTrue(co3.getTipoCartaSettore() == RUMOREinSETTORE);
	}

	@Test
	public void testGetConOggetto() {
		assertFalse(c1.getConOggetto());
		assertFalse(c2.getConOggetto());
		assertFalse(c3.getConOggetto());
		assertTrue(co1.getConOggetto());
		assertTrue(co2.getConOggetto());
		assertTrue(co3.getConOggetto());
	}

}
