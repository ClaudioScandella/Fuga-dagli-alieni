package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import org.junit.Before;
import org.junit.Test;

public class CartaScialuppaTest {
	
	private CartaScialuppa c1,c2;
	private Colore VERDE, ROSSA;

	@Before
	public void setUp() throws Exception {
		c1 = new CartaScialuppa(VERDE);
		c2 = new CartaScialuppa(ROSSA);
	}

	@Test
	public void testCartaScialuppa() {
		assertTrue(c1.getColore() == VERDE);
		assertTrue(c2.getColore() == ROSSA);
	}

	@Test
	public void testGetColore() {
		assertTrue(c1.getColore() == VERDE);
		assertTrue(c2.getColore() == ROSSA);
	}

}
