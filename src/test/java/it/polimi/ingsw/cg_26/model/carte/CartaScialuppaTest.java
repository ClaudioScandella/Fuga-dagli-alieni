package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import org.junit.Before;
import org.junit.Test;

public class CartaScialuppaTest {
	
	private CartaScialuppa c1;

	@Before
	public void setUp() throws Exception {
		
		c1 = new CartaScialuppa(Colore.VERDE);
	}

	@Test
	public void testGetColore() {
		assertEquals(c1.getColore(), Colore.VERDE);
	}

}
