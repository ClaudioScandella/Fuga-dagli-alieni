package it.polimi.ingsw.cg_26.model.mappa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SettoreScialuppa2Test {
	private Settore s1 = new SettoreScialuppa2("P08");
	private Settore s2 = new SettoreScialuppa2("C07","settoreScialuppa2");
	
	@Before
	public void setUp() throws Exception {
		s1.setBloccata(true);
		s2.setBloccata(false);

	}

	@Test
	public void testGetNome() {
		assertTrue(s1.getNome() == "P08");
		assertTrue(s2.getNome() == "C07");
	}

	@Test
	public void testGetNomeSupplementare() {
		assertTrue(s2.getNomeSupplementare() == "settoreScialuppa2");	
	}

	@Test
	public void testGetBloccata() {
		assertTrue(s1.getBloccata());
		assertFalse(s2.getBloccata());	
	}
}
