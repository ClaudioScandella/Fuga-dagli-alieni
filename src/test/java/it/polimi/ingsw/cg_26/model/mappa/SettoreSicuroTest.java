package it.polimi.ingsw.cg_26.model.mappa;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SettoreSicuroTest {
	private Settore s1 = new SettoreSicuro("C07","settoreSicuro");
	
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
		assertTrue(s1.getNomeSupplementare() == "settoreSicuro");	
	}

	@Test
	public void testGetBloccata() {
		assertTrue(s1.getBloccata());
	}
}
