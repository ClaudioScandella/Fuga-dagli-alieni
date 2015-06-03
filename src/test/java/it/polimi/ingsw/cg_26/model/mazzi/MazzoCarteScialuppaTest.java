package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import org.junit.Before;
import org.junit.Test;

public class MazzoCarteScialuppaTest {
	
	MazzoCarteScialuppa m1 = new MazzoCarteScialuppa();

	@Before
	public void setUp() throws Exception {
	}

//come faccio a testare il mischia?!?
/*	@Test
	public void testMischia() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testPesca() {
		assertEquals(m1.pesca(), Colore.ROSSA);
		m1.rimuoviPrimaCarta();
		assertEquals(m1.pesca(), Colore.VERDE);
	}

}
