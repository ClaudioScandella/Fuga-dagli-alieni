package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import org.junit.Before;
import org.junit.Test;

public class CartaSettoreTest {

	private CartaSettore c1; 
	
	@Before
	public void setUp() throws Exception {
		c1 = new CartaSettore(TipoSettore.SILENZIO, true);
	}

	@Test
	public void testGetTipoCartaSettore() {
		assertEquals(c1.getTipoCartaSettore(), TipoSettore.SILENZIO);
	}

	@Test
	public void testGetConOggetto() {
		assertTrue(c1.getConOggetto() == true);
	}

}
