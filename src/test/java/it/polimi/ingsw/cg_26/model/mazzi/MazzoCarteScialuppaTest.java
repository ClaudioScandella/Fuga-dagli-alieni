package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MazzoCarteScialuppaTest {
	
	private MazzoCarteScialuppa m1 = new MazzoCarteScialuppa();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMazzoCarteScialuppa() {
		assertTrue(m1.getMazzoCarteScialuppa().size() == 8);
	}

}
