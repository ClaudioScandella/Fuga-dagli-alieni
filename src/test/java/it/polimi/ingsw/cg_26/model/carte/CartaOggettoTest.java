package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import org.junit.Before;
import org.junit.Test;

public class CartaOggettoTest {
	
	private CartaOggetto c1;

	@Before
	public void setUp() throws Exception {
		c1 = new CartaOggetto(TipoOggetto.DIFESA);
	}

	@Test
	public void testGetTipoOggetto() {
		assertEquals(c1.getTipoOggetto(), TipoOggetto.DIFESA);
	}

}
