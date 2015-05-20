package it.polimi.ingsw.cg_26.model.carte;

import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;
import junit.framework.TestCase;

public class CartaScialuppaTest extends TestCase {
	
	private CartaScialuppa c1,c2;
	private Colore VERDE, ROSSA;
	
	public CartaScialuppaTest(String nome){
		super(nome);
	}
	
	public void setUp(){
		c1 = new CartaScialuppa(VERDE);
		c2 = new CartaScialuppa(ROSSA);
	}

	public void testCartaScialuppa() {
		assertTrue(c1.getColore() == VERDE);
		assertTrue(c2.getColore() == ROSSA);
	}

	public void testGetColore() {
		assertTrue(c1.getColore() == VERDE);
		assertTrue(c2.getColore() == ROSSA);
	}

}
