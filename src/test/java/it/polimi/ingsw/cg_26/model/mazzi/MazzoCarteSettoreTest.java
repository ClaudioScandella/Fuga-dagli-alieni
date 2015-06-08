package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MazzoCarteSettoreTest {

	private MazzoCarteSettore m1 = new MazzoCarteSettore();
	private MazzoCarteSettore m2 = new MazzoCarteSettore();
	private ArrayList<CartaSettore> array = new ArrayList<CartaSettore>();
	
	@Before
	public void setUp() throws Exception {
		array.add(new CartaSettore(TipoSettore.SILENZIO, true));
		m1.setMazzoCarteSettore(array);
	}

	@Test
	public void testGetMazzoCarteSettore() {
		assertEquals(m1.getMazzoCarteSettore(), array);
		assertTrue(m2.getMazzoCarteSettore().size() == 25);
	}

	@Test
	public void testGetScartiCarteSettore() {
		assertTrue(m1.getScartiCarteSettore().size() == 0);
		assertTrue(m2.getScartiCarteSettore().size() == 0);
	}

}
