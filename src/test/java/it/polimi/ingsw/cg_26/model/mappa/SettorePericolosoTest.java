package it.polimi.ingsw.cg_26.model.mappa;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SettorePericolosoTest {
	private Settore s1 = new SettorePericoloso("C07","settorePericoloso");
	
	@Before
	public void setUp() throws Exception {
		s1.setBloccata(true);
	}

	@Test
	public void testGetNome() {
		assertTrue(s1.getNome() == "C07");
	}

	@Test
	public void testGetNomeSupplementare() {
		assertTrue(s1.getNomeSupplementare() == "settorePericoloso");	
	}

	@Test
	public void testGetBloccata() {
		assertTrue(s1.getBloccata());
	}

}
